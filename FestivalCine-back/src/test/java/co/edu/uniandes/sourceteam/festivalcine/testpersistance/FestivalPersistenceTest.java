/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.testpersistance;

import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.FestivalPersistence;
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
public class FestivalPersistenceTest {
    
    
    @Deployment
    public static JavaArchive createDepolyment() {
        return ShrinkWrap.create(JavaArchive.class)
        .addPackage(FestivalEntity.class.getPackage())
        .addPackage(FestivalPersistence.class.getPackage())
        .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
        .addAsManifestResource("META-INF/benas.xml","beans.xml");
    }
    
    @Inject
    private FestivalPersistence festivalPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<FestivalEntity> data =  new ArrayList<FestivalEntity>();
    
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
                fail("falla en la configuracion de la ba de datos");
            }
        }
    }
    
    private void clearData() {
        em.createQuery("delete from FestivalEntity").executeUpdate();
        em.createQuery("delete from CriticoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3;i++){
            FestivalEntity festival = factory.manufacturePojo(FestivalEntity.class);
            em.persist(festival);
            data.add(festival);
        }
    }


    /**
     * Test of findAll method, of class FestivalPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<FestivalEntity> list = festivalPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FestivalEntity festival : list ) {
            boolean found = false;
            for (FestivalEntity festival2 : data && !found) {
                if (festival.getId().equals(festival2.getId())) {
                    found = true;
                }
            }
            if(!found)
                break;
            Assert.assertTrue(found);
        }   
    }
    
    
    @Test
    public void findFestivalTest() {
        FestivalEntity entity = data.get(0);
        FestivalEntity newEntity = festivalPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void findFestivalByNameTest() {
        FestivalEntity entity = data.get(0);
        FestivalEntity newEntity = festivalPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    

    /**
     * Test of create method, of class FestivalPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        FestivalEntity festival = factory.manufacturePojo(FestivalEntity.class);

        FestivalEntity resultado = festivalPersistence.create(festival);

        Assert.assertNotNull(resultado);
        FestivalEntity entidad = em.find(FestivalEntity.class, resultado.getId());
        Assert.assertNotNull(entidad);
        Assert.assertEquals(festival.getName(), entidad.getName());
    }

    /**
     * Test of update method, of class FestivalPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        FestivalEntity entidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FestivalEntity festival = factory.manufacturePojo(FestivalEntity.class);

        festival.setId(entidad.getId());

        festivalPersistence.update(festival);

        FestivalEntity resp = em.find(FestivalEntity.class, entidad.getId());

        Assert.assertEquals(festival.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class FestivalPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        FestivalEntity entity = data.get(0);
        festivalPersistence.delete(entity.getId());
        FestivalEntity deleted = em.find(FestivalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
