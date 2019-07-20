package helping_hands;
import javax.swing.*;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import java.awt.event.*;
//login frame of project
public class login extends JFrame implements ActionListener
	{
	
			// JDBC driver name and database URL
		   	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   	static final String DB_URL = "jdbc:mysql://localhost/helping_hands";
		   
		   	//  Database credentials
		   	static final String USER = "root";
		   	static final String PASS = "";
			JFrame mainframe=new JFrame();
			JTextField username=new JTextField(50);					//text field for username
			JPasswordField password=new  JPasswordField(20);			// password field for password
			JButton submit=new JButton("LOGIN");						//login button
			JButton signup_link=new JButton("Create Account");
			
			//md5 encryption
			   public static String getMd5(String input) 
			    { 
			        try { 
			  
			            // Static getInstance method is called with hashing MD5 
			            MessageDigest md = MessageDigest.getInstance("MD5"); 
			  
			            // digest() method is called to calculate message digest 
			            //  of an input digest() return array of byte 
			            byte[] messageDigest = md.digest(input.getBytes()); 
			  
			            // Convert byte array into signum representation 
			            BigInteger no = new BigInteger(1, messageDigest); 
			  
			            // Convert message digest into hex value 
			            String hashtext = no.toString(16); 
			            while (hashtext.length() < 32) { 
			                hashtext = "0" + hashtext; 
			            } 
			            return hashtext; 
			        }  
			  
			        // For specifying wrong message digest algorithms 
			        catch (NoSuchAlgorithmException e) { 
			            throw new RuntimeException(e); 
			        } 
			    }
			//constructor for intializing basic terms
			login() 
				{
						JLabel heading=new JLabel("Login to HELPING-HAND",JLabel.CENTER);
						heading.setBounds(80,60,800,80);
						heading.setFont(new Font("Serif", Font.BOLD, 45));
						mainframe.add(heading);
						mainframe.setSize(800,800);
						mainframe.setLayout(null);
						mainframe.setVisible(true);
						mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						inputDetails();											
				}
			
			//function to check for valid username and password
			int check(String username,String password)
				{
					password=getMd5(password);									//to convert password into md5 encryption
					Connection conn=null;
					Statement stmt=null;
					try
						{
							      Class.forName("com.mysql.jdbc.Driver");
							      conn = DriverManager.getConnection(DB_URL, USER, PASS);
							      stmt = conn.createStatement();
							      String sql = "SELECT * from signup";
							      ResultSet rs = stmt.executeQuery(sql);
							      while(rs.next())
							      {
									         int sno  = rs.getInt("id");
									         String user = rs.getString("username");
									         String pass = rs.getString("password");
									         if(user.equals(username)&&pass.contentEquals(password))
									         	{
									        	 	return sno;
									         	}
							      }
							      rs.close();
							      return 0;
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
					return 0;
				}
			
			//function to input details of user i.e username and password
			void inputDetails()				
				{
						JLabel developer_details=new JLabel("Developed by - AMAN TARIYAL(tariyalaman39@gmail.com)");
						developer_details.setBounds(420,680,400,40);
						mainframe.add(developer_details);
						JLabel username_label=new JLabel("Username:");						//username  label
						JLabel password_label=new JLabel("Password:");						//password label
						username.setBounds(220,150,180,40);
						signup_link.setBounds(600,300,150,30);
						username_label.setBounds(40,150,180,40);
						username_label.setFont(new Font("Serif", Font.BOLD, 35));			//for increasing font size
						password_label.setBounds(40,220,180,40);
						password_label.setFont(new Font("Serif", Font.BOLD, 35));
						password.setBounds(220,220,180,40);
						submit.setBounds(250,300,80,30);
						mainframe.add(password_label);
						mainframe.add(submit);
						mainframe.add(username_label);
						mainframe.add(password);
						mainframe.add(username);
						mainframe.add(signup_link);
						submit.addActionListener(this);
						signup_link.addActionListener(										//overriding action listener
								new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
											{
												 mainframe.dispose();						//closing older frame
												signup s=	 new signup();								//opening new frame
												 
											}
									}
								);
				}
			
				//function to response submit button
				public void actionPerformed(ActionEvent e)
					{
						String entr_username,entr_password;									//to store the values which is entered
						entr_username=username.getText();
						entr_password=password.getText();
						int sno=check(entr_username,entr_password);							//user id
						if(sno!=0)
							{
								JOptionPane.showMessageDialog(this,"Your have successfully logged in");
								mainframe.dispose();
								home h=new home(sno);
//								new home(sno);
							}
						else
							{
								JOptionPane.showMessageDialog(this,"Please Enter valid  username and password");
							}
					}
			public static void main(String []args)
				{
					login l=new login();
				}
	}
