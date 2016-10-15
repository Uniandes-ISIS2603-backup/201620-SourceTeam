/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.testpersistance;

import co.edu.uniandes.sourceteam.festivalcine.entities.PeliculaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.PeliculaPersistence;
import co.edu.uniandes.sourceteam.festivalcine.persistence.SalaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ya.bejarano10
 */
public class SalaPersistanceTest 
{
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(SalaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private SalaPersistence salaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<SalaEntity> data = new ArrayList<SalaEntity>();
    
    @Test
    public void findAllSalasTest() {
        List<SalaEntity> list = salaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (SalaEntity ent : list) {
            boolean found = false;
            for (SalaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void findSalaTest() {
        SalaEntity entity = data.get(0);
        SalaEntity newEntity = salaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNumSala(), newEntity.getNumSala());
    }
    
    @Test
    public void findSalaByNumeroTest() {
        SalaEntity entity = data.get(0);
        SalaEntity newEntity = salaPersistence.findByNumero(entity.getNumSala());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNumSala(), newEntity.getNumSala());
    }
    
    @Test
    public void createSalaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);

        SalaEntity result = salaPersistence.create(newEntity);

        Assert.assertNotNull(result);
        SalaEntity entity = em.find(SalaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNumSala(), entity.getNumSala());
    }
    
    @Test
    public void updateSalaTest() {
        SalaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);

        newEntity.setId(entity.getId());

        salaPersistence.update(newEntity);

        SalaEntity resp = em.find(SalaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNumSala(), resp.getNumSala());
    }
    
    @Test
    public void deleteSalaTest() {
        SalaEntity entity = data.get(0);
        salaPersistence.delete(entity.getId());
        SalaEntity deleted = em.find(SalaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
