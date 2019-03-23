package com.st.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.st.bean.Department;

public class DepartmentServiceDbDao {
	
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
			e.printStackTrace();
		}
		return false;
	}

	public static boolean addDept(Department department) {
		Connection connection = DatabaseConnectionService.connectDb();
		try(Statement statement = connection.createStatement();
				PreparedStatement preparedStatement2 = connection.prepareStatement("Insert into "
						+ "DEPARTMENT_DETAILS(dept_id, dept_name, manager_id) values(?,?,?)"))
		{
			if(depExists(department.getDeptId()))
			{
				System.out.println("Department already exist");
				return false;
			}
			
			preparedStatement2.setString(1, department.getDeptId());
			preparedStatement2.setString(2, department.getName());
			preparedStatement2.setString(3, department.getManagerName());
			preparedStatement2.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
		
		
		
	}

	public static boolean deleteDept(String id) {
		Connection connection = DatabaseConnectionService.connectDb();

		try(Statement statement = connection.createStatement())
		{
			
			ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM DEPARTMENT_DETAILS"
					+ " WHERE dept_id = '"+ id + "'");
			resultSet.next();
			if(! depExists(id))
			{
				System.out.println("Department does not exist");
				return false;
			}
			System.out.println("asas");
			statement.execute("Delete From DEPARTMENT_DETAILS where dept_id ='" +id+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean editDept(Department department) {
		Connection connection = DatabaseConnectionService.connectDb();
		try(Statement statement = connection.createStatement();
				PreparedStatement preparedStatement2 = connection.prepareStatement("Update "
						+ " DEPARTMENT_DETAILS SET "
						+ " dept_name = ? , manager_id = ? "
						+ " WHERE  dept_id = ? "))
		{
			preparedStatement2.setString(3, department.getDeptId());
			preparedStatement2.setString(1, department.getName());
			preparedStatement2.setString(2, department.getManagerName());
			preparedStatement2.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static Department getDepartment(Department department) {
		Connection connection = DatabaseConnectionService.connectDb();
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT "
				+ " dept_id, dept_name, manager_id "
				+ " FROM DEPARTMENT_DETAILS dep " 
				+ " WHERE  dept_id = ? "))
		{
			if(! depExists(department.getDeptId()))
			{
				return null;
			}
			preparedStatement.setString(1, department.getDeptId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				System.out.println("dsafjsdafj");
				System.out.println("Dept id: "+resultSet.getString("dept_id"));
				System.out.println("dept Name: "+resultSet.getString("dept_name"));
				System.out.println("manager id: "+resultSet.getString("manager_id"));
				department.setDeptId(resultSet.getString("dept_id"));
				department.setName(resultSet.getString("dept_name"));
				department.setManagerName(resultSet.getString("manager_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return department;
	}

	public static ArrayList<Department> getAllDepartment() {
		ArrayList<Department> arrayList = new ArrayList<Department>();
		Connection connection = DatabaseConnectionService.connectDb();
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT "
				+ " dept_id, dept_name, manager_id  "
				+ " FROM DEPARTMENT_DETAILS  " ))
		{
			
//			Nothing to be set
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Department department = new Department();
				department.setDeptId(resultSet.getString("dept_id"));
				department.setName(resultSet.getString("DEPT_NAME"));
				department.setManagerName(resultSet.getString("manager_id"));
				arrayList.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return arrayList;
	}

}
