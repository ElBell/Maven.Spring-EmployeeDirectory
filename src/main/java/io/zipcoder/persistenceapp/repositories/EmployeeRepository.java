package io.zipcoder.persistenceapp.repositories;

import io.zipcoder.persistenceapp.entities.Department;
import io.zipcoder.persistenceapp.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByManager(Integer manager);
    List<Employee> findByDepartment(Department department);
}
