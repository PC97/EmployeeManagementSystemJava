package com.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.st.bean.Department;
import com.st.service.DatabaseConnectionService;

public class DepartmentUsingDbDao {

	public Map<String, Department> readDb()
	{
		HashMap<String, Department> hmap = new HashMap<String, Department>();
		Connection connection = DatabaseConnectionService.connectDb();
		//TODO: try with autoresource close java 1.7 syntax
		try (Statement statement = connection.createStatement()){
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPARTMENT_DETAILS");
			while(resultSet.next())
			{
				Department department = new Department();
				department.setDeptId(resultSet.getString(1));
				department.setName(resultSet.getString(2));
				department.setManagerName(resultSet.getString(3));
				//department.setSalary(resultSet.getDouble(4));
				hmap.put(department.getDeptId(), department);
			}
		} catch (SQLException e) {
			System.out.println("Exceptn while copying hashmap");
			e.printStackTrace();
		}
		///DatabaseConnectionService.closeConnection();
		return hmap;
		
	}

	public void writeDb(Map<String, Department> hmap) {
		Connection connection = DatabaseConnectionService.connectDb();
		try (PreparedStatement statement = connection.prepareStatement("insert into "
				+ "DEPARTMENT_DETAILS(dept_id, dept_name, manager_id) "
				+ "values(?,?,?)");
				Statement delStatement =connection.createStatement())
		{
			
			delStatement.executeQuery("TRUNCATE TABLE DEPARTMENT_DETAILS");
			
			//ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES");
			//TODO: check how to do batch update
			connection.setAutoCommit(false);
			for (Map.Entry<String, Department> entry : hmap.entrySet())
			{
				Department emp = entry.getValue();
				statement.setString(1, emp.getDeptId());
				statement.setString(2, emp.getName());
				statement.setString(3, emp.getDeptId());
				//statement.setDouble(4, emp.getSalary());
				statement.addBatch();
			}
			int count[] = statement.executeBatch();
			System.out.println("Total number of departments are -> " + count.length);
			connection.commit();
			//statement.close();
		} catch (SQLException e) {
			System.out.println("Exceptn while copying hashmap to db");
			e.printStackTrace();
		}
		
		//DatabaseConnectionService.closeConnection();
	}
}
