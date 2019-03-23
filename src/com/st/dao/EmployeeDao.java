/**
 * 
 */
package com.st.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.st.bean.Employee;

/**
 * @author chaturvp
 *
 */
public class EmployeeDao {

	@SuppressWarnings("unchecked")
	public Map<String, Employee> readFile() 
	{
		HashMap<String, Employee> hmap = new HashMap<String, Employee>();
		File f = new File("employeeData.ser");
		if(f.exists() && !f.isDirectory())
		{
	      try
	      {
	         FileInputStream fis = new FileInputStream("employeeData.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         hmap = (HashMap<String, Employee>) ois.readObject();
	         ois.close();
	         fis.close();
	         System.out.println("Size"+hmap.size());
	         return hmap;
	      }catch(IOException ioe)
	      {
	         ioe.printStackTrace();
	         return hmap;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Class not found");
	         c.printStackTrace();
	         return hmap;
	      }
		}
		return hmap;
	}

	public void writeFile(Map<String, Employee> employeeMap) {
		
		try
        {
               FileOutputStream fos =
                  new FileOutputStream("employeeData.ser",true);
        		System.out.println("FOS created"); 
               ObjectOutputStream oos = new ObjectOutputStream(fos);
        		System.out.println("OOs created"); 
               
               System.out.println("Number of employees"+employeeMap.size());
               
               oos.writeObject(employeeMap);
               oos.close();
               fos.close();
               System.out.printf("Serialized Employee data is saved in hashmap.ser");
        }catch(IOException ioe)
         {
               ioe.printStackTrace();
         }	
	}
		
}
	
	
