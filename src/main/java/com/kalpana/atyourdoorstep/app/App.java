package com.kalpana.atyourdoorstep.app;

import com.kalpana.atyourdoorstep.dao.EmployeeDao;
import com.kalpana.atyourdoorstep.entity.Employee;
import com.kalpana.atyourdoorstep.entity.EmployeePersonalDetails;
import com.kalpana.atyourdoorstep.entity.EmployeeType;
import com.kalpana.atyourdoorstep.entity.Gender;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AtYourDoorStep");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            new App().testInsert(em);
            em.getTransaction().commit();
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

    public Long testInsert(EntityManager em) {
        Employee emp = new EmployeeDao(em).insert(getEmployee());
        System.out.println("Employee with employee id =" + emp.getEmployeeId() + " Saved");
        return emp.getEmployeeId();
    }

}
