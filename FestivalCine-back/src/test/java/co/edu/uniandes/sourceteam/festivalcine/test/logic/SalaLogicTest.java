/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.test.logic;

import co.edu.uniandes.sourceteam.festivalcine.api.ISalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.SalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.PeliculaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.SalaPersistence;
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
public class SalaLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ISalaLogic salaLogic;
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
    private List<SalaEntity> data = new ArrayList<SalaEntity>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SalaLogic.class.getPackage())
                .addPackage(ISalaLogic.class.getPackage())
                .addPackage(SalaPersistence.class.getPackage())
                .addPackage(SalaPersistence.class.getPackage())
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
        em.createQuery("delete from SalaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 6 ; i++) {
            SalaEntity entity = factory.manufacturePojo(SalaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Company con un nombre que no existe
     */
    @Test
    public void createSalaTest1() throws Exception 
    {
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);
       
        SalaEntity result = salaLogic.createSala(newEntity);
        Assert.assertNotNull(result);

        SalaEntity entity = em.find(SalaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());

    }

    /**
     * Prueba para crear un Company con un nombre que ya existe
     */
    @Test(expected = Exception.class)
    public void createSalaTest2() throws Exception {
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);
        newEntity.setName(data.get(0).getName());
        SalaEntity result = salaLogic.createSala(newEntity);
    }

    /**
     * Prueba para consultar la lista de Companys
     *
     *
     */
    @Test
    public void getSalasTest() {
        List<SalaEntity> list = salaLogic.getSalas();
        Assert.assertEquals(data.size(), list.size());
        for (SalaEntity entity : list) {
            boolean found = false;
            for (SalaEntity storedEntity : data) {
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
    public void getSalaTest() {
        SalaEntity entity = data.get(0);
        SalaEntity resultEntity = salaLogic.getSala(entity.getId());
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
    public void deleteSalaTest() {
        SalaEntity entity = data.get(1);
        salaLogic.deleteSala(entity.getId());
        SalaEntity deleted = em.find(SalaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Company
     *
     *
     */
    @Test
    public void updateSalaTest() {
        SalaEntity entity = data.get(0);
        SalaEntity pojoEntity = factory.manufacturePojo(SalaEntity.class);

        pojoEntity.setId(entity.getId());

        salaLogic.updateSala(pojoEntity);

        SalaEntity resp = em.find(SalaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
}
