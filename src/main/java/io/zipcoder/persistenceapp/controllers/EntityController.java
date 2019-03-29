package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.entities.Department;
import io.zipcoder.persistenceapp.entities.Employee;
import io.zipcoder.persistenceapp.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/API")
public class EntityController {
    private final EntityService entityService;

    @Autowired
    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    @PostMapping("/employees/")
    public Employee createEmployee(@RequestBody Employee employee) {
        return entityService.createEmployee(employee);
    }

    @PostMapping("/departments/")
    public Department createDepartment(@RequestBody Department department) {
        return entityService.createDepartment(department);
    }

    @PutMapping("/employees/updatemanager/{employeeId}")
    public void setManager(@PathVariable int employeeId, @RequestParam int manager) {
        entityService.setManager(employeeId, manager);
    }

    @PutMapping("/employees/{employeeId}")
    public void update(@PathVariable int employeeId, @RequestBody Employee employee) {
        entityService.update(employeeId, employee);
    }

    @PutMapping("/departments/updatemanager/{departmentId}")
    public void updateDepartmentManager(@PathVariable int departmentId, @RequestParam int manager) {
        entityService.updateDepartmentManager(departmentId, manager);
    }

    @PutMapping("/departments/updatename/{departmentId}")
    public void updateDepartmentName(@PathVariable int departmentId, @RequestParam String name) {
        entityService.updateDepartmentName(departmentId, name);
    }

    @GetMapping("/employees/manager/{managerId}")
    public List<Employee> getEmployeesByManager(@PathVariable int managerId) {
        return entityService.getEmployeesByManager(managerId);
    }

    @GetMapping("/employees/manager/null")
    public List<Employee> getNullManager() {
        return entityService.getNullManager();
    }

    @GetMapping("/employees/{employeeId}/managers")
    public List<Employee> findAllManagers(@PathVariable int employeeId) {
        return entityService.findAllManagers(employeeId);
    }

    @GetMapping("employees/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable int departmentId) {
        return entityService.findByDepartment(departmentId);
    }

    @DeleteMapping("/employees/remove/")
    public void removeEmployees(@RequestBody Employee ... employees) {
        entityService.removeEmployees(employees);
    }

    @DeleteMapping("/employees/remove/{departmentId}")
    public void removeByDepartment(@PathVariable int departmentId) {
        entityService.removeByDepartment(departmentId);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getAttributes(@PathVariable int employeeId) {
        return entityService.getAttributes(employeeId);
    }

    @PutMapping("/departments/merge")
    public void mergeDepartments(@RequestBody Department a, @RequestBody Department b) {
        entityService.mergeDepartments(a, b);
    }

    @GetMapping("/departments/{departmentId}")
    public Department findDepartment(@PathVariable int departmentId) {
        return entityService.findDepartment(departmentId);
    }

}
