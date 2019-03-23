package com.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.st.bean.Employee;
import com.st.service.DatabaseConnectionService;

public class EmployeeUsingDbDao {

	public Map<String, Employee> readDb()
	{
		HashMap<String, Employee> hmap = new HashMap<String, Employee>();
		Connection connection = DatabaseConnectionService.connectDb();
		//TODO: try with autoresource close java 1.7 syntax
		try (Statement statement = connection.createStatement()){
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE_DETAILS");
			while(resultSet.next())
			{
				Employee employee = new Employee();
				employee.setId(resultSet.getString(1));
				employee.setName(resultSet.getString(2));
				employee.setDeptId(resultSet.getString(3));
				employee.setSalary(resultSet.getDouble(4));
				hmap.put(employee.getId(), employee);
			}
		} catch (SQLException e) {
			System.out.println("Exceptn while copying hashmap");
			e.printStackTrace();
		}
		///DatabaseConnectionService.closeConnection();
		return hmap;
		
	}

	public void writeDb(Map<String, Employee> hmap) {
		Connection connection = DatabaseConnectionService.connectDb();
		try (PreparedStatement statement = connection.prepareStatement("insert into "
				+ "EMPLOYEE_DETAILS(emp_id, emp_name, dept_id, salary) "
				+ "values(?,?,?,?)");
				Statement delStatement =connection.createStatement())
		{
			delStatement.executeQuery("TRUNCATE TABLE employee_details");
			
			//ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES");
			//TODO: check how to do batch update
			connection.setAutoCommit(false);
			for (Map.Entry<String, Employee> entry : hmap.entrySet())
			{
				Employee emp = entry.getValue();
				statement.setString(1, emp.getId());
				statement.setString(2, emp.getName());
				statement.setString(3, emp.getDeptId());
				statement.setDouble(4, emp.getSalary());
				statement.addBatch();
			}
			int count[] = statement.executeBatch();
			System.out.println("Total number of employees are" + count.length);
			connection.commit();
			//statement.close();
		} catch (SQLException e) {
			System.out.println("Exceptn while copying hashmap to db");
			e.printStackTrace();
		}
		
		//DatabaseConnectionService.closeConnection();
	}
}
