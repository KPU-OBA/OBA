package main;

import gui.english_level4;

import java.awt.EventQueue;

public class main {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//main_page frame = new main_page();
					english_level4 frame = new english_level4();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
}
