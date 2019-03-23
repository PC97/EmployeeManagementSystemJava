package com.st.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.st.bean.Department;

public class DepartmentServiceDb {

	public boolean addDepartment(Scanner inp) {
		Department department = new Department();
		boolean finish = true;
		while(finish) {
			try {
				System.out.println("Enter department id :");
				department.setDeptId(inp.nextLine());	
				System.out.println("department id entered is :" +department.getDeptId());
				System.out.println("Enter department name :");
				department.setName(inp.nextLine());
				System.out.println("department name entered is :" +department.getName());
				System.out.println("Enter manager id :");
				department.setManagerName(inp.nextLine());
				finish=false;
			} catch (Exception e) {
				System.out.println("Please re-enter details in correct format");
				//e.printStackTrace();
			}	
		}
		if(DepartmentServiceDbDao.addDept(department))
		{
			System.out.println("Department added");
			return true;
		}
		return false;
		
	}

	public boolean editDepartment(Scanner inp) {
		System.out.println("Enter department id :");
		String id = inp.nextLine();	
		System.out.println("department id entered is : " + id);
		if(! DepartmentServiceDbDao.depExists(id))
		{
			System.out.println("Department does not Exist. Try add option");
 			return false;
		}
		Department department = new Department();
		department.setDeptId(id);
		System.out.println("Enter department name :");
		department.setName(inp.nextLine());
		System.out.println("department name entered is :" +department.getName());
		System.out.println("Enter manager department id  :");
		department.setManagerName(inp.nextLine());
		System.out.println("department manager entered is :" +department.getDeptId());
		if(DepartmentServiceDbDao.editDept(department))
		{
		System.out.println("Department Editted");
		return true;
		}
		return false;
		
	}

	public Department getDepartment(Scanner inp) {
		System.out.println("Enter department id :");
		Department department = new Department();
		String id = inp.nextLine();	
		System.out.println("department id entered is : " + id);
		department.setDeptId(id);
		department = 	DepartmentServiceDbDao.getDepartment(department);
		if( department == null)
		{
			System.out.println("Department does not exists");
			return null;
		}
		
		return department;

	}

	public boolean deleteDepartment(Scanner inp) {
		String id;
		System.out.println("Enter Department ID"); 
 		id = inp.nextLine();
 		if( DepartmentServiceDbDao.deleteDept(id))
 		{
 			System.out.println("Department deleted");
 			return true;
 		}

 		return false;
		
	}

	public void getAllDepartmentDetails(Scanner inp) {
		System.out.println("Following are all the department");
		ArrayList<Department> arrayList = DepartmentServiceDbDao.getAllDepartment();
		Iterator<Department> iterator = arrayList.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}

}
