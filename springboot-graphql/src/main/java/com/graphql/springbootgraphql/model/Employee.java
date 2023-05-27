package com.graphql.springbootgraphql.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String name;

    @OneToMany(mappedBy = "employee")
    private List<Email> emails;
}
