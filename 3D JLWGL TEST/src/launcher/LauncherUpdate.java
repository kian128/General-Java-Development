package launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;

import main.Main;

public class LauncherUpdate extends Launcher {
		
	Graphics g = this.getGraphics();
	
	private JButton back;
	private Rectangle rback;
	
	public LauncherUpdate() {
		super(1);
		setTitle("Update");
		drawButtons();
		update();
	}
		
	private void update() {
		try {
			/*
			 * Get a connection to the URL and start up a buffered reader.
			 */
			long startTime = System.currentTimeMillis();

			System.out.println("Connecting...\n");
			paint(g, "Connecting...", "", "", "");

			URL url = new URL("https://dl.dropbox.com/s/7nmoaprw9p7f35e/run.exe?dl=1");
			url.openConnection();
			InputStream reader = url.openStream();

			/*
			 * Setup a buffered file writer to write out what we read from the
			 * website.
			 */
			FileOutputStream writer = new FileOutputStream("run.exe");
			byte[] buffer = new byte[153600];
			int totalBytesRead = 0;
			int bytesRead = 0;

			System.out.println("Reading ZIP file 150KB blocks at a time...\n");
			paint(g, "Connecting...", "Reading file 150KB blocks at a time", "This window will close when", "the download is complete...");

			while ((bytesRead = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, bytesRead);
				buffer = new byte[153600];
				totalBytesRead += bytesRead;
			}

			long endTime = System.currentTimeMillis();

			System.out.println("Done. "
					+ (new Integer(totalBytesRead).toString())
					+ " bytes read ("
					+ (new Long(endTime - startTime).toString())
					+ " millseconds).\n");
			writer.close();
			reader.close();
			System.exit(0);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
	
	public void paint(Graphics g, String s1, String s2, String s3, String s4) {
		g.setColor(Color.BLACK);
		g.drawString(s1, 10, 80);
		g.drawString(s2, 10, 120);
		g.drawString(s3, 10, 160);
		g.drawString(s4, 10, 180);
	}
	
}
