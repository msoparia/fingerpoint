package psych.indiana.edu.mysql;

import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FingerPointController implements MouseListener, KeyListener{

	private FingerPointView screen;
	private MySQLAccess mySQLDb = new MySQLAccess();
	
	public FingerPointController(){
		screen = new FingerPointView();
		screen.getContentPane().setFocusable(true);
		screen.getContentPane().addKeyListener(this);
		screen.getContentPane().addMouseListener(this);
		screen.setFullScreen();
		try {
			mySQLDb.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == 27){
			if (JOptionPane.showConfirmDialog(new JFrame(),
			        "Do you want to quit this application ?", "Title",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				//screen.restoreScreen();
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0){
			Double x= e.getPoint().getX(), y = e.getPoint().getY();
			System.out.println("Left clicked: "+x+","+y);
			FingerPointModel.getInstance().addCoordinates(x, y);
			addCoordinatesToDBase(x,y);
			screen.repaint();
		}
		else System.out.println("Right Clicked");
		
	}
	private void addCoordinatesToDBase(Double x, Double y) {
		// TODO Auto-generated method stub
		mySQLDb.insertData("insert into coordinates values ('"+x+"', '"+y+"')");
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
