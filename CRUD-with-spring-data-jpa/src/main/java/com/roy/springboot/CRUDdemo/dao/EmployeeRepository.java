package com.roy.springboot.CRUDdemo.dao;

import com.roy.springboot.CRUDdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
