/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.test.logic;

import co.edu.uniandes.sourceteam.festivalcine.api.IBoletaLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.IClienteLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.BoletaLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.ClienteLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.BoletaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.ClienteEntity;
import co.edu.uniandes.sourceteam.festivalcine.exceptions.BusinessLogicException;
import co.edu.uniandes.sourceteam.festivalcine.persistence.BoletaPersistence;
import co.edu.uniandes.sourceteam.festivalcine.persistence.ClientePersistence;
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
public class ClienteLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IClienteLogic clienteLogic;
    /**
     *
     */
    @Inject
    private BoletaPersistence departmentPersistence;
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
    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addPackage(IClienteLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
                .addPackage(BoletaPersistence.class.getPackage())
                .addPackage(BoletaEntity.class.getPackage())
                .addPackage(BoletaLogic.class.getPackage())
                .addPackage(IBoletaLogic.class.getPackage())
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
        em.createQuery("delete from BoletaEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            for (BoletaEntity d : entity.getBoletas()) {
                d.setCliente(entity);
            }
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Cliente con un nombre que no existe
     */
    @Test
    public void createClienteTest1() throws BusinessLogicException {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        for (BoletaEntity d : newEntity.getBoletas()) {
            d.setCliente(newEntity);
        }

        ClienteEntity result = clienteLogic.createCliente(newEntity);
        Assert.assertNotNull(result);

        ClienteEntity entity = em.find(ClienteEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertNotNull(entity.getBoletas());
        Assert.assertNotNull(result.getBoletas());
        Assert.assertEquals(result.getBoletas().size(), entity.getBoletas().size());

        for (BoletaEntity d : result.getBoletas()) {
            boolean found = false;
            for (BoletaEntity oracle : entity.getBoletas()) {
                if (d.getName().equals(oracle.getName())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);

        }

    }

    /**
     * Prueba para crear un Cliente con un nombre que ya existe
     */
//    @Test(expected = BusinessLogicException.class)
//    public void createClienteTest2() throws Exception {
//        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
//        newEntity.setName(data.get(0).getName());
//        ClienteEntity result = clienteLogic.createCliente(newEntity);
//    }

    /**
     * Prueba para consultar la lista de Clientes
     *
     *
     */
    @Test
    public void getClientesTest() {
        List<ClienteEntity> list = clienteLogic.getClientes();
        Assert.assertEquals(data.size(), list.size());
        for (ClienteEntity entity : list) {
            boolean found = false;
            for (ClienteEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Cliente
     *
     *
     */
    @Test
    public void getClienteTest() {
        ClienteEntity entity = data.get(0);
        ClienteEntity resultEntity = clienteLogic.getCliente(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Cliente
     *
     *
     */
    @Test
    public void deleteClienteTest() {
        ClienteEntity entity = data.get(1);
        clienteLogic.deleteCliente(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Cliente
     *
     *
     */
    @Test
    public void updateClienteTest() {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);

        pojoEntity.setId(entity.getId());

        clienteLogic.updateCliente(pojoEntity);

        ClienteEntity resp = em.find(ClienteEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
