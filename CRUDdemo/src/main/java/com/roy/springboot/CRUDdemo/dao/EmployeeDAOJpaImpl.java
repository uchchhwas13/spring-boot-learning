package com.roy.springboot.CRUDdemo.dao;

import com.roy.springboot.CRUDdemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    private final EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        // Find employee by primary key
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        // merge handles both SAVE/INSERT (if id is 0) and UPDATE (if id exists)
        // It returns the updated/managed database instance
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(int id) {
        // Find the entity first
        Employee employee = entityManager.find(Employee.class, id);

        // If it exists, remove it
        if (employee != null) {
            entityManager.remove(employee);
        }
    }
}
