package com.projects.employee_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projects.employee_server.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // add custom queries if needed later
}
