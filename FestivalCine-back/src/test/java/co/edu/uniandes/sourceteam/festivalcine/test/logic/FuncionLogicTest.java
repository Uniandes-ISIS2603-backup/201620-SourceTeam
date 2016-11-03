/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.test.logic;

import co.edu.uniandes.sourceteam.festivalcine.api.IFuncionLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.IPeliculaLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.ISalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.FuncionLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.PeliculaLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.SalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.FuncionEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.PeliculaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.FuncionPersistence;
import co.edu.uniandes.sourceteam.festivalcine.persistence.PeliculaPersistence;
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
 * @author ba.bohorquez10
 */
@RunWith(Arquillian.class)
public class FuncionLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IFuncionLogic funcionLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<FuncionEntity> data = new ArrayList<FuncionEntity>();
    
    private List<PeliculaEntity> peliculasData = new ArrayList<>();
    
    private List<SalaEntity> salasData = new ArrayList<>();
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FuncionEntity.class.getPackage())
                .addPackage(FuncionLogic.class.getPackage())
                .addPackage(IFuncionLogic.class.getPackage())
                .addPackage(FuncionPersistence.class.getPackage())
                .addPackage(PeliculaEntity.class.getPackage())
                .addPackage(PeliculaLogic.class.getPackage())
                .addPackage(IPeliculaLogic.class.getPackage())
                .addPackage(PeliculaPersistence.class.getPackage())
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
        em.createQuery("delete from FuncionEntity").executeUpdate();
        em.createQuery("delete from SalaEntity").executeUpdate();
        em.createQuery("delete from PeliculaEntity").executeUpdate();
    }
    
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) 
        {
            PeliculaEntity actual = factory.manufacturePojo(PeliculaEntity.class);
            em.persist(actual);
            peliculasData.add(actual);
        }
        
        for (int i = 0; i < 3; i++) 
        {
            SalaEntity actual = factory.manufacturePojo(SalaEntity.class);
            em.persist(actual);
            salasData.add(actual);
        }
        
        for (int i = 0; i < 3; i++) 
        {
            FuncionEntity entity = factory.manufacturePojo(FuncionEntity.class);
            em.persist(entity);
            data.add(entity);
            
            data.get(i).setPelicula( peliculasData.get(i) );
            data.get(i).setSala( salasData.get(i) );
        }
    }
    
    @Test
    public void createFuncionTest1() throws Exception
    {
        FuncionEntity newEntity = factory.manufacturePojo(FuncionEntity.class);
        FuncionEntity result = funcionLogic.createFuncion(newEntity);
        Assert.assertNotNull(result);
        FuncionEntity entity = em.find(FuncionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test(expected = Exception.class)
    public void createFuncionTest2() throws Exception 
    {
        FuncionEntity newEntity = factory.manufacturePojo(FuncionEntity.class);
        newEntity.setName( data.get(0).getName() ); 
        FuncionEntity result = funcionLogic.createFuncion(newEntity);
    }
    
    @Test(expected = Exception.class)
    public void createFuncionTest3() throws Exception 
    {
        FuncionEntity newEntity = factory.manufacturePojo(FuncionEntity.class);
        newEntity.setPrecio(-2);
        FuncionEntity result = funcionLogic.createFuncion(newEntity);
    }
    
    @Test
    public void getFuncionesTest() 
    {
        List<FuncionEntity> list = funcionLogic.getFunciones();
        Assert.assertEquals(data.size(), list.size());
        for (FuncionEntity entity : list) 
        {
            boolean found = false;
            for (FuncionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId() ) ) 
                {
                    found = true;
                }
            }
            
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getFuncionTest() 
    {
        FuncionEntity entity = data.get(0);
        FuncionEntity resultEntity = funcionLogic.getFuncion( entity.getId() );
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void deleteFuncionTest() 
    {
        FuncionEntity entity = data.get(1);
        funcionLogic.deleteFuncion( entity.getId() );
        FuncionEntity deleted = em.find( FuncionEntity.class, entity.getId() );
        Assert.assertNull(deleted);
    }

   
    @Test
    public void updateFuncionTest() 
    {
        FuncionEntity entity = data.get(0);
        FuncionEntity pojoEntity = factory.manufacturePojo(FuncionEntity.class);

        pojoEntity.setId( entity.getId() );

        funcionLogic.updateFuncion(pojoEntity);

        FuncionEntity resp = em.find( FuncionEntity.class, entity.getId() );

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
    
    @Test
    public void getPeliculaTest() 
    {
        FuncionEntity entity = data.get(0);
        PeliculaEntity pelicula = peliculasData.get(0);
        PeliculaEntity resp = funcionLogic.getPelicula ( entity.getId() );

        Assert.assertEquals( pelicula.getName(), resp.getName() );
        Assert.assertEquals( pelicula.getId(), resp.getId() );
       
    }
    
    @Test
    public void setPeliculaTest()
    {
        FuncionEntity entity = data.get(0);
        PeliculaEntity sala = peliculasData.get(1);
        PeliculaEntity resp = funcionLogic.setPelicula( entity.getId(), sala.getId() );

        Assert.assertNotNull(resp);
        Assert.assertEquals( sala.getId(), resp.getId() );
    }
    
    
    @Test
    public void getSalaTest()
    {
        FuncionEntity entity = data.get(0);
        SalaEntity sala = salasData.get(0);
        SalaEntity resp = funcionLogic.getSala( entity.getId() );

        Assert.assertEquals( sala.getName(), resp.getName() );
        Assert.assertEquals( sala.getId(), resp.getId() );
    }
    
    @Test
    public void setSalaTest()
    {
        FuncionEntity entity = data.get(0);
        SalaEntity sala = salasData.get(1);
        SalaEntity resp = funcionLogic.setSala( entity.getId(), sala.getId() );

        Assert.assertNotNull(resp);
        Assert.assertEquals( sala.getId(), resp.getId() );
    }
    
}
    
    
 

