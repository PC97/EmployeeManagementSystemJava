package com.st.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.st.bean.Employee;
import com.st.dao.EmployeeServiceDbDao;

public class EmployeeServiceDb {

	public boolean addEmployee(Scanner in) {
	
		Employee employee = new Employee();
		boolean finish = true;
		while(finish) {
			try {
				System.out.println("Enter employee id :");
				employee.setId(in.nextLine());	
				System.out.println("employee id entered is :" +employee.getId());
				System.out.println("Enter employee name :");
				employee.setName(in.nextLine());
				System.out.println("employee name entered is :" +employee.getName());
				System.out.println("Enter employee department id  :");
				employee.setDeptId(in.nextLine());
				System.out.println("employee DeptId entered is :" +employee.getDeptId());
				System.out.println("Enter employee salary  :");
				employee.setSalary(Double.parseDouble(in.nextLine()));
				System.out.println("employee Salary entered is :" +employee.getSalary());
				finish=false;
			} catch (Exception e) {
				System.out.println("Please re-enter details in correct format");
				employee.setSalary(0);
				//e.printStackTrace();
			}	
		}
		if(EmployeeServiceDbDao.addEmp(employee))
		{
		System.out.println("Employee added");
		return true;
		}
		return false;
	}

	public boolean deleteEmployee(Scanner inp) {
		
		String id;
		System.out.println("Enter Employee ID"); 
 		id = inp.nextLine();
 		if( EmployeeServiceDbDao.deleteEmp(id))
 		{
 			System.out.println("Employee deleted");
 			return true;
 		}
		return false;

	}

	public boolean editEmployee(Scanner inp) {
		System.out.println("Enter employee id :");
		String id = inp.nextLine();	
		System.out.println("employee id entered is : " + id);
		if(! EmployeeServiceDbDao.empExists(id))
		{
			System.out.println("Employee does not Exist. Try add option");
 			return false;
		}
		Employee employee = new Employee();
		employee.setId(id);
		System.out.println("Enter employee name :");
		employee.setName(inp.nextLine());
		System.out.println("employee name entered is :" +employee.getName());
		System.out.println("Enter employee department id  :");
		employee.setDeptId(inp.nextLine());
		System.out.println("employee DeptId entered is :" +employee.getDeptId());
		System.out.println("Enter employee salary  :");
		employee.setSalary(Double.parseDouble(inp.nextLine()));
		System.out.println("employee Salary entered is :" +employee.getSalary());
		if(EmployeeServiceDbDao.editEmp(employee))
		{
		System.out.println("Employee Editted");
		return true;
		}
		return false;
	}

	public Employee getEmployee(Scanner inp) {
		System.out.println("Enter employee id :");
		Employee employee = new Employee();
		String id = inp.nextLine();	
		System.out.println("employee id entered is : " + id);
		employee.setId(id);
		employee = 	EmployeeServiceDbDao.getEmployee(employee);
		if( employee == null)
		{
			System.out.println("Employee does not exists");
			return null;
		}
		
		return employee;
	}

	public void getAllEmployeeDetails(Scanner inp) {
		System.out.println("Following are all the employees");
		ArrayList<Employee> arrayList = EmployeeServiceDbDao.getAllEmployee();
		Iterator<Employee> iterator = arrayList.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}
}
