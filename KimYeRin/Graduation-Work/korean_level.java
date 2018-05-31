package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class korean_level extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public korean_level() {
		setTitle("Korean");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 558);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ImageIcon Icon1 = new ImageIcon(main_page.class.getResource("../images/korean_level1.png"));
	    Image originImg1 = Icon1.getImage(); 
	    Image changedImg1= originImg1.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
	    ImageIcon newIcon1 = new ImageIcon(changedImg1);
	    contentPane.setLayout(null);
	    JButton button1 = new JButton(newIcon1);
	    button1.setBounds(49, 196, 96, 101);
	    button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				dispose();
				new korean_level1().setVisible(true);
			}
		
		});
		contentPane.add(button1);
		
		
		ImageIcon Icon2 = new ImageIcon(main_page.class.getResource("../images/korean_level2.png"));
	    Image originImg2 = Icon2.getImage(); 
	    Image changedImg2 = originImg2.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
	    ImageIcon newIcon2 = new ImageIcon(changedImg2);
	    JButton button2 = new JButton(newIcon2);
	    button2.setBounds(188, 196, 96, 101);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				dispose();
				new korean_level2().setVisible(true);
			}
		
		});
		contentPane.add(button2);
		
		ImageIcon Icon3 = new ImageIcon(main_page.class.getResource("../images/korean_level3.png"));
	    Image originImg3 = Icon3.getImage(); 
	    Image changedImg3 = originImg3.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
	    ImageIcon newIcon3 = new ImageIcon(changedImg3);
		JButton button3 = new JButton(newIcon3);
		button3.setBounds(333, 196, 96, 101);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new korean_level3().setVisible(true);
			}
		});
		contentPane.add(button3);
		
		
		ImageIcon Icon4 = new ImageIcon(main_page.class.getResource("../images/korean_level4.png"));
	    Image originImg4 = Icon4.getImage(); 
	    Image changedImg4 = originImg4.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
	    ImageIcon newIcon4 = new ImageIcon(changedImg4);
		JButton button4 = new JButton(newIcon4);
		button4.setBounds(474, 196, 96, 101);
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new korean_level4().setVisible(true);
			}
		});
		contentPane.add(button4);
		
		
		ImageIcon Icon5 = new ImageIcon(main_page.class.getResource("../images/korean_level5.png"));
	    Image originImg5 = Icon5.getImage(); 
	    Image changedImg5 = originImg5.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
	    ImageIcon newIcon5 = new ImageIcon(changedImg5);
		JButton button5 = new JButton(newIcon5);
		button5.setBounds(616, 196, 96, 101);
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new korean_level5().setVisible(true);
			}
		});
		contentPane.add(button5);
		
		ImageIcon back_icon = new ImageIcon(main_page.class.getResource("../images/back.png"));
	    Image originImg6 = back_icon.getImage(); 
	    Image changedImg6 = originImg6.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
	    ImageIcon newIcon6 = new ImageIcon(changedImg6);
		JButton back = new JButton(newIcon6);
		back.setBounds(0, 0, 52, 51);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new main_page().setVisible(true);
			}
		});
		contentPane.add(back);
		
		ImageIcon Icon7 = new ImageIcon(main_page.class.getResource("../images/exit.png"));
	    Image originImg7 = Icon7.getImage(); 
	    Image changedImg7 = originImg7.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
	    ImageIcon newIcon7 = new ImageIcon(changedImg7);
		JButton exit = new JButton(newIcon7);
		exit.setBounds(51, 0, 52, 51);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(exit);
		
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(main_page.class.getResource("../images/korean_background.png")));
		lblNewLabel.setBounds(0, 0, 772, 516);
		contentPane.add(lblNewLabel);
			
	}
}