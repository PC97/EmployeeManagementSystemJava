package com.st.main;

import java.util.Scanner;

import com.st.bean.Department;
import com.st.bean.Employee;
import com.st.service.DatabaseConnectionService;
import com.st.service.DepartmentServiceDb;
import com.st.service.EmployeeServiceDb;

public class Main {

	public static void main(String[] args) {
		int choice;
		Scanner inp = new Scanner(System.in);
		EmployeeServiceDb employeeService = new EmployeeServiceDb();
		DepartmentServiceDb serviceDepartment = new DepartmentServiceDb();
		try {
			do {

				System.out.println("Enter following numbers for corresponding options");
				System.out.println("1 for Adding Employee");
				System.out.println("2 for Edit employee info");
				System.out.println("3 for Delete");
				System.out.println("4 for Displaying info of any employee");
				System.out.println("5 for Displaying info of all employee");
				System.out.println("6 for Adding Department");
				System.out.println("7 for Editting Department Info");
				System.out.println("8 for Deleting any Department Entry");
				System.out.println("9 for Displaying info of any Department ");
				System.out.println("10 for Displaying info of all Department");
				System.out.println("Any other for Exiting");

				System.out.println("");
				choice = inp.nextInt();
				inp.nextLine(); // For reading newline character

				switch (choice) {
				case 1:
					employeeService.addEmployee(inp);
					break;
				case 2:
					employeeService.editEmployee(inp);
					break;
				case 3:
					employeeService.deleteEmployee(inp);
					break;
				case 4:
					Employee emp = employeeService.getEmployee(inp);
					if (!(emp == null)) {
						System.out.println(emp);
					}
					break;
				case 5:
					employeeService.getAllEmployeeDetails(inp);
					break;
				case 6:
					serviceDepartment.addDepartment(inp);
					break;
				case 7:
					serviceDepartment.editDepartment(inp);
					break;
				case 8:
					serviceDepartment.deleteDepartment(inp);
					break;
				case 9:
					Department dept = serviceDepartment.getDepartment(inp);
					if (!(dept == null)) {
						System.out.println(dept);
					}
					break;
				case 10:
					serviceDepartment.getAllDepartmentDetails(inp);
					break;
				default:
					System.out.println("Exiting Employee Mgmt System");
//				serviceDepartment.finalize();
//				service.finalize();
					
					DatabaseConnectionService.closeConnection();
					return;

				}
			} while (true);
			//TODO:Close connection here
		} catch (Exception e) {
			System.out.println("Exiting Employee Mgmt System");
			DatabaseConnectionService.closeConnection();
			return;
		}
		
	}

}
