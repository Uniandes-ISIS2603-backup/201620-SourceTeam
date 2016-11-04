/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.test.logic;

import co.edu.uniandes.sourceteam.festivalcine.api.ICriticoLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.IFestivalLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.CriticoLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.FestivalLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.CriticoPersistence;
import co.edu.uniandes.sourceteam.festivalcine.persistence.FestivalPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.ardila13
 */
@RunWith(Arquillian.class)
public class FestivalLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
        
    @Inject
    private IFestivalLogic festivalLogic;
    
    @Inject
    private ICriticoLogic criticosLogic;
    
        
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<FestivalEntity> data = new ArrayList<FestivalEntity>();
    
    private List<CriticoEntity> criticosData = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FestivalEntity.class.getPackage())
                .addPackage(FestivalLogic.class.getPackage())
                .addPackage(IFestivalLogic.class.getPackage())
                .addPackage(FestivalPersistence.class.getPackage())
                .addPackage(CriticoPersistence.class.getPackage())
                .addPackage(CriticoEntity.class.getPackage())
                .addPackage(CriticoLogic.class.getPackage())
                .addPackage(ICriticoLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() {
        em.createQuery("delete from CriticoEntity").executeUpdate();
        em.createQuery("delete from FestivalEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            CriticoEntity criticos = factory.manufacturePojo(CriticoEntity.class);
            em.persist(criticos);
            criticosData.add(criticos);
        }
        
        for (int i = 0; i < 3; i++) {
            FestivalEntity entity = factory.manufacturePojo(FestivalEntity.class);
            em.persist(entity);
            data.add(entity);

            if (i == 0) {
                criticosData.get(i).setFestival(entity);
            }
        }
    }
    
    @Test
    public void createFestivalTest1() throws Exception {
        FestivalEntity newEntity = factory.manufacturePojo(FestivalEntity.class);
        for (CriticoEntity d : newEntity.getCriticos()) {
            d.setFestival(newEntity);
        }

        FestivalEntity result = festivalLogic.createFestival(newEntity);
        Assert.assertNotNull(result);

        FestivalEntity entity = em.find(FestivalEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertNotNull(entity.getCriticos());
        Assert.assertNotNull(result.getCriticos());
        Assert.assertEquals(result.getCriticos().size(), entity.getCriticos().size());

        for (CriticoEntity d : result.getCriticos()) {
            boolean found = false;
            for (CriticoEntity oracle : entity.getCriticos()) {
                if (d.getName().equals(oracle.getName())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);

        }

    }
    
    @Test(expected = Exception.class)
    public void createFestivalTest2() throws Exception {
        FestivalEntity newEntity = factory.manufacturePojo(FestivalEntity.class);
        newEntity.setName(data.get(0).getName());
        FestivalEntity result = festivalLogic.createFestival(newEntity);
    }
    
    @Test
    public void getFestivalesTest() {
        List<FestivalEntity> list = festivalLogic.getFestivales();
        Assert.assertEquals(data.size(), list.size());
        for (FestivalEntity entity : list) {
            boolean found = false;
            for (FestivalEntity entity2 : data) {
                if (entity.getId().equals(entity2.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getFestivalTest2() {
        FestivalEntity entity = data.get(0);
        FestivalEntity resultEntity = festivalLogic.getFestival(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
 
    @Test
    public void deleteFestivalTest() {
        FestivalEntity entity = data.get(1);
        festivalLogic.deleteFestival(entity.getId());
        FestivalEntity deleted = em.find(FestivalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateFestivalTest() throws Exception {
        FestivalEntity entity = data.get(0);
        FestivalEntity pojoEntity = factory.manufacturePojo(FestivalEntity.class);

        pojoEntity.setId(entity.getId());
        if(pojoEntity.getDuracion() < 0)
            pojoEntity.setDuracion(pojoEntity.getDuracion()*-1);
        else if (pojoEntity.getDuracion() == 0)
            pojoEntity.setDuracion(1);

        festivalLogic.updateFestival(pojoEntity);

        FestivalEntity resp = em.find(FestivalEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
       
    
    @Test
    public void getCriticosTest() {
        FestivalEntity entity = data.get(0);
        CriticoEntity criticoEntity = criticosData.get(0);
        CriticoEntity response = festivalLogic.getCritico(entity.getId(), criticoEntity.getId());

        Assert.assertEquals(criticoEntity.getName(), response.getName());
        Assert.assertEquals(criticoEntity.getId(), response.getId());
        Assert.assertEquals(criticoEntity.getCredencial(), response.getCredencial());
        Assert.assertEquals(criticoEntity.getDuracion(), response.getDuracion());
    }
    
    
    @Test
    public void listCriticosTest() {
        List<CriticoEntity> list = festivalLogic.listCriticos(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }
    
    @Test
    public void addCriticoTest() {
        FestivalEntity entity = data.get(0);
        CriticoEntity criticoEntity = criticosData.get(1);
        CriticoEntity response = festivalLogic.addCritico(entity.getId(), criticoEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(criticoEntity.getId(), response.getId());
    }
    
    @Test
    public void removeCriticoTest() {
        festivalLogic.removeCritico(data.get(0).getId(), criticosData.get(0).getId());
        CriticoEntity response = festivalLogic.getCritico(data.get(0).getId(), criticosData.get(0).getId());
        Assert.assertNull(response);
    }
}
