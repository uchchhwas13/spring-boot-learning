package com.roy.springboot.CRUDdemo.rest;

import com.roy.springboot.CRUDdemo.entity.Employee;
import com.roy.springboot.CRUDdemo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final JsonMapper jsonMapper;
    @Autowired
    public EmployeeRestController(EmployeeService employeeService, JsonMapper jsonMapper) {
        this.employeeService = employeeService;
        this.jsonMapper = jsonMapper;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee emp = employeeService.findById(employeeId);
        if (emp == null) {
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
        return emp;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee emp) {
        emp.setId(0);
        Employee dbEmployee = employeeService.save(emp);
        return dbEmployee;
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody Employee emp) {

        Employee existing = employeeService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        emp.setId(id);
        Employee updatedEmployee = employeeService.save(emp);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - "+ employeeId;
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,
                                  @RequestBody Map<String, Object> patchPayload) {
        Employee emp = employeeService.findById(employeeId);
        if(emp == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        if ( patchPayload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body - "+ employeeId);
        }
        Employee patchedEmployee = jsonMapper.updateValue(emp, patchPayload);
        return employeeService.save(patchedEmployee);
    }
}
