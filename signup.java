package helping_hands;
import javax.swing.*;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class signup extends JFrame implements ActionListener
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
			JTextField name=new JTextField(30);							//name field
			JButton submit=new JButton("SIGNUP");						//signup button
			JButton login_link =new JButton("Already have account");
			
			//constructor for intializing some basic values
			signup()
				{
						JLabel heading=new JLabel("SIGNUP to HELPING-HANDS");
						heading.setBounds(80,40,520,50);
						heading.setFont(new Font("Serif", Font.BOLD, 35));			//for increasing font size
						mainframe.setSize(800,800);
						mainframe.setLayout(null);
						mainframe.setVisible(true);
						mainframe.add(heading);
						inputDetails();
				}
			
			//md5 encryptor
			   public static String getMd5(String input) 
			    { 
			        try
			        	{ 
			  
					            // Static getInstance method is called with hashing MD5 
					            MessageDigest md = MessageDigest.getInstance("MD5"); 
					  
					            // digest() method is called to calculate message digest 
					            //  of an input digest() return array of byte 
					            byte[] messageDigest = md.digest(input.getBytes()); 
					  
					            // Convert byte array into signum representation 
					            BigInteger no = new BigInteger(1, messageDigest); 
					  
					            // Convert message digest into hex value 
					            String hashtext = no.toString(16); 
					            while (hashtext.length() < 32)
						            { 
					            			hashtext = "0" + hashtext; 
						            } 
					            return hashtext; 
			        	}  
			  
			        // For specifying wrong message digest algorithms 
			        catch (NoSuchAlgorithmException e) 
				        { 
			        			throw new RuntimeException(e); 
				        } 
			    }
			   
			//function to add details in Database
			void addDetailsToDB(String name,String username,String password)
				{
						password=getMd5(password);
						Connection conn = null;
						Statement stmt = null;
						try
							{
								Class.forName("com.mysql.jdbc.Driver");
								conn = DriverManager.getConnection(DB_URL, USER, PASS);
								stmt = conn.createStatement();
								String sql = "INSERT INTO signup(name,username,password) " +
				                   "VALUES ('"+name+"','"+username+"','"+password+"')";
								stmt.executeUpdate(sql);
								JOptionPane.showMessageDialog(this,"You have succesfully signed in now you can login");
								mainframe.dispose();
								new login();
				     
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
			void inputDetails()
				{
						JLabel username_label=new JLabel("username:");						//username  label
						JLabel password_label=new JLabel("password:");						//password label
						JLabel name_label=new JLabel("Name:");									//name label
						login_link.setBounds(550,300,160,30);
						username.setBounds(220,150,180,40);
						name_label.setBounds(90,100,180,40);
						username_label.setBounds(40,150,180,40);
						username_label.setFont(new Font("Serif", Font.BOLD, 35));			//for increasing font size
						password_label.setBounds(40,220,180,40);
						name_label.setFont(new Font("Serif", Font.BOLD, 35));
						password_label.setFont(new Font("Serif", Font.BOLD, 35));
						password.setBounds(220,220,180,40);
						name.setBounds(220,100,180,40);
						submit.setBounds(250,300,80,30);
						mainframe.add(password_label);
						mainframe.add(submit);
						mainframe.add(username_label);
						mainframe.add(password);
						mainframe.add(username);
						mainframe.add(name_label);
						mainframe.add(name);
						mainframe.add(login_link);
						submit.addActionListener(this);
						login_link.addActionListener(											//overiding action listener
								new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
											{
												mainframe.dispose();
												new login();
											}
									}
								
								);
						
				}
			public void actionPerformed(ActionEvent e)
				{
					String entr_name,entr_username,entr_password;
					entr_name=name.getText();
					entr_username=username.getText();
					entr_password=password.getText();
					addDetailsToDB(entr_name,entr_username,entr_password);
				}
			public static void main(String []args)
				{
					//signup s=new signup();
				}
	}
