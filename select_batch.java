package helping_hands;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.Font;
public class select_batch extends JFrame implements ActionListener
	{
			// JDBC driver name and database URL
		   	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   	static final String DB_URL = "jdbc:mysql://localhost/helping_hands";
		   
		   	//  Database credentials
		   	static final String USER = "root";
		   	static final String PASS = "";
			JFrame mainframe=new JFrame();
			JButton btnGoBack=new JButton("GoBack");									//button to go back to last frame
			JButton btnLogout=new JButton("Logout");								//button to logout of account
			
			//constructor to initialize basic details
			select_batch(int user_id,int choice)									//choice variable is to set the destination of this page 
				{
					btnLogout.setBounds(650,10,120,30);
					mainframe.add(btnLogout);
					btnGoBack.setBounds(650,50,100,30);
					mainframe.add(btnGoBack);
					JLabel lblHeading=new JLabel("Select Batch");
					lblHeading.setBounds(10,20,200,40);
					lblHeading.setFont(new Font("SERIF",Font.BOLD,35));
					mainframe.add(lblHeading);
					Connection conn=null;
					Statement stmt=null;
					try
						{
							      Class.forName("com.mysql.jdbc.Driver");
							      conn = DriverManager.getConnection(DB_URL, USER, PASS);
							      stmt = conn.createStatement();
							      String sql = "SELECT * from batch where signup_id="+user_id;
							      ResultSet rs = stmt.executeQuery(sql);
							      JButton btnBatches[]=new JButton[100];
							      int i=1;
							      while(rs.next())
							      {
									         int id  = rs.getInt("id");
									         String course = rs.getString("course");
									         String semester = rs.getString("sem");
									         String section = rs.getString("sec");
									         btnBatches[id]=new JButton(course+"-"+semester+"-"+section); 
									         btnBatches[id].setBounds(50,60+(40*i+10),110,30);
									         mainframe.add(btnBatches[id]);
									         btnBatches[id].addActionListener(
									        		 		new ActionListener()
									        		 			{
									        		 				public void actionPerformed(ActionEvent e)
									        		 					{
									        		 							
									        		 							if(choice==1)
									        		 								{
									        		 										mainframe.dispose();
									        		 										new insert_rewards(id,user_id);
									        		 								}
									        		 							else if(choice==2)
									        		 								{
									        		 										mainframe.dispose();	
									        		 										new view_progress(id,user_id);
									        		 								}
									        		 							else if(choice==3)
									        		 								{
									        		 									int dialogButton = JOptionPane.YES_NO_OPTION;
									        		 									int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to delete this batch(this will delete every information )?","Warning",dialogButton);
									        		 									if(dialogResult == JOptionPane.YES_OPTION)
										        		 									{
									        		 												mainframe.dispose();
									        		 												new delete_batch(id,user_id);
										        		 									}
									        		 								}
									        		 					}
									        		 			}
									        		 );
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
					mainframe.setSize(800,800);
					mainframe.setLayout(null);
					mainframe.setVisible(true);
					btnGoBack.addActionListener(
								new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
											{
														if(choice==1)
			    		 								{
															mainframe.dispose();
															new view_batch(user_id);	
			    		 								}
			    		 							else if(choice==2)
			    		 								{
				    		 								mainframe.dispose();
															new view_batch(user_id);	
			    		 								}
			    		 							else if(choice==3)
			    		 								{
				    		 								mainframe.dispose();
															new home(user_id);
			    		 								}
											}
									}
							);
					
				}
			public static void main(String []args)
				{
					//add_rewards a=new add_rewards(2);
				}
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				
			}
	}
