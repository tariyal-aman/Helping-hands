package helping_hands;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
public class view_progress extends JFrame
	{
			// JDBC driver name and database URL
		   	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   	static final String DB_URL = "jdbc:mysql://localhost/helping_hands";
		   
		   	//  Database credentials
		   	static final String USER = "root";
		   	static final String PASS = "";
		   	
			JFrame mainframe=new JFrame();
			JButton btnGoBack=new JButton("GoBack");									//button to go back to last frame
			//constructors to initialize basic details
			view_progress(int batch_id,int user_id)
				{
						btnGoBack.setBounds(650,50,100,30);
						mainframe.add(btnGoBack);
						printBatchDetails(batch_id);
						JLabel lblStudentName=new JLabel("Name");						//head label of name column
						JLabel lblScoredMarks =new JLabel("Marks Scored");				//head label of marks scored label
						lblScoredMarks.setBounds(320,180,80,30);
						lblStudentName.setBounds(100,180,80,30);
						printStudentsMarks(batch_id,user_id);
						mainframe.add(lblScoredMarks);
						mainframe.add(lblStudentName);
						mainframe.setSize(800,800);
						mainframe.setLayout(null);
						mainframe.setVisible(true);
						btnGoBack.addActionListener(
									new ActionListener()
										{
											public void actionPerformed(ActionEvent e)
												{
														mainframe.dispose();
														new select_batch(user_id,2);
												}
										}
								);
				}
			
			//function to print marks of students
			void printStudentsMarks(int batch_id,int user_id)
				{				
									int marks[][]=new int[2][100],i=0,totalMarks=0;						//array to store student id with total marks
									String studentsNames[]=new String[100];
									Connection conn=null;
									Statement stmt=null;
									try
										{
											      Class.forName("com.mysql.jdbc.Driver");
											      conn = DriverManager.getConnection(DB_URL, USER, PASS);
											      stmt = conn.createStatement();
											      String sql = "SELECT id,name from students where batch_id="+batch_id;
											      ResultSet rs = stmt.executeQuery(sql);
											      i=0;
											      while(rs.next())
											      {
											    	         int id  = rs.getInt("id");
													         studentsNames[i]=rs.getString("name");
													         marks[0][i]=id;
													         ++i;
											      }
											      rs.close();
											      
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
														 }// do nothing
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
									try
										{
											      Class.forName("com.mysql.jdbc.Driver");
											      conn = DriverManager.getConnection(DB_URL, USER, PASS);
											      stmt = conn.createStatement();
											      int j,k=0;
											      for(j=0;j<i;++j)
											      	{
														      String  sql="SELECT sum(rewards) as sumMarks,sum(total_rewards) as totalMarks from rewards where batch_id="+batch_id+" AND std_id="+marks[0][j]+" AND signup_id="+user_id;	    
														      ResultSet rs = stmt.executeQuery(sql);
														      while(rs.next())
														      {
																         marks[1][k]=rs.getInt("sumMarks");
																         totalMarks=rs.getInt("totalMarks");
																         ++k;
															 }
														      rs.close();
											      	}
											      
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
														 }// do nothing
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
						int j;
						JLabel lblTotalMarks=new JLabel("Total MArks - "+totalMarks);
						lblTotalMarks.setBounds(400,180,150,30);
						mainframe.add(lblTotalMarks);
						JLabel lblStudentsNames[]=new JLabel[i];							//label to print names of students
						JLabel lblStudentsMarks[]=new JLabel[i];							//label to print marks of students
						for(j=0;j<i;++j)
							{
								lblStudentsNames[j]=new JLabel(studentsNames[j]);
								lblStudentsMarks[j]=new JLabel(""+marks[1][j]);
								lblStudentsNames[j].setBounds(100,200+(j*30+10),150,30);
								lblStudentsMarks[j].setBounds(320,200+(j*30+10),150,30);
								mainframe.add(lblStudentsNames[j]);
								mainframe.add(lblStudentsMarks[j]);
								
							}
				}
			
			//function to print details of batch
			void printBatchDetails(int batch_id)
				{
								Connection conn=null;
								Statement stmt=null;
								try
									{
										      Class.forName("com.mysql.jdbc.Driver");
										      conn = DriverManager.getConnection(DB_URL, USER, PASS);
										      stmt = conn.createStatement();
										      String sql = "SELECT * from batch where id="+batch_id;
										      ResultSet rs = stmt.executeQuery(sql);
										      int i=1;
										      while(rs.next())
										      {
												         int id  = rs.getInt("id");
												         int total_students=Integer.parseInt(rs.getString("total_stud"));
												         String course = rs.getString("course");
												         String semester = rs.getString("sem");
												         String section = rs.getString("sec");
												         JLabel lblheading=new JLabel("Batch Details");
												         JLabel lblcourse=new JLabel("Course = "+course);
												         JLabel lblsemester=new JLabel("Semester = "+semester);
												         JLabel lblsection=new JLabel("Section = "+section);
												         JLabel lbltotal_stud=new JLabel("Total Students = "+total_students);
												         lblheading.setBounds(100,10,150,30);
												         lblcourse.setBounds(100,50,150,30);
												         lblsemester.setBounds(300,50,150,30);
												         lblsection.setBounds(100,80,150,30);
												         lbltotal_stud.setBounds(300,80,150,30);
												         mainframe.add(lblcourse);
												         mainframe.add(lblsection);
												         mainframe.add(lblsemester);
												         mainframe.add(lbltotal_stud);
												         mainframe.add(lblheading);
										      }
										      rs.close();
										      
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
													 }// do nothing
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
			public static void main(String []args)
				{
						//view_progress s=new view_progress(9,4);
				}
	}
