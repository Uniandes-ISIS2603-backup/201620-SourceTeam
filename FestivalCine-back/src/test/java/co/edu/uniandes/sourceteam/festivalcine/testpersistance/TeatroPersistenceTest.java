package co.edu.uniandes.sourceteam.festivalcine.testpersistance;

import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.TeatroPersistence;
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
 * @author ba.bohorquez10
 */
@RunWith(Arquillian.class)
public class TeatroPersistenceTest 
{
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TeatroEntity.class.getPackage())
                .addPackage(TeatroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private TeatroPersistence teatroPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<TeatroEntity> data = new ArrayList<TeatroEntity>();

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
    
    private void clearData() 
    {
        em.createQuery("delete from TeatroEntity").executeUpdate();
    }
    
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TeatroEntity entity = factory.manufacturePojo(TeatroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createTeatroTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        TeatroEntity newEntity = factory.manufacturePojo(TeatroEntity.class);

        TeatroEntity result = teatroPersistence.create(newEntity);

        Assert.assertNotNull(result);
        TeatroEntity entity = em.find(TeatroEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Test
    public void getTeatrosTest()
    {
        List<TeatroEntity> list = teatroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TeatroEntity ent : list) 
        {
            boolean found = false;
            for (TeatroEntity entity : data) {
                if ( ent.getId().equals(entity.getId() ) ) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Teatro.
     *
     * 
     */
    @Test
    public void getTeatroTest() 
    {
        TeatroEntity entity = data.get(0);
        TeatroEntity newEntity = teatroPersistence.find( entity.getId() );
        Assert.assertNotNull(newEntity);
        Assert.assertEquals( entity.getName(), newEntity.getName() );
        Assert.assertEquals( entity.getCiudad(), newEntity.getCiudad() );
    }

    /**
     * Prueba para eliminar un Teatro.
     *
     * 
     */
    @Test
    public void deleteTeatroTest() 
    {
        TeatroEntity entity = data.get(0);
        teatroPersistence.delete(entity.getId());
        TeatroEntity deleted = em.find(TeatroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Teatro.
     *
     * 
     */
    @Test
    public void updateTeatroTest() 
    {
        TeatroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TeatroEntity newEntity = factory.manufacturePojo(TeatroEntity.class);

        newEntity.setId(entity.getId());

        teatroPersistence.update(newEntity);

        TeatroEntity resp = em.find(TeatroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getCiudad(), resp.getCiudad() );
        
    }
}
