package com.nbcc;

import com.nbcc.DrawEnv;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class FinalProject
{
	public static void main (String[] args)
	{
		DrawEnv panel = new DrawEnv();
                
                Leaderboard leaderboard = new Leaderboard();
                leaderboard.setVisible(true);
                
		JFrame application = new JFrame("The Miraculous Quest of P. B. WitherBottom");
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.getContentPane().add("Center", new DrawEnv());		
		application.setSize(new Dimension(1300, 820)); // set the size of the frame
		application.setVisible(true); // make the frame visible
	}//end of main

	public static int jumpcount = 0;
}//end of class