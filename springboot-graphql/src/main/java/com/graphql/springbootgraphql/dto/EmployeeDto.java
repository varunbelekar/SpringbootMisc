package com.graphql.springbootgraphql.dto;

import com.graphql.springbootgraphql.model.Email;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDto {
    private Long employeeId;
    private String name;
    private List<Email> emails;
}
