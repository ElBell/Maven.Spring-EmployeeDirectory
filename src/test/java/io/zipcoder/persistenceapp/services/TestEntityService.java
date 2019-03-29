package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.entities.Department;
import io.zipcoder.persistenceapp.entities.Employee;
import io.zipcoder.persistenceapp.services.EntityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TestEntityService {
    @Autowired
    EntityService entityService;

    @Test
    public void testGetEmployeesByNull() {
        entityService.getNullManager().forEach(employee -> entityService.removeEmployees(employee));
        int expected = 15;
        List<Employee> employees = new ArrayList<>();
        for (int i = 11110; i < 11130; i++) {
            Employee employee = new Employee();
            employee.setNumber(i);
            if (i >= 11122) {
                employee.setManager(i % 5);
            }
            employees.add(employee);
        }
        employees.forEach(employee -> entityService.createEmployee(employee));
        int actual = entityService.getNullManager().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateEmployee() {
        String expected = "HiThere";
        Employee employee = new Employee();
        employee.setNumber(10192384);
        employee.setFirstName(expected);
        entityService.createEmployee(employee);
        String actual = entityService.getAttributes(10192384).getFirstName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateReturnEmployee() {
        String expected = "HiThere@Hi.com";
        Employee employee = new Employee();
        employee.setNumber(3);
        employee.setEmail(expected);
        String actual = entityService.createEmployee(employee).getEmail();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateReturnDepartment() {
        String expected = "testName";
        Department department = new Department();
        department.setNumber(3);
        department.setName(expected);
        String actual = entityService.createDepartment(department).getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateDepartmentManager() {
        int expected = 19382740;
        Department department = new Department();
        department.setManager_id(1);
        department.setNumber(34);
        entityService.createDepartment(department);
        entityService.updateDepartmentManager(34, expected);
        int actual = entityService.findDepartment(34).getManager_id();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetManager() {
        int expected = 9;
        Employee employee = new Employee();
        employee.setNumber(10);
        employee.setManager(10);
        entityService.createEmployee(employee);
        entityService.setManager(10, expected);
        int actual = entityService.getAttributes(10).getManager();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        Employee employee = new Employee();
        employee.setNumber(10);
        employee.setPhoneNumber("12341234");
        entityService.createEmployee(employee);
        Employee newEmployee = new Employee();
        newEmployee.setNumber(10);
        newEmployee.setPhoneNumber("765432");
        entityService.update(10, newEmployee);
        Assert.assertEquals("765432", entityService.getAttributes(10).getPhoneNumber());
    }

    @Test
    public void testUpdateDepartmentName() {
        String expected = "DogPeters";
        Department department = new Department();
        department.setName("DogIgnorers");
        department.setNumber(34);
        entityService.createDepartment(department);
        entityService.updateDepartmentName(34, expected);
        String actual = entityService.findDepartment(34).getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetEmployeesByManager() {
        int expected = 4;
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Employee employee = new Employee();
            employee.setNumber(i);
            employee.setManager(i % 5);
            employees.add(employee);
        }
        employees.forEach(employee -> entityService.createEmployee(employee));
        int actual = entityService.getEmployeesByManager(1).size();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testFindAllManagers() {
        int expected = 2;
        Employee employee1 = new Employee();
        employee1.setNumber(1123);
        employee1.setManager(1234);
        Employee employee2 = new Employee();
        employee2.setNumber(1234);
        employee2.setManager(2234);
        Employee employee3 = new Employee();
        employee3.setNumber(2234);
        employee3.setManager(3234);
        Employee employee4 = new Employee();
        employee4.setNumber(4567890);
        employee4.setManager(134534);
        entityService.createEmployee(employee1);
        entityService.createEmployee(employee2);
        entityService.createEmployee(employee3);
        entityService.createEmployee(employee4);
        int actual = entityService.findAllManagers(1123).size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindByDepartment() {
        int expected = 2;
        Department department = new Department();
        Department department1 = new Department();
        department.setNumber(1);
        department1.setNumber(10);
        Employee employee1 = new Employee();
        employee1.setNumber(5000);
        employee1.setDepartment(department);
        Employee employee2 = new Employee();
        employee2.setNumber(5678);
        employee2.setDepartment(department);
        Employee employee3 = new Employee();
        employee3.setNumber(34567);
        employee3.setDepartment(department1);
        entityService.createDepartment(department);
        entityService.createDepartment(department1);
        entityService.createEmployee(employee1);
        entityService.createEmployee(employee2);
        entityService.createEmployee(employee3);
        int actual = entityService.findByDepartment(1).size();
        Assert.assertEquals(expected, actual);
    }
    @Test(expected = JpaObjectRetrievalFailureException.class)
    public void testRemoveByDepartment() {
        int expected = 2;
        Department department = new Department();
        Department department1 = new Department();
        department.setNumber(2);
        department1.setNumber(30);
        Employee employee1 = new Employee();
        employee1.setNumber(600);
        employee1.setDepartment(department);
        Employee employee2 = new Employee();
        employee2.setNumber(601);
        employee2.setDepartment(department);
        Employee employee3 = new Employee();
        employee3.setNumber(602);
        employee3.setDepartment(department1);
        entityService.createEmployee(employee1);
        entityService.createEmployee(employee2);
        entityService.createEmployee(employee3);
        entityService.createDepartment(department);
        entityService.createDepartment(department1);
        entityService.removeByDepartment(department.getNumber());
        int actual = entityService.findByDepartment(2).size();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveEmployees() {
        Employee employee = new Employee();
        employee.setNumber(900);
        Employee employee1 = new Employee();
        employee1.setNumber(901);
        Employee employee2 = new Employee();
        employee2.setNumber(902);
        entityService.createEmployee(employee);
        entityService.createEmployee(employee1);
        entityService.createEmployee(employee2);
        entityService.removeEmployees(employee, employee1);
        int actual = entityService.getAttributes(employee.getNumber()).getNumber();
        Assert.assertEquals(900, actual);
    }

    @Test
    public void testMergeDepartments() {
        int expected = 3;
        Department department = new Department();
        int expectedManager = 45;
        department.setNumber(1);
        department.setManager_id(expectedManager);
        Department department1 = new Department();
        department1.setManager_id(67);
        department1.setNumber(10);
        Employee employee1 = new Employee();
        employee1.setNumber(5000);
        employee1.setDepartment(department);
        Employee employee2 = new Employee();
        employee2.setNumber(5678);
        employee2.setDepartment(department);
        Employee employee3 = new Employee();
        employee3.setNumber(34567);
        employee3.setDepartment(department1);
        entityService.createDepartment(department);
        entityService.createDepartment(department1);
        entityService.createEmployee(employee1);
        entityService.createEmployee(employee2);
        entityService.createEmployee(employee3);
        entityService.mergeDepartments(department, department1);
        int actual = entityService.findByDepartment(1).size();
        int actualManager = entityService.findDepartment(1).getManager_id();
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedManager, actualManager);
    }
}
