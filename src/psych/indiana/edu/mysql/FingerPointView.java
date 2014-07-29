package psych.indiana.edu.mysql;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FingerPointView extends JFrame {
	
	private GraphicsDevice vga;
	private DisplayMode displayMode;
	private Image fingerPrint;
	private boolean loaded;
	private ArrayList<Coordinates> coordinateList;
	
	public FingerPointView(){
		coordinateList = FingerPointModel.getInstance().getCoordinateList();
		loaded = false;
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vga = env.getDefaultScreenDevice();
		displayMode = vga.getDisplayMode();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		//setForeground(Color.BLACK);
		setFont(new Font("Arial", Font.PLAIN, 24));
		loadImages();
	}
	
	public void loadImages(){
		try{
			fingerPrint = ImageIO.read(ClassLoader.getSystemResourceAsStream("fingerprint.jpg"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		loaded = true;
		repaint();
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		if(loaded){
			g2.drawImage(fingerPrint, 0, 0, 1366, 768, null);
		}
		Iterator<Coordinates> listIterator = coordinateList.iterator();
		while(listIterator.hasNext()){
			Coordinates currentCoordinate = listIterator.next();
			int x = currentCoordinate.getX()-50;
			int y = currentCoordinate.getY()-50;
			int width = 100;
			int height = 100;
			System.out.println("Painting at: "+(x+50)+", "+(y+50));
		    g2.setStroke(new BasicStroke(3.0f));
		    g2.setPaint(new Color(200,0,0));
			g2.drawRect(x, y, width, height);
		}
	}
	
	
	public void setFullScreen(){
		this.setUndecorated(true);
		this.setResizable(false);
		vga.setFullScreenWindow(this);
		
		if(displayMode!=null && vga.isDisplayChangeSupported()){
			try{
				vga.setDisplayMode(new DisplayMode(displayMode.getWidth(), displayMode.getHeight(), displayMode.getBitDepth(), displayMode.getRefreshRate()));
				System.out.println(displayMode.getWidth()+", "+ displayMode.getHeight()+", "+ displayMode.getBitDepth()+", "+ displayMode.getRefreshRate());
			}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("You have an incompatible Video/Graphics card");
			}
		}
	}
	
	public Window getFullScreenWindow(){
		return vga.getFullScreenWindow();
	}
	
	public void restoreScreen(){
		Window w = vga.getFullScreenWindow();
		if(w != null){
			w.dispose();
		}
		vga.setFullScreenWindow(null);
	}
}
