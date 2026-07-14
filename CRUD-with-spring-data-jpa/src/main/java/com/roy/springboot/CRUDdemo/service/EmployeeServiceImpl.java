package com.roy.springboot.CRUDdemo.service;

import com.roy.springboot.CRUDdemo.dao.EmployeeRepository;
import com.roy.springboot.CRUDdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employee = repository.findById(id);
        Employee result = null;
        if (employee.isPresent()) {
            result = employee.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return result;
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
