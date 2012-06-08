package applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JLabel;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import main.Main;

public class GameApplet extends Applet {
	
	Canvas displayParent;
	
	public void startLWJGL() {
		try {
			Display.setParent(displayParent);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			new Main();
		} catch (Exception e) {}
	}
	
	public void stopLWJGL() {
		Main.running = false;
	}
	
	public void start() {}
	public void stop() {}
	
	public void destroy() {
		remove(displayParent);
		super.destroy();
	}
	
	public void init() {
		setLayout(new BorderLayout());
		try {
			displayParent = new Canvas() {
				public final void addNotify() {
					super.addNotify();
					startLWJGL();
				}
				public final void removeNotify() {
					stopLWJGL();
					super.removeNotify();
				}
			};
			displayParent.setSize(getWidth(), getHeight());
			add(displayParent);
			displayParent.setFocusable(true);
			displayParent.requestFocus();
			displayParent.setIgnoreRepaint(true);
			setVisible(true);
		} catch(Exception e) {
			System.err.println("Unable to create display:" + e);
		}
	}
	
}
	
