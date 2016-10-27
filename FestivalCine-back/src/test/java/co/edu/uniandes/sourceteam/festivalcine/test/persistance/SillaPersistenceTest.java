/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.test.persistance;

import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.SillaEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.SillaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import uk.co.jemos.podam.api.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.rodriguez20
 */
@RunWith(Arquillian.class)
public class SillaPersistenceTest {
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SillaEntity.class.getPackage())
                .addPackage(SillaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Compañía que contiene los departamentos. La relación entre Sala y
     * Silla es "composite"
     */
    SalaEntity fatherEntity;

    /**
     * Lista de los departamentos que serán utilizados en las pruebas. La
     * relación entre Sala y Silla es "composite"
     */
    private List<SillaEntity> data = new ArrayList<>();

    @Inject
    private SillaPersistence sillaPersistence;

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
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete  from SillaEntity").executeUpdate();
        em.createQuery("delete  from SalaEntity").executeUpdate();
    }

    /**
     * Para el correcto funcionamiento de las pruebas, inserta los datos
     * iniciales en la base de datos utilizando un manejador de persistencia.
     *
     * Crea una compañía y luego le adiciona tres departamentos.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        
        fatherEntity = factory.manufacturePojo(SalaEntity.class);
        
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        
        for (int i = 0; i < 3; i++) {
            SillaEntity entity = factory.manufacturePojo(SillaEntity.class);
            entity.setSala(fatherEntity);
            data.add(entity);
            em.persist(entity);
        }

    }
    
    @Test
    public void createSillaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        
        SillaEntity newEntity = factory.manufacturePojo(SillaEntity.class);
        
        newEntity.setSala(fatherEntity);
        
        SillaEntity result = sillaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        SillaEntity entity = em.find(SillaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    
    @Test
    public void getSillasInSalaTest() {
        List<SillaEntity> list = sillaPersistence.findAllInSala(fatherEntity.getId());
        
        Assert.assertEquals(data.size(), list.size());
        
        for (SillaEntity ent : list) {
            boolean found = false;
            for (SillaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getSillaTest() {
        SillaEntity entity = data.get(0);
        SillaEntity newEntity = sillaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void deleteSillaTest() {
        SillaEntity entity = data.get(0);
        sillaPersistence.delete(entity.getId());
        SillaEntity deleted = em.find(SillaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateSillaTest() {
        SillaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SillaEntity newEntity = factory.manufacturePojo(SillaEntity.class);

        newEntity.setId(entity.getId());

        sillaPersistence.update(newEntity);

        SillaEntity resp = em.find(SillaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    
    
    
}




