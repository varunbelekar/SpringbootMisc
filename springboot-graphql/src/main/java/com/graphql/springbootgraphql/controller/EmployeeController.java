package com.graphql.springbootgraphql.controller;

import com.graphql.springbootgraphql.dto.EmailDto;
import com.graphql.springbootgraphql.dto.EmployeeDto;
import com.graphql.springbootgraphql.model.Email;
import com.graphql.springbootgraphql.model.Employee;
import com.graphql.springbootgraphql.repository.EmailRepository;
import com.graphql.springbootgraphql.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmailRepository emailRepository;

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employee")
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = employees
                .stream().map(this::toEmployeeDto).collect(Collectors.toList());

        //Without JPA mapping
        /*employeeDtos.forEach(employeeDto ->
                employeeDto.setEmails(emailRepository.findByEmployeeId(employeeDto.getEmployeeId())));*/
        return employeeDtos;
    }

    public EmployeeDto toEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setName(employee.getName());
        employeeDto.setEmails(employee.getEmails());
        return employeeDto;
    }


    @PostMapping("/email")
    public Email saveEmail(@RequestBody EmailDto emailDto) {
        Employee employee = employeeRepository.getReferenceById(emailDto.getEmployeeId());
        Email email = toEmail(emailDto);
        email.setEmployee(employee);
        return emailRepository.save(email);
    }

    public Email toEmail(EmailDto emailDto) {
        Email email = new Email();
        email.setEmail(emailDto.getEmail());
        return email;
    }
}
