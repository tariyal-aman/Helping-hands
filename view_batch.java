package helping_hands;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
public class view_batch extends JFrame implements ActionListener
	{
		JFrame mainframe=new JFrame();
		JButton j1=new JButton("Add Rewards");
		JButton j2=new JButton("View Progress");
		JLabel heading=new JLabel("Welcome to HELPING-HANDS");
		JButton btnGoBack=new JButton("GoBack");
		
		//constructor
		view_batch(int user_id)
			{
				btnGoBack.setBounds(650,50,100,30);
				mainframe.add(btnGoBack);
				heading.setBounds(80,40,550,50);
				j1.setBounds(80,180,150,40);
				j2.setBounds(80,240,150,40);
				heading.setFont(new Font("Serif",Font.BOLD,35));
				mainframe.add(heading);
				mainframe.add(j1);
				mainframe.add(j2);
				j1.addActionListener(
						new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
								{	
										mainframe.dispose();
										new select_batch(user_id,1);				//second argument is to set the destination of insert_reward 
								}
						}
					);
				j2.addActionListener(
						new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
									{	
											mainframe.dispose();
											new select_batch(user_id,2);			//second argument is to set the destination of view_progress
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
											new home(user_id);
									}
							}
				);
			}
		
		//function to response buttons
		public void actionPerformed(ActionEvent e)
			{
					
			}
		public static void main(String []args)
			{
				//view_batch h=new view_batch();
			}
		
	}