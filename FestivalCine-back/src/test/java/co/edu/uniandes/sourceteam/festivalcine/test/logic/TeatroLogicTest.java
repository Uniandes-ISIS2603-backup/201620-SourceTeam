/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.test.logic;

import co.edu.uniandes.sourceteam.festivalcine.api.ISalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.ITeatroLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.SalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.TeatroLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.SalaPersistence;
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
public class TeatroLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ITeatroLogic teatroLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<TeatroEntity> data = new ArrayList<TeatroEntity>();
    
    private List<SalaEntity> salasData = new ArrayList<>();
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TeatroEntity.class.getPackage())
                .addPackage(TeatroLogic.class.getPackage())
                .addPackage(ITeatroLogic.class.getPackage())
                .addPackage(TeatroPersistence.class.getPackage())
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(SalaLogic.class.getPackage())
                .addPackage(ISalaLogic.class.getPackage())
                .addPackage(SalaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    @Before
    public void setUp() 
    {
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
        em.createQuery("delete from SalaEntity").executeUpdate();
        em.createQuery("delete from TeatroEntity").executeUpdate();
    }
    
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) 
        {
            SalaEntity salaActual = factory.manufacturePojo(SalaEntity.class);
            em.persist(salaActual);
            salasData.add(salaActual);
        }
        
        for (int i = 0; i < 3; i++) 
        {
            TeatroEntity entity = factory.manufacturePojo(TeatroEntity.class);
            em.persist(entity);
            data.add(entity);
            
            if (i == 0) 
            {
                salasData.get(0).setTeatro(entity);
            }
        }
        
    }
    
    @Test
    public void createTeatroTest1() throws Exception
    {
        TeatroEntity newEntity = factory.manufacturePojo(TeatroEntity.class);
        TeatroEntity result = teatroLogic.createTeatro(newEntity);
        Assert.assertNotNull(result);
        TeatroEntity entity = em.find(TeatroEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    
    @Test(expected = Exception.class)
    public void createTeatroTest2() throws Exception 
    {
        TeatroEntity newEntity = factory.manufacturePojo(TeatroEntity.class);
        newEntity.setName( data.get(0).getName() );
        TeatroEntity result = teatroLogic.createTeatro(newEntity);
    }
    
    @Test
    public void getTeatrosTest() {
        List<TeatroEntity> list = teatroLogic.getTeatros();
        Assert.assertEquals(data.size(), list.size());
        for (TeatroEntity entity : list) {
            boolean found = false;
            for (TeatroEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId() ) ) 
                {
                    found = true;
                }
            }
            
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getTeatroTest() {
        TeatroEntity entity = data.get(0);
        TeatroEntity resultEntity = teatroLogic.getTeatro( entity.getId() );
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    
    @Test
    public void deleteTeatroTest() 
    {
        TeatroEntity entity = data.get(1);
        teatroLogic.deleteTeatro( entity.getId() );
        TeatroEntity deleted = em.find( TeatroEntity.class, entity.getId() );
        Assert.assertNull(deleted);
    }

   
    @Test
    public void updateTeatroTest() 
    {
        TeatroEntity entity = data.get(0);
        TeatroEntity pojoEntity = factory.manufacturePojo(TeatroEntity.class);

        pojoEntity.setId( entity.getId() );

        teatroLogic.updateTeatro(pojoEntity);

        TeatroEntity resp = em.find( TeatroEntity.class, entity.getId() );

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
    
    @Test
    public void getSalasTest() {
        TeatroEntity entity = data.get(0);
        SalaEntity sala = salasData.get(0);
        SalaEntity resp = teatroLogic.getSala( entity.getId(), sala.getId() );

        Assert.assertEquals( sala.getName(), resp.getName() );
        Assert.assertEquals( sala.getId(), resp.getId() );
        
    }
    
    @Test
    public void listSalasTest() 
    {
        List<SalaEntity> list = teatroLogic.listSalas( data.get(0).getId() );
        Assert.assertEquals( 1, list.size() );
    }
    
    @Test
    public void addSalaTest() 
    {
        TeatroEntity entity = data.get(0);
        SalaEntity sala = salasData.get(1);
        SalaEntity resp = teatroLogic.addSala( entity.getId(), sala.getId() );

        Assert.assertNotNull(resp);
        Assert.assertEquals( sala.getId(), resp.getId() );
    }
    
    @Test
    public void removeSalaTest()
    {
        teatroLogic.removeSala( data.get(0).getId(), salasData.get(0).getId() );
        SalaEntity resp = teatroLogic.getSala(data.get(0).getId(), salasData.get(0).getId() );
        Assert.assertNull(resp);
    }
}
