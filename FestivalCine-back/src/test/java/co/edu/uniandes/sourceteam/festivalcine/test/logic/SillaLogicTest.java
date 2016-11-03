/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.test.logic;

import co.edu.uniandes.sourceteam.festivalcine.api.ISillaLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.SillaLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.SillaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.exceptions.BusinessLogicException;
import co.edu.uniandes.sourceteam.festivalcine.persistence.SillaPersistence;
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
 * @author s.rodriguez20
 */
@RunWith(Arquillian.class)
public class SillaLogicTest {
   SalaEntity fatherEntity;

    /**
     *
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     *
     */
    @Inject
    private ISillaLogic sillaLogic;

    /**
     *
     */
    @PersistenceContext
    private EntityManager em;

    /**
     *
     */
    @Inject
    private UserTransaction utx;

    /**
     *
     */
    private List<SillaEntity> sillaData = new ArrayList<SillaEntity>();

    /**
     *
     */
    private List<SalaEntity> salaData = new ArrayList<>();

    

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SillaEntity.class.getPackage())
                .addPackage(SillaLogic.class.getPackage())
                .addPackage(ISillaLogic.class.getPackage())
                .addPackage(SillaPersistence.class.getPackage())
                .addPackage(SalaEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from SillaEntity").executeUpdate();
        em.createQuery("delete from SalaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        fatherEntity = factory.manufacturePojo(SalaEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            SillaEntity entity = factory.manufacturePojo(SillaEntity.class);
            entity.setSala(fatherEntity);
            em.persist(entity);
            sillaData.add(entity);
        }
    }

    /**
     * Prueba para crear un Silla
     *
     *
     */
    @Test
    public void createSillaTest1() throws BusinessLogicException{
        SillaEntity newEntity = factory.manufacturePojo(SillaEntity.class);
        SillaEntity result = sillaLogic.createSilla(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        SillaEntity entity = em.find(SillaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
 /**
     * Prueba para crear un Sala con un nombre que ya existe
     */
    @Test(expected = BusinessLogicException.class)
    public void createSillaTest2() throws Exception {
        SillaEntity dept = factory.manufacturePojo(SillaEntity.class);
        dept.setSala(fatherEntity);
        dept.setFila(sillaData.get(0).getFila());
        dept.setNumero(sillaData.get(0).getNumero());
        SillaEntity result = sillaLogic.createSilla(sillaData.get(0).getSala().getId(), dept);
    }
    /**
     * Prueba para consultar la lista de Sillas
     *
     *
     */
    @Test
    public void getSillasTest() {
        List<SillaEntity> list = sillaLogic.getSillas(fatherEntity.getId());
        Assert.assertEquals(sillaData.size(), list.size());
        for (SillaEntity entity : list) {
            boolean found = false;
            for (SillaEntity storedEntity : sillaData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Silla
     *
     *
     */
    @Test
    public void getSillaTest() {
        SillaEntity entity = sillaData.get(0);
        SillaEntity resultEntity = sillaLogic.getSilla(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Silla
     *
     *
     */
    @Test
    public void deleteSillaTest() {
        SillaEntity entity = sillaData.get(1);
        sillaLogic.deleteSilla(entity.getId());
        SillaEntity deleted = em.find(SillaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Silla
     *
     *
     */
    @Test
    public void updateSillaTest() {
        SillaEntity entity = sillaData.get(0);
        SillaEntity pojoEntity = factory.manufacturePojo(SillaEntity.class);

        pojoEntity.setId(entity.getId());

        sillaLogic.updateSilla(fatherEntity.getId(), pojoEntity);

        SillaEntity resp = em.find(SillaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
