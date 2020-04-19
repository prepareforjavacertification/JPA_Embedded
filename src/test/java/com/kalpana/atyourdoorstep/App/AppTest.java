package com.kalpana.atyourdoorstep.App;

import com.kalpana.atyourdoorstep.dao.EmployeeDao;
import com.kalpana.atyourdoorstep.entity.Employee;
import com.kalpana.atyourdoorstep.entity.EmployeePersonalDetails;
import com.kalpana.atyourdoorstep.entity.EmployeeType;
import com.kalpana.atyourdoorstep.entity.Gender;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private Employee emp;
    private EntityManager em;
    private EmployeeDao dao;
    private EntityManagerFactory emf;
    
    @Before
    public void setUp(){
        emf = Persistence.createEntityManagerFactory("AtYourDoorStep");
        em = emf.createEntityManager();
        dao = new EmployeeDao(em);
    }
    
    @After
    public void tearDown(){
        emp = null;
        dao = null;
        em = null;
        emf = null;
    }
    
    private void testInsert() {
        emp = dao.insert(getEmployee());
        System.out.println("Employee with employee id ="+emp.getEmployeeId() +" Saved");
      }

    @Test
    public void testEmployeeCrud() {
        try {
            em.getTransaction().begin();
            testInsert();
            em.getTransaction().commit();
             assertTrue(emp.getEmployeeId() != null);
            } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    private Employee getEmployee() {
        Employee emp = new Employee();
        emp.setEmployeeJoiningDate(new Date());
        emp.setEmployeeSalary(11000L);
        emp.setEmployeeType(EmployeeType.PERMANENT);
        
        EmployeePersonalDetails personalDetails = new EmployeePersonalDetails();
        personalDetails.setFirstName("Robin");
        personalDetails.setLastName("Hood");
        personalDetails.setGender(Gender.MALE);
        personalDetails.setMiddleName(null);
        
        emp.setPersonalDetails(personalDetails);
        return emp;
    }
}
