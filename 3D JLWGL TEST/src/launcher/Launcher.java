package launcher;

import java.awt.Color;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;

import javax.swing.*;

import main.GameStates;
import main.GuiStates;
import main.Main;

public class Launcher extends JFrame {
	
	protected JPanel window = new JPanel();
	private JButton play, sandbox, update, about, quit;
	private Rectangle rplay, rsandbox, rupdate, rabout, rquit;
	
	protected int width = 240, height = 320;
	protected int buttonWidth = 80, buttonHeight = 40;
	
	private static boolean visibility = true;
	
	public Launcher(int id) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {}
		setTitle("3D Game Launcher");
		setSize(width, height);
		setResizable(false);
		getContentPane().add(window);
		setVisible(visibility);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		
		if(id == 0) {
			drawButtons();
		}
		repaint();
	}
	
	private void drawButtons() {
		play = new JButton("PLAY");
		sandbox = new JButton("SANDBOX");
		update = new JButton("UPDATE");
		about = new JButton("ABOUT");
		quit = new JButton("QUIT");
		
		rplay = new Rectangle(width / 2 - buttonWidth / 2, height * 1/6 - 35, buttonWidth, buttonHeight);
		play.setBounds(rplay);
		window.add(play);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GameStates.state = GameStates.state.LOADING;
				new Main();
			}
		});
		
		rsandbox = new Rectangle(width / 2 - buttonWidth / 2, height * 2/6 - 35, buttonWidth, buttonHeight);
		sandbox.setBounds(rsandbox);
		window.add(sandbox);
		sandbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GameStates.state = GameStates.state.GAME_SANDBOX;
				new Main();
			}
		});
		
		rupdate = new Rectangle(width / 2 - buttonWidth / 2, height * 3/6 - 35, buttonWidth, buttonHeight);
		update.setBounds(rupdate);
		window.add(update);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LauncherUpdate();
			}
		});
		
		rabout = new Rectangle(width / 2 - buttonWidth / 2, height * 4/6 - 35, buttonWidth, buttonHeight);
		about.setBounds(rabout);
		window.add(about);
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GuiStates.state = GuiStates.state.ABOUT;
				new Main();
			}
		});
		
		rquit = new Rectangle(width / 2 - buttonWidth / 2, height * 5/6 - 35, buttonWidth, buttonHeight);
		quit.setBounds(rquit);
		window.add(quit);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
