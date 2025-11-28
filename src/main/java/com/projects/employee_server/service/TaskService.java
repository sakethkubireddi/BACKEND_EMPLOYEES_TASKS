package com.projects.employee_server.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.projects.employee_server.entity.Task;
import com.projects.employee_server.entity.Employee;
import com.projects.employee_server.repository.TaskRepository;
import com.projects.employee_server.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public Task createTask(Task task) {
        // if task.employee is set with an id, ensure the employee exists and attach managed entity
        if (task.getEmployee() != null && task.getEmployee().getId() != null) {
            Long empId = task.getEmployee().getId();
            Employee emp = employeeRepository.findById(empId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + empId));
            task.setEmployee(emp);
        } else {
            task.setEmployee(null); // unassigned
        }
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
    }

    public Task updateTask(Long id, Task updated) {
        Task existing = taskRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());

        // Update assignment if provided
        if (updated.getEmployee() != null && updated.getEmployee().getId() != null) {
            Long empId = updated.getEmployee().getId();
            Employee emp = employeeRepository.findById(empId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + empId));
            existing.setEmployee(emp);
        } else {
            existing.setEmployee(null); // unassign
        }

        return taskRepository.save(existing);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByEmployeeId(Long employeeId) {
        // Optionally throw if employee doesn't exist — here we just return empty list if none
        if (!employeeRepository.existsById(employeeId)) {
            throw new EntityNotFoundException("Employee not found with id: " + employeeId);
        }
        return taskRepository.findByEmployeeId(employeeId);
    }
}
