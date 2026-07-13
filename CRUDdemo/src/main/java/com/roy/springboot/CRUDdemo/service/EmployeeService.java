package com.roy.springboot.CRUDdemo.service;

import com.roy.springboot.CRUDdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteById(int id);
}
