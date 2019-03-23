package com.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.st.bean.Employee;
import com.st.service.DatabaseConnectionService;

public class EmployeeServiceDbDao {
	
	public static boolean empExists(String id)
	{
		Connection connection = DatabaseConnectionService.connectDb();
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT "
				+ "COUNT(*) FROM EMPLOYEE_DETAILS WHERE emp_id = ?"))
		{
			preparedStatement.setString(1, id);
			ResultSet resultSet = null ;
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if(resultSet.getInt(1) != 0)
				{
				return true;
			
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static boolean depExists(String id)
	{
		Connection connection = DatabaseConnectionService.connectDb();
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT "
				+ "COUNT(*) FROM DEPARTMENT_DETAILS WHERE dept_id = ?"))
		{
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if(resultSet.getInt(1) != 0)return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean addEmp(Employee employee) {
		Connection connection = DatabaseConnectionService.connectDb();
		try(
				PreparedStatement preparedStatement2 = connection.prepareStatement("Insert into "
						+ "EMPLOYEE_DETAILS(emp_id, emp_name, dept_id, salary) values(?,?,?,?)"))
		{
			if(empExists(employee.getId()))
			{
				System.out.println("User already exist");
				return false;
			}
			
			if( ! depExists(employee.getDeptId()))
			{
				System.out.println("Department does not exists, Please add department first.");
				return false;
			}
			preparedStatement2.setString(1, employee.getId());
			preparedStatement2.setString(2, employee.getName());
			preparedStatement2.setString(3, employee.getDeptId());
			preparedStatement2.setDouble(4, employee.getSalary());
			preparedStatement2.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean deleteEmp(String id) {
		Connection connection = DatabaseConnectionService.connectDb();

		try(Statement statement = connection.createStatement())
		{
			if( ! empExists(id))
			{
				System.out.println("User does not exist");
				return false;
			}
			statement.execute("Delete From EMPLOYEE_DETAILS where emp_id = '" +id +"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static boolean editEmp(Employee employee) {
		Connection connection = DatabaseConnectionService.connectDb();
		try(Statement statement = connection.createStatement();
				PreparedStatement preparedStatement2 = connection.prepareStatement("Update "
						+ " EMPLOYEE_DETAILS SET "
						+ " emp_name = ? , dept_id = ?, salary = ? "
						+ " WHERE  emp_id = ? "))
		{
			if( ! depExists(employee.getDeptId()))
			{
				System.out.println("Department does not exists, Please add department first.");
				return false;
			}
			preparedStatement2.setString(4, employee.getId());
			preparedStatement2.setString(1, employee.getName());
			preparedStatement2.setString(2, employee.getDeptId());
			preparedStatement2.setDouble(3, employee.getSalary());
			preparedStatement2.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static Employee getEmployee(Employee employee) {

		Connection connection = DatabaseConnectionService.connectDb();
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT "
				+ " EMP_NAME, salary, emp.dept_id, dept_name, manager_id "
				+ " FROM EMPLOYEE_DETAILS emp " 
				+ " INNER JOIN DEPARTMENT_DETAILS dep " 
				+ " ON  emp.emp_id = ? and emp.dept_id = dep.dept_id "))
		{
			if(! empExists(employee.getId()))
			{
				return null;
			}
			preparedStatement.setString(1, employee.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
//			System.out.println("111111111111111");
			while(resultSet.next())
			{
//				System.out.println("dsafjsdafj");
				System.out.println("NAME : "+resultSet.getString("EMP_NAME"));
				System.out.println("Dept id: "+resultSet.getString("dept_id"));
				System.out.println("dept Name: "+resultSet.getString("dept_name"));
				System.out.println("manager id: "+resultSet.getString("manager_id"));
				System.out.println("salary : "+resultSet.getDouble("salary"));
				employee.setDeptId(resultSet.getString("dept_id"));
				employee.setName(resultSet.getString("EMP_NAME"));
				employee.setSalary(resultSet.getDouble("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return employee;
	}
	public static ArrayList<Employee> getAllEmployee() {
		ArrayList<Employee> arrayList = new ArrayList<Employee>();
		Connection connection = DatabaseConnectionService.connectDb();
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT "
				+ " emp_id, EMP_NAME, salary, dept_id  "
				+ " FROM EMPLOYEE_DETAILS  " ))
		{
			
//			Nothing to be set
			ResultSet resultSet = preparedStatement.executeQuery();
//			System.out.println("111111111111111");
			while(resultSet.next())
			{
				Employee employee = new Employee();
				employee.setId(resultSet.getString("emp_id"));
				employee.setDeptId(resultSet.getString("dept_id"));
				employee.setName(resultSet.getString("EMP_NAME"));
				employee.setSalary(resultSet.getDouble("salary"));
				arrayList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return arrayList;
	}
	

}
