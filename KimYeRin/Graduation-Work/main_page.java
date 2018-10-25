package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.Icon;
import java.awt.Toolkit;

public class main_page extends JFrame {

	/**
	 * Create the frame.
	 */
	public main_page() {
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setBounds(300, 100, 788, 558);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ImageIcon Icon1 = new ImageIcon(main_page.class.getResource("../images/korean.png"));
	    Image originImg1 = Icon1.getImage(); 
	    Image changedImg1= originImg1.getScaledInstance(150, 150, Image.SCALE_SMOOTH );
	    ImageIcon newIcon1 = new ImageIcon(changedImg1);
	    contentPane.setLayout(null);
	    JButton button1 = new JButton(Icon1);
	    button1.setBounds(300, 150, 125, 170);
	    button1.setBorderPainted(false);
	    button1.setContentAreaFilled(false);
	    button1.setFocusPainted(false);
	    button1.setOpaque(false);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new korean_level().setVisible(true);
			}
	
		});
		contentPane.add(button1);
		
		
	
		ImageIcon Icon2 = new ImageIcon(main_page.class.getResource("../images/english.png"));
	    Image originImg2 = Icon2.getImage(); 
	    Image changedImg2 = originImg2.getScaledInstance(250, 250, Image.SCALE_SMOOTH );
	    ImageIcon newIcon2 = new ImageIcon(changedImg2);
	    contentPane.setLayout(null);
	    JButton button2 = new JButton(Icon2);
	    button2.setBounds(100, 150, 130, 250 );
	    button2.setBorderPainted(false);
	    button2.setContentAreaFilled(false);
	    button2.setFocusPainted(false);
	    button2.setOpaque(false);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				dispose();
				new english_level().setVisible(true);
			}
		});
		contentPane.add(button2);
		
		ImageIcon Icon3 = new ImageIcon(main_page.class.getResource("../images/math.png"));
	    Image originImg3 = Icon3.getImage(); 
	    Image changedImg3 = originImg3.getScaledInstance(200, 200, Image.SCALE_SMOOTH );
	    ImageIcon newIcon3 = new ImageIcon(changedImg3);
	    contentPane.setLayout(null);
		JButton button3 = new JButton(Icon3);
		button3.setBounds(500, 150, 125, 175);
		button3.setBorderPainted(false);
	    button3.setContentAreaFilled(false);
	    button3.setFocusPainted(false);
	    button3.setOpaque(false);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new math_level().setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(button3);
		
		ImageIcon Icon7 = new ImageIcon(main_page.class.getResource("../images/exit.png"));
	    Image originImg7 = Icon7.getImage(); 
	    Image changedImg7 = originImg7.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
	    ImageIcon newIcon7 = new ImageIcon(changedImg7);
	    contentPane.setLayout(null);
	    JButton exit = new JButton(Icon7);
		exit.setBounds(51, 0, 52, 51);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.setOpaque(false);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(exit);
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(main_page.class.getResource("../images/background.png"))); 
		lblNewLabel.setBounds(0, 0, 772, 516);

		contentPane.add(lblNewLabel);
		
	}
	
	public void paint(Graphics g) {
		// System.out.println(toggle);
		// backGround
		Image backImage = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/background.png"));
		g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);

		// exit 버튼
		Image btn_exit = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/exit.png"));
		g.drawImage(btn_exit, 51, 0, 52, 51, this);

		

		/*
		 * * korean 버튼
		 */
		Image btn_korean = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/korean.png"));
		g.drawImage(btn_korean, 310, 180, 125, 170, this);
		
		
		/*
		 * * english 버튼
		 */
		Image btn_english = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/english.png"));
		g.drawImage(btn_english, 100, 200, 130, 250, this);


		/*
		 * * math 버튼
		 */
		Image btn_math = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/math.png"));
		g.drawImage(btn_math, 500, 200, 125, 175, this);
	}

}
