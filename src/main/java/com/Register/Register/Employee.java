package com.Register.Register;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

public class Employee {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getEmpid() {
	return empid;
}
public void setEmpid(int empid) {
	this.empid = empid;
}
public String getEmployeeName() {
	return EmployeeName;
}
public void setEmployeeName(String employeeName) {
	EmployeeName = employeeName;
}
public Long getEmployeephonenumber() {
	return Employeephonenumber;
}
public void setEmployeephonenumber(Long employeephonenumber) {
	Employeephonenumber = employeephonenumber;
}
public String getEmployeemailid() {
	return Employeemailid;
}
public void setEmployeemailid(String employeemailid) {
	Employeemailid = employeemailid;
}
private int empid;
private String EmployeeName;
private Long Employeephonenumber;
private String Employeemailid;
}
