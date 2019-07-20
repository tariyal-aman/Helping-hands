package helping_hands;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class delete_batch
	{
		// JDBC driver name and database URL
	   	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   	static final String DB_URL = "jdbc:mysql://localhost/helping_hands";
	   
	   	//  Database credentials
	   	static final String USER = "root";
	   	static final String PASS = "";
   
		//constructor of  delete batch
		delete_batch(int batch_id,int user_id)
			{
				delete(batch_id,user_id);
			}
		
		//function to delete the batch
		void delete(int batch_id,int user_id)
				{
							Connection conn = null;
							Statement stmt = null;
							try
								{
									Class.forName("com.mysql.jdbc.Driver");
									conn = DriverManager.getConnection(DB_URL, USER, PASS);
									stmt = conn.createStatement();
									String sql = "DELETE from rewards where signup_id="+user_id+" AND batch_id="+batch_id;
									String sql1= "DELETE from students where batch_id="+batch_id;
									String sql2= "DELETE from batch where id="+batch_id;										
					         		stmt.executeUpdate(sql);
					         		stmt.executeUpdate(sql1);
					         		stmt.executeUpdate(sql2);
									//JOptionPane.showMessageDialog(this,"You have successfully signed in now you can login");
									new select_batch(user_id,3);
					     
								}
							catch(SQLException se)
								{
									se.printStackTrace();
								}
							catch(Exception e)
								{
									e.printStackTrace();
								}
							finally
							{
									try
										{
											if(stmt!=null)
												conn.close();
										}
									catch(SQLException se)
										{
										}
									try
									{
										if(conn!=null)
											conn.close();
									}
								catch(SQLException se)
										{
											se.printStackTrace();
										}
							}
				}
	}
