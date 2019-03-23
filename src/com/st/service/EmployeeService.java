package com.st.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.st.bean.Employee;
import com.st.dao.EmployeeUsingDbDao;

public class EmployeeService {

	 static Map<String,Employee> employeeMap=new HashMap<String,Employee>();
	
	public EmployeeService()
	{
		EmployeeUsingDbDao employeeDbDao = new EmployeeUsingDbDao();
		employeeMap = employeeDbDao.readDb();
	}
	
	public boolean addEmployee(Scanner in) {
		Employee emp=new Employee();
		boolean finish = true;
		while(finish) {
			try {
				System.out.println("Enter employee id :");
				emp.setId(in.nextLine());	
				System.out.println("emp id entered is :" +emp.getId());
				if( EmployeeService.employeeMap.containsKey(emp.getId()))
				{
					System.out.println("Employee with this id already exists");
					return false;
				}
				System.out.println("Enter employee name :");
				emp.setName(in.nextLine());
				System.out.println("emp name entered is :" +emp.getName());
				System.out.println("Enter employee department id  :");
				emp.setDeptId(in.nextLine());
				if(! DepartmentService.departmentMap.containsKey(emp.getDeptId()))
				{
					System.out.println("Department does not exists, Please first add department");
					return false;
				}
				System.out.println("emp DeptId entered is :" +emp.getDeptId());
				System.out.println("Enter employee salary  :");
				emp.setSalary(Double.parseDouble(in.nextLine()));
				System.out.println("emp Salary entered is :" +emp.getSalary());
				finish=false;
			} catch (Exception e) {
				System.out.println("Please re-enter details in correct format");
				emp.setSalary(0);
				//e.printStackTrace();
			}	
		}
		employeeMap.put(emp.getId(), emp);
		return true;
	}
	
	public boolean editEmployee(Scanner in) {
		String id;
		System.out.println("Enter Employee ID"); 
 		id = in.nextLine();
 		if(! EmployeeService.employeeMap.containsKey(id))
 		{
 			System.out.println("Employee does not exists, Please try add option");
 			return false;
 		}
 		
 		
 		Employee emp=new Employee();
		boolean finish = true;
		while(finish) {
			try {
//				System.out.println("Enter employee id :");
//				emp.setId(in.nextLine());	
//				System.out.println("emp id entered is :" +emp.getId());
				emp.setId(id);
				System.out.println("Enter employee name :");
				emp.setName(in.nextLine());
				System.out.println("emp name entered is :" +emp.getName());
				System.out.println("Enter employee department id  :");
				emp.setDeptId(in.nextLine());
				if(! DepartmentService.departmentMap.containsKey(emp.getDeptId()))
				{
					System.out.println("Department does not exists, Please first add department");
					return false;
				}
				System.out.println("emp DeptId entered is :" +emp.getDeptId());
				System.out.println("Enter employee salary  :");
				emp.setSalary(Double.parseDouble(in.nextLine()));
				System.out.println("emp Salary entered is :" +emp.getSalary());
				finish=false;
			} catch (Exception e) {
				System.out.println("Please re-enter details in correct format");
				emp.setSalary(0);
				//e.printStackTrace();
			}
		}
		EmployeeService.employeeMap.replace(id, emp);
		return true;
	}
	
	public boolean deleteEmployee(Scanner in) {
		String id;
		System.out.println("Enter Employee ID"); 
 		id = in.next();
 		if(! EmployeeService.employeeMap.containsKey(id))
 		{
 			System.out.println("Employee does not exists ");
 			return false;
 		}
 		EmployeeService.employeeMap.remove(id);
 		System.out.println("User deleted");
		return true;
	}
	
	public Employee getEmployee(Scanner in) {
		String id;
		System.out.println("Enter Employee ID"); 
 		id = in.next();
 		if(! EmployeeService.employeeMap.containsKey(id))
 		{
 			System.out.println("Employee does not exists ");
 			return null;
 		}
		return EmployeeService.employeeMap.get(id);
	}
	
	public void finalize()
	{
		EmployeeUsingDbDao employeeDbDao = new EmployeeUsingDbDao();
		employeeDbDao.writeDb(employeeMap);
	}
}
