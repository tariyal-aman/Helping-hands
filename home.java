package helping_hands;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
public class home extends JFrame implements ActionListener
	{
		JFrame mainframe=new JFrame();
		JButton btnCreateBatch=new JButton("Create-Batch");
		JButton btnDashBoard=new JButton("DashBoard");
		JButton btnDeleteBatch=new JButton("Delete-Batch");
		JLabel heading=new JLabel("Welcome to HELPING-HANDS");
		JButton btnLogout=new JButton("Logout");								//button to logout of account
		
		//constructor
		home(int user_id)
			{
				btnLogout.setBounds(650,10,120,30);
				mainframe.add(btnLogout);
				heading.setBounds(80,40,550,50);
				btnCreateBatch.setBounds(80,180,150,40);
				btnDashBoard.setBounds(80,240,150,40);
				btnDeleteBatch.setBounds(80,300,150,40);
				heading.setFont(new Font("Serif",Font.BOLD,35));
				mainframe.add(heading);
				mainframe.add(btnCreateBatch);
				mainframe.add(btnDashBoard);
				mainframe.add(btnDeleteBatch);
				btnCreateBatch.addActionListener(this);									//for response of create batch button
				btnDashBoard.addActionListener(										//for response of select batch button
						new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
									{	
											mainframe.dispose();
											new view_batch(user_id);
									}
							}
						);
				btnDeleteBatch.addActionListener(
								new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
											{
												mainframe.dispose();
												new select_batch(user_id,3);
											}
									}
						);
			btnCreateBatch.addActionListener(
						new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
									{
										mainframe.dispose();
										new create_batch(user_id);
									}
							}
				);
				mainframe.setSize(800,800);
				mainframe.setLayout(null);
				mainframe.setVisible(true);
				btnLogout.addActionListener(
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
		
		//function to response submit buttons
				public void actionPerformed(ActionEvent e)
					{
					
					}
		public static void main(String []args)
			{
				//home h=new home(4);
			}
		
	}
