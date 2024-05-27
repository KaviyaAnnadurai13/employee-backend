
package com.Register.Register;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
@Autowired
EmployeeService employeeService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/post")
public Employee saveEmployee(@RequestBody Employee employee){
    return employeeService.saveEmployee(employee);
}
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
@GetMapping

public List<Employee> getAllEmployee(){
    return employeeService.getAllEmployee();
}
@PreAuthorize("hasRole('ROLE_USER')")
@GetMapping("/empget/{id}")

public Optional<Employee> getEmployeeById(@PathVariable int id){
    return employeeService.getEmployeeById(id);
}

@PreAuthorize("hasRole('ROLE_ADMIN')")  
@PutMapping("/update/{id}")

public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee){
    return employeeService.updateEmployee(id,employee);
}
@PreAuthorize("hasRole('ROLE_ADMIN')")  
@DeleteMapping("/delete/{id}")

public void deleteEmployee(@PathVariable int id){
    employeeService.deleteEmployee(id);
}

}
