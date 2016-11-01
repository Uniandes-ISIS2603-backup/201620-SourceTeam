/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.test.logic;

import co.edu.uniandes.sourceteam.festivalcine.api.IPeliculaLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.PeliculaLogic;
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
public class PeliculaLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IPeliculaLogic peliculaLogic;
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
    private List<PeliculaEntity> data = new ArrayList<PeliculaEntity>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PeliculaEntity.class.getPackage())
                .addPackage(PeliculaLogic.class.getPackage())
                .addPackage(IPeliculaLogic.class.getPackage())
                .addPackage(PeliculaPersistence.class.getPackage())
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
        em.createQuery("delete from PeliculaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            PeliculaEntity entity = factory.manufacturePojo(PeliculaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Company con un nombre que no existe
     */
    @Test
    public void createPeliculaTest1() throws Exception 
    {
        PodamFactory factory = new PodamFactoryImpl();
        PeliculaEntity newEntity = factory.manufacturePojo(PeliculaEntity.class);
       
        PeliculaEntity result = peliculaLogic.createPelicula(newEntity);
        Assert.assertNotNull(result);

        PeliculaEntity entity = em.find(PeliculaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());

    }

    /**
     * Prueba para crear un Company con un nombre que ya existe
     */
    @Test(expected = Exception.class)
    public void createPeliculaTest2() throws Exception {
        PeliculaEntity newEntity = factory.manufacturePojo(PeliculaEntity.class);
        newEntity.setName(data.get(0).getName());
        PeliculaEntity result = peliculaLogic.createPelicula(newEntity);
    }

    /**
     * Prueba para consultar la lista de Companys
     *
     *
     */
    @Test
    public void getPeliculasTest() {
        List<PeliculaEntity> list = peliculaLogic.getPeliculas();
        Assert.assertEquals(data.size(), list.size());
        for (PeliculaEntity entity : list) {
            boolean found = false;
            for (PeliculaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Company
     *
     *
     */
    @Test
    public void getPeliculaTest() {
        PeliculaEntity entity = data.get(0);
        PeliculaEntity resultEntity = peliculaLogic.getPelicula(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Company
     *
     *
     */
    @Test
    public void deletePeliculaTest() {
        PeliculaEntity entity = data.get(1);
        peliculaLogic.deletePelicula(entity.getId());
        PeliculaEntity deleted = em.find(PeliculaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Company
     *
     *
     */
    @Test
    public void updatePeliculaTest() {
        PeliculaEntity entity = data.get(0);
        PeliculaEntity pojoEntity = factory.manufacturePojo(PeliculaEntity.class);

        pojoEntity.setId(entity.getId());

        peliculaLogic.updatePelicula(pojoEntity);

        PeliculaEntity resp = em.find(PeliculaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
}
