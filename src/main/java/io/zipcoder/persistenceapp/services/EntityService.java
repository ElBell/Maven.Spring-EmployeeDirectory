package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.entities.Department;
import io.zipcoder.persistenceapp.entities.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EntityService {
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EntityService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void setManager(int employeeId, int manager) {
        Employee employee = employeeRepository.findOne(employeeId);
        employee.setManager(manager);
        employeeRepository.save(employee);
    }

    public void update(int id, Employee employee) {
        employeeRepository.delete(id);
        employeeRepository.save(employee);
    }

    public void updateDepartmentManager(int departmentId, int manager) {
        Department department = departmentRepository.findOne(departmentId);
        department.setManager_id(manager);
        departmentRepository.save(department);
    }

    public void updateDepartmentName(int departmentId, String name) {
        Department department = departmentRepository.findOne(departmentId);
        department.setName(name);
        departmentRepository.save(department);
    }

    public List<Employee> getEmployeesByManager(int manager) {
        return employeeRepository.findByManager(manager);
    }

    public List<Employee> getNullManager() {
        return employeeRepository.findByManager(null);
    }

    public List<Employee> findAllManagers(int employeeId) {
        List<Employee> employees = new ArrayList<>();
        while (employeeRepository.findOne(employeeRepository.findOne(employeeId).getManager()) != null) {
            Employee manager = employeeRepository.findOne(employeeRepository.findOne(employeeId).getManager());
            employees.add(manager);
            employeeId = manager.getNumber();
        }
        return employees;
    }
    public List<Employee> findByDepartment(int departmentId) {
        return employeeRepository.findByDepartment(departmentRepository.findOne(departmentId));
    }

    public void removeEmployees(Employee ... employees) {
        employeeRepository.delete(Arrays.asList(employees));
    }

    public void removeByDepartment(int departmentId) {
        employeeRepository.delete(employeeRepository.findByDepartment(departmentRepository.findOne(departmentId)));
    }

    public Employee getAttributes(int employeeId) {
        return employeeRepository.findOne(employeeId);
    }

    public void mergeDepartments(Department a, Department b) {
        departmentRepository.findOne(b.getNumber()).setManager_id(a.getManager_id());
        List<Employee> employees = employeeRepository.findByDepartment(b);
        employees.forEach(employee -> employee.setDepartment(a));
        employeeRepository.save(employees);
    }

    public Department findDepartment(int departmentId) {
        return departmentRepository.findOne(departmentId);
    }

}
