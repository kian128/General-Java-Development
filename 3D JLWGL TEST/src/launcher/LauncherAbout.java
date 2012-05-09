package launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.Main;

public class LauncherAbout extends Launcher {
	
	Graphics g = this.getGraphics();
	
	private JButton back;
	private Rectangle rback;

	public LauncherAbout() {
		super(1);
		setTitle("About");
		drawButtons();
		paint(g);
	}
	
	private void drawButtons() {
		back = new JButton("RETURN");
		rback = new Rectangle(width / 2 - buttonWidth / 2, 190, buttonWidth, buttonHeight);
		back.setBounds(rback);
		window.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Launcher(0);
			}
		});
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("Author: Kian Bennett", 60, 80);
		g.drawString("Version: " + Main.version, 60, 120);
	}
	
}
