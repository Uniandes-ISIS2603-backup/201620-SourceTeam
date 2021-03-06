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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CriticoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ICriticoLogic criticoLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<CriticoEntity> criticoData = new ArrayList<CriticoEntity>();
    
     FestivalEntity festival;
    
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
    }

   private void insertData() {

        festival = factory.manufacturePojo(FestivalEntity.class);
        festival.setId(1L);
        em.persist(festival);
        for (int i = 0; i < 3; i++) {
            CriticoEntity entity = factory.manufacturePojo(CriticoEntity.class);
            entity.setFestival(festival);
            em.persist(entity);
            criticoData.add(entity);
        }
    }

    @Test
    public void createCriticoTest() throws Exception{
        CriticoEntity newEntity = factory.manufacturePojo(CriticoEntity.class);
        if(newEntity.getDuracion() < 0)
            newEntity.setDuracion(newEntity.getDuracion()*-1);
        else if(newEntity.getDuracion() == 0)
            newEntity.setDuracion(1);
        CriticoEntity result = criticoLogic.createCritico( newEntity);
        Assert.assertNotNull(result);
        CriticoEntity entity = em.find(CriticoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test(expected = Exception.class)
    public void createCriticoTest2() throws Exception {
        CriticoEntity critico = criticoData.get(0);
        critico.setDuracion(-1);
        CriticoEntity result = criticoLogic.createCritico(critico);
    }
    
    @Test
    public void getCriticosTest() {
        List<CriticoEntity> list = criticoLogic.getCriticos();
        Assert.assertEquals(criticoData.size(), list.size());
        for (CriticoEntity entity : list) {
            boolean found = false;
            for (CriticoEntity entity2 : criticoData) {
                if (entity.getId().equals(entity2.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
        @Test
    public void getCriticoTest() {
        CriticoEntity entity = criticoData.get(0);
        CriticoEntity resultEntity = criticoLogic.getCritico(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    
    @Test
    public void deleteCriticoTest() {
        CriticoEntity entity = criticoData.get(1);
        criticoLogic.deleteCritico(entity.getId());
        CriticoEntity deleted = em.find(CriticoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
        @Test
    public void updateCriticoTest() throws Exception{
        CriticoEntity entity = criticoData.get(0);
        CriticoEntity pojoEntity = factory.manufacturePojo(CriticoEntity.class);

        pojoEntity.setId(entity.getId());
        
        if(pojoEntity.getDuracion() < 0)
            pojoEntity.setDuracion(pojoEntity.getDuracion()*-1);
        else if(pojoEntity.getDuracion() == 0)
            pojoEntity.setDuracion(1);

        criticoLogic.updateCritico( pojoEntity);


        CriticoEntity resp = em.find(CriticoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
