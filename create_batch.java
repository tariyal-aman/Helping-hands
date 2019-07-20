package helping_hands;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
public class create_batch extends JFrame implements ActionListener
	{	
		// JDBC driver name and database URL
	   	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   	static final String DB_URL = "jdbc:mysql://localhost/helping_hands";
	   
	   	//  Database credentials
	   	static final String USER = "root";
	   	static final String PASS = "";
	   
		JFrame mainframe =new JFrame();
		JButton submit=new JButton("Submit");
		JLabel heading=new JLabel("Enter Details of batch");
		JTextField course=new JTextField();
		JTextField semester=new JTextField();
		JTextField section=new JTextField();
		JTextField total_stud=new JTextField();
		JButton btnGoBack=new JButton("GoBack");									//button to go back to last frame
		
		//constructor to initialize basics
		create_batch(int user_id)
			{
					btnGoBack.setBounds(650,50,100,30);
					mainframe.add(btnGoBack);
					JLabel course_label=new JLabel("Course:");					
					JLabel sem_label=new JLabel("Semester:");
					JLabel sec_label=new JLabel("Section:");
					JLabel total_stud_label=new JLabel("Toal Students:");
					course_label.setBounds(80,120,180,60);								//boundary setting
					sem_label.setBounds(80,200,180,60);
					sec_label.setBounds(80,280,180,60);
					total_stud_label.setBounds(80,350,280,60);
					course.setBounds(350,135,120,30);
					semester.setBounds(350,210,120,30);
					section.setBounds(350,290,120,30);
					total_stud.setBounds(350,360,120,30);
					course_label.setFont(new Font("Serif",Font.BOLD,35));				//font setting
					sec_label.setFont(new Font("Serif",Font.BOLD,35));
					sem_label.setFont(new Font("Serif",Font.BOLD,35));
					total_stud_label.setFont(new Font("Serif",Font.BOLD,35));
					mainframe.add(course);
					mainframe.add(semester);
					mainframe.add(section);
					mainframe.add(total_stud);
					mainframe.add(sem_label);
					mainframe.add(sec_label);
					mainframe.add(total_stud_label);
					mainframe.add(course_label);
					submit.setBounds(360,450,80,30);
					heading.setBounds(80,40,550,50);
					heading.setFont(new Font("Serif",Font.BOLD,35));
					mainframe.add(heading);
					mainframe.add(submit);
					mainframe.setSize(800,800);
					mainframe.setLayout(null);
					mainframe.setVisible(true);
					mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);							//to activate close button
					submit.addActionListener(
							new ActionListener()
								{
									public void actionPerformed(ActionEvent e)
										{
												String entr_course,entr_semester,entr_section,entr_total_stud;
												entr_course=course.getText();
												entr_semester=semester.getText();
												entr_section=section.getText();
												entr_total_stud=total_stud.getText();
												int total_stud=Integer.parseInt(entr_total_stud);
												int batch_id=addDetailsToDB(entr_course,entr_semester,entr_section,entr_total_stud,user_id);
												System.out.println(batch_id);
												if(batch_id!=0)
													{
														mainframe.dispose();
														new students(batch_id,total_stud,user_id);
													}
										}
								}
							);
					btnGoBack.addActionListener(
							new ActionListener()
								{
									public void actionPerformed(ActionEvent e)
										{
											mainframe.dispose();
											new home(user_id);
										}
								}
						);
			}
		
		//function to save details in database 
		int addDetailsToDB(String course,String semester,String section,String total_stud,int user_id)
			{
					Connection conn = null;
					Statement stmt = null;
					try
						{
							Class.forName("com.mysql.jdbc.Driver");
							conn = DriverManager.getConnection(DB_URL, USER, PASS);
							stmt = conn.createStatement();
							String sql = "INSERT INTO batch(signup_id,course,sem,sec,total_stud) " +
			                   "VALUES ("+user_id+",'"+course+"',"+semester+",'"+section+"',"+total_stud+")";
							stmt.executeUpdate(sql);
							sql="select max(id) as mid from batch";											//to get the latest batch_id from DB
							ResultSet rs = stmt.executeQuery(sql);
							rs.next();
							int batch_id  = rs.getInt("mid");
							mainframe.dispose();
							return batch_id;
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
					return 0;
			}
		
		//function to response submit button
				public void actionPerformed(ActionEvent e)
					{
					
					}
				
		public static void main(String []args)
			{
				//create_batch c=new create_batch(2);
			}
	}
