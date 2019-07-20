package helping_hands;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class insert_rewards extends JFrame implements ActionListener 
	{
			// JDBC driver name and database URL
		   	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   	static final String DB_URL = "jdbc:mysql://localhost/helping_hands";
		   
		   	//  Database credentials
		   	static final String USER = "root";
		   	static final String PASS = "";
		    int id[]=new int[100],i;
		    JFrame mainframe=new JFrame();
		    JTextField txtMarks[]=new JTextField[100];							//text fields to store marks of students
			JTextField txtTotalMarks=new JTextField(10);							//text field to store total marks
			JButton btnGoBack=new JButton("GoBack");									//button to go back to last frame
			
			//constructor to initialize basic variables
			insert_rewards(int batch_id,int user_id)
				{
								  btnGoBack.setBounds(650,50,100,30);
								  mainframe.add(btnGoBack);
								  JLabel lblTotalMarks=new JLabel("Enter Total Marks");
								  JButton btnSubmitMarks=new JButton("Submit");
								  btnSubmitMarks.setBounds(480,50,120,30);
								  mainframe.add(btnSubmitMarks);
								  JLabel lblRoll,lblName,lblMarks;
								  lblRoll=new JLabel("Roll Number");
								  lblName=new JLabel("Name");
								  lblMarks=new JLabel("Marks");
								  lblRoll.setBounds(100,150,100,30);
								  lblName.setBounds(240,150,60,30);
								  lblMarks.setBounds(350,150,60,30);
								  mainframe.add(lblRoll);
								  mainframe.add(lblMarks);
								  mainframe.add(lblName);
								  txtTotalMarks.setBounds(350,70,90,40);
								  mainframe.add(txtTotalMarks);
								  lblTotalMarks.setBounds(10,70,500,40);
								  lblTotalMarks.setFont(new Font("SERIF",Font.BOLD,35));
								  mainframe.add(lblTotalMarks);
								  Connection conn=null;
								  Statement stmt=null;
								  try
								  {
								  Class.forName("com.mysql.jdbc.Driver");
							      conn = DriverManager.getConnection(DB_URL, USER, PASS);
							      stmt = conn.createStatement();
							      String sql = "SELECT * from students where batch_id="+batch_id;
							      ResultSet rs = stmt.executeQuery(sql);
							      JLabel lblroll,lblname;
							      while(rs.next())
							      {
									          id[i]  = rs.getInt("id");
									         String name = rs.getString("name");
									         int roll=Integer.parseInt(rs.getString("roll"));
									         lblroll=new JLabel(" "+roll);
									         lblname=new JLabel(name);
									         lblroll.setBounds(120,160+(40*(i+1)+10),80,30);
									         lblname.setBounds(220,160+(40*(i+1)+10),80,30);
									         mainframe.add(lblroll);
									         mainframe.add(lblname);
									         txtMarks[id[i]]=new JTextField(10);
									         txtMarks[id[i]].setBounds(330,160+(40*(i+1)+10),80,30);
									         mainframe.add(txtMarks[id[i]]);
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
								  btnSubmitMarks.addActionListener(								//function to response button(to add rewards in Database)
										  new ActionListener()
										  		{
											  			public void actionPerformed(ActionEvent e)	
											  				{	
											  						int j=0;
											  						boolean flag=false;
													  				for(j=0;j<i;++j)
																	{
													  					if(storeMarksToDB(i,j,user_id,batch_id))
													  						{
													  							flag=true;
													  						}
													  					else
													  						{
													  							flag=false;
													  						}
																	}
													  				if(flag)
													  					{
														  					printMessage("Marks Added Successfully");
												  							mainframe.dispose();
												  							new home(user_id);
													  					}
											  				}
										  		}
										  );
					mainframe.setSize(800,800);
					mainframe.setLayout(null);
					mainframe.setVisible(true);
					btnGoBack.addActionListener(
								new ActionListener()
										{
												public void actionPerformed(ActionEvent e)
													{
															mainframe.dispose();
															new select_batch(user_id,1);
													}
										}
							);
				}
			
			//function to print desirable message
			void printMessage(String message)
				{
					JOptionPane.showMessageDialog(this,message);
				}
			
			//function to store marks in Database
			boolean storeMarksToDB(int i,int j,int user_id,int batch_id)
				{
							Connection conn = null;
							Statement stmt = null;	
								try
									{
											Class.forName("com.mysql.jdbc.Driver");
											conn = DriverManager.getConnection(DB_URL, USER, PASS);
											stmt = conn.createStatement();
											String sql = "INSERT INTO rewards(signup_id,batch_id,std_id,rewards,total_rewards) " +
							                   "VALUES ("+user_id+",'"+batch_id+"',"+id[j]+",'"+txtMarks[id[j]].getText()+"',"+txtTotalMarks.getText()+")";
											stmt.executeUpdate(sql);				
											mainframe.dispose();
											return true;
										
									}
								catch(SQLException se)
									{
										se.printStackTrace();
									}
								catch(Exception ee)
									{
										ee.printStackTrace();
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
							
							return false;
				}
			
			//function to respond buttons
			public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					
				}
			public static void main(String []args)
				{
					//insert_rewards c=new insert_rewards(9,4);
				}
			
	}
