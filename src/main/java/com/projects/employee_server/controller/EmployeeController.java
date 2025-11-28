package com.projects.employee_server.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityNotFoundException;

import com.projects.employee_server.entity.Employee;
import com.projects.employee_server.service.EmployeeService;
import com.projects.employee_server.entity.Task;
import com.projects.employee_server.service.TaskService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final TaskService taskService; // for employee tasks endpoint

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.createEmployee(employee);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            Employee emp = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(emp);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Employee updated = employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted Successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }

    // Additional helpful endpoint: get tasks for a given employee
    @GetMapping("/employees/{id}/tasks")
    public ResponseEntity<?> getTasksForEmployee(@PathVariable Long id) {
        try {
            java.util.List<Task> tasks = taskService.getTasksByEmployeeId(id);
            return ResponseEntity.ok(tasks);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }
}
