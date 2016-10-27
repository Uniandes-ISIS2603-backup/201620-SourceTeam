/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.test.persistance;

import co.edu.uniandes.sourceteam.festivalcine.entities.PeliculaEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.PeliculaPersistence;
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
 * @author ya.bejarano10
 */
@RunWith(Arquillian.class)
public class PeliculaPersistanceTest 
{
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PeliculaEntity.class.getPackage())
                .addPackage(PeliculaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private PeliculaPersistence peliculaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
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
        em.createQuery("delete from PeliculaEntity").executeUpdate();
    }
    private List<PeliculaEntity> data = new ArrayList<PeliculaEntity>();
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PeliculaEntity entity = factory.manufacturePojo(PeliculaEntity.class);
            
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void findAllPeliculasTest() {
        List<PeliculaEntity> list = peliculaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PeliculaEntity ent : list) {
            boolean found = false;
            for (PeliculaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void findPeliculaTest() {
        PeliculaEntity entity = data.get(0);
        PeliculaEntity newEntity = peliculaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void findPeliculaByNameTest() {
        PeliculaEntity entity = data.get(0);
        PeliculaEntity newEntity = peliculaPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void createPeliculaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PeliculaEntity newEntity = factory.manufacturePojo(PeliculaEntity.class);

        PeliculaEntity result = peliculaPersistence.create(newEntity);

        Assert.assertNotNull(result);
        PeliculaEntity entity = em.find(PeliculaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Test
    public void updatePeliculaTest() {
        PeliculaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PeliculaEntity newEntity = factory.manufacturePojo(PeliculaEntity.class);

        newEntity.setId(entity.getId());

        peliculaPersistence.update(newEntity);

        PeliculaEntity resp = em.find(PeliculaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    @Test
    public void deletePeliculaTest() {
        PeliculaEntity entity = data.get(0);
        peliculaPersistence.delete(entity.getId());
        PeliculaEntity deleted = em.find(PeliculaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
