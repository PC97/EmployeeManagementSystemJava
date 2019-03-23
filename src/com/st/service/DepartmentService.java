package com.st.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.st.bean.Department;
import com.st.bean.Employee;
import com.st.dao.DepartmentUsingDbDao;

public class DepartmentService {
 static Map<String,Department> departmentMap=new HashMap<String,Department>();
	
	public DepartmentService()
	{
		DepartmentUsingDbDao DepartmentUsingDbDao = new DepartmentUsingDbDao();
		departmentMap = DepartmentUsingDbDao.readDb();
	}
	public boolean addDepartment(Scanner in) {
		Department dept=new Department();
		boolean finish = true;
		while(finish ) {
			try {
				System.out.println("Enter Departmet id :");
				dept.setDeptId(in.nextLine());	
				System.out.println("dept id entered is :" +dept.getDeptId());
				if(DepartmentService.departmentMap.containsKey(dept.getDeptId()))
				{
					System.out.println("Department with this id already exists");
					return false;
				}
				System.out.println("Enter Department name :");
				dept.setName(in.nextLine());
				System.out.println("Department name entered is :" +dept.getName());
				System.out.println("Enter manager id :");
				dept.setManagerName(in.nextLine());
				System.out.println("manager id is :" +dept.getManagerName());
				finish = false;
			} catch (Exception e) {
				System.out.println("Please re-enter data");
			}				
		}
		departmentMap.put(dept.getDeptId(), dept);
		return true;
	}
	
	public boolean editDepartment(Scanner in) {
		String id;
		System.out.println("Enter Department ID"); 
 		id = in.nextLine();
 		if(! DepartmentService.departmentMap.containsKey(id))
 		{
 			System.out.println("Department does not exists");
 			return false;
 		}
 		
 		Department dept=new Department();
		boolean finish = true;
		while(finish ) {
			try {
//				System.out.println("Enter Departmet id :");
//				dept.setDeptId(in.nextLine());	
//				System.out.println("dept id entered is :" +dept.getDeptId());
				dept.setDeptId(id);
				System.out.println("Enter department name :");
				dept.setName(in.nextLine());
				System.out.println("Dept name entered is :" +dept.getName());
				System.out.println("Enter manager id :");
				dept.setManagerName(in.nextLine());
				System.out.println("manager id is :" +dept.getManagerName());
				finish = false;
			} catch (Exception e) {
				System.out.println("Please re-enter data");
				
				//e.printStackTrace();
			}				
		}
		
		DepartmentService.departmentMap.replace(id, dept);
		return true;
	}
	
	public boolean deleteDepartment(Scanner in) {
		String id;
		System.out.println("Enter Department ID"); 
 		id = in.nextLine();
 		Map<String, Employee> employeeMap = EmployeeService.employeeMap;
 		for (Map.Entry<String, Employee> entry : employeeMap.entrySet())
 		{
 			Employee employee = entry.getValue();
 			if(employee.getDeptId().equals(id))
 			{
 				System.out.println("Department cannot be deleted due to Foreign key contraints");
 				return false;
 			}
 		}
 		if(! DepartmentService.departmentMap.containsKey(id))
 		{
 			System.out.println("Department does not exists ");
 			return false;
 		}
 		DepartmentService.departmentMap.remove(id);
 		System.out.println("Department deleted");
		return true;
	}
	
	public Department getDepartment(Scanner in) {
		String id;
		System.out.println("Enter Department ID"); 
 		id = in.next();
 		if(! DepartmentService.departmentMap.containsKey(id))
 		{
 			System.out.println("Department does not exists ");
 			return null;
 		}
		return DepartmentService.departmentMap.get(id);
	}
	public void finalize()
	{
		DepartmentUsingDbDao DepartmentUsingDbDao = new DepartmentUsingDbDao();
		DepartmentUsingDbDao.writeDb(departmentMap);
	}
	
}
