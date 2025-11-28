package com.projects.employee_server.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.projects.employee_server.entity.Employee;
import com.projects.employee_server.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
    }

    public Employee updateEmployee(Long id, Employee updated) {
        Employee existing = employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setDepartment(updated.getDepartment());
        // Note: tasks are managed via Task endpoints; avoid overwriting here unless intended

        return employeeRepository.save(existing);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
