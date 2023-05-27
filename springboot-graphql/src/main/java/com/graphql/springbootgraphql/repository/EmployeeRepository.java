package com.graphql.springbootgraphql.repository;

import com.graphql.springbootgraphql.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
