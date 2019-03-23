package com.st.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.st.bean.Department;

public class DepartmentDao {
	@SuppressWarnings("unchecked")
	public Map<String, Department> readFile() 
	{
		HashMap<String, Department> hmap = new HashMap<String, Department>();
		File f = new File("departmentData.ser");
		if(f.exists() && !f.isDirectory())
		{
	      try
	      {
	         FileInputStream fis = new FileInputStream("departmentData.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         hmap = (HashMap<String, Department>) ois.readObject();
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

	public void writeFile(Map<String, Department> DepartmentMap) {
		
		try
        {
               FileOutputStream fos =
                  new FileOutputStream("departmentData.ser",true);
        		System.out.println("FOS created"); 
               ObjectOutputStream oos = new ObjectOutputStream(fos);
        		System.out.println("OOs created"); 
               
               System.out.println("Number of Departments"+DepartmentMap.size());
               
               oos.writeObject(DepartmentMap);
               oos.close();
               fos.close();
               System.out.printf("Serialized Department data is saved in departmentData.ser");
        }catch(IOException ioe)
         {
               ioe.printStackTrace();
         }	
	}
}
