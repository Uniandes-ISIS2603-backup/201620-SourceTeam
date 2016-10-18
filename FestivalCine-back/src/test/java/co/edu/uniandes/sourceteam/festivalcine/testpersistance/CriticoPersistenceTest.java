/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.testpersistance;

import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.CriticoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.ardila13
 */
@RunWith(Arquillian.class)
public class CriticoPersistenceTest {
    
    
    @Deployment
    public static JavaArchive createDepolyment() {
        return ShrinkWrap.create(JavaArchive.class)
        .addPackage(CriticoEntity.class.getPackage())
        .addPackage(CriticoPersistence.class.getPackage())
        .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
        .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }

    private void clearData() {
        em.createQuery("delete from CriticoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3;i++){
            CriticoEntity critico = factory.manufacturePojo(CriticoEntity.class);
            em.persist(critico);
            data.add(critico);
        }
    }
    
    @Inject
    private CriticoPersistence criticoPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<CriticoEntity> data =  new ArrayList<CriticoEntity>();
    
    public CriticoPersistenceTest() {
    }
    
    @Before
    public void setUp() {
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e)
        {
            try{
                utx.rollback();
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class FestivalPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<CriticoEntity> list = criticoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CriticoEntity critico : list ) {
            boolean found = false;
            for (CriticoEntity critico2 : data ) {
                if (critico.getId().equals(critico2.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }    
    }
    
    
     
    @Test
    public void findCriticoTest() {
        CriticoEntity entity = data.get(0);
        CriticoEntity newEntity = criticoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of create method, of class CriticoPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        CriticoEntity critico = factory.manufacturePojo(CriticoEntity.class);

        CriticoEntity resultado = criticoPersistence.create(critico);

        Assert.assertNotNull(resultado);
        CriticoEntity entidad = em.find(CriticoEntity.class, resultado.getId());
        Assert.assertNotNull(entidad);
        Assert.assertEquals(critico.getName(), entidad.getName());
    }

    /**
     * Test of update method, of class CriticoPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        CriticoEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CriticoEntity critico = factory.manufacturePojo(CriticoEntity.class);

        critico.setId(entidad.getId());

        criticoPersistence.update(critico);

        CriticoEntity resp = em.find(CriticoEntity.class, entidad.getId());

        Assert.assertEquals(critico.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class CriticoPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        CriticoEntity entity = data.get(0);
        criticoPersistence.delete(entity.getId());
        CriticoEntity deleted = em.find(CriticoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
