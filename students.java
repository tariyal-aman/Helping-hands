package helping_hands;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import java.awt.Font;
public class students extends JFrame
{
		// JDBC driver name and database URL
	   	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   	static final String DB_URL = "jdbc:mysql://localhost/helping_hands";
	   
	   	//  Database credentials
	   	static final String USER = "root";
	   	static final String PASS = "";

		JFrame mainframe=new JFrame();
		
		//constructor for students class(parameter for batch id)
		students(int batch_id,int total_students,int user_id)
			{
				JLabel heading=new JLabel("Add Students Deatails");
				heading.setBounds(75,50,450,60);
				heading.setFont(new Font("Serif",Font.BOLD,35));
				int i;
				JLabel students[]=new JLabel[total_students];							//array of jlabels to print roll no.
				JTextField names[]=new JTextField[total_students];						//array of texfields to input names of each roll no. 
				for(i=0;i<total_students;++i)
					{
						students[i]=new JLabel("Roll no."+(i+1));
						students[i].setFont(new Font("Serif",Font.BOLD,25));
						students[i].setBounds(45,(150+(40*i)),120,20);
						names[i]=new JTextField();
						names[i].setBounds(250,(150+(40*i)),120,20);
						mainframe.add(names[i]);
						mainframe.add(students[i]);
					}
				JButton submit=new JButton("Submit");
				submit.setBounds(150,960,90,40);
				mainframe.add(submit);
				mainframe.add(heading);
				mainframe.setLayout(null);
				mainframe.setVisible(true);
				mainframe.setSize(1800,1800);
				submit.addActionListener(
						new ActionListener()
							{
									public void actionPerformed(ActionEvent e)
										{
											int i;
											Connection conn = null;
											Statement stmt = null;
											try
												{
													Class.forName("com.mysql.jdbc.Driver");
													conn = DriverManager.getConnection(DB_URL, USER, PASS);
													stmt = conn.createStatement();
													for(i=0;i<total_students;++i)
													{
														String sql = "INSERT INTO students(batch_id,roll,name) " +
										                   "VALUES ("+batch_id+",'"+(i+1)+"','"+names[i].getText()+"')";
														stmt.executeUpdate(sql);
													}
													mainframe.dispose();
													new home(user_id);
									     
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
										}
							}
				);
				mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		public static void main(String []args)
			{
				//students s=new students(1,3,4);
			}
}
