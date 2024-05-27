package com.Register.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(int id, Employee employee) {
        Employee employeeToUpdate = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));

        employeeToUpdate.setEmpid(employee.getEmpid());
        employeeToUpdate.setEmployeeName(employee.getEmployeeName());
        employeeToUpdate.setEmployeephonenumber(employee.getEmployeephonenumber());
        employeeToUpdate.setEmployeemailid(employee.getEmployeemailid());

        return employeeRepository.save(employeeToUpdate);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
