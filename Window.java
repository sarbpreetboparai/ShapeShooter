import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Window extends JFrame {
	private Canvas canvas;
	public Timer shapeTimer;
	public Timer bulletTimer;

	public Window() {
		setTitle("My Graphic Game");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		canvas = new Canvas();

		this.add(canvas, BorderLayout.CENTER);

		JButton b1 = new JButton("Circle");
		b1.addActionListener(new CircleButtonHandler());
		JButton b2 = new JButton("Square");
		b2.addActionListener(new SquareButtonHandler());
		JButton b3 = new JButton("Rectangle");
		b3.addActionListener(new RectangleButtonHandler());
		JButton b4 = new JButton("Arc");
		b4.addActionListener(new ArcButtonHandler());
		JButton b5 = new JButton("Triangle");
		b5.addActionListener(new TriButtonHandler());
		JButton b6 = new JButton("Move!");
		b6.addActionListener(new MoveButtonHandler());
		JButton b7 = new JButton("Stop!");
		b7.addActionListener(new StopButtonHandler());
		JButton b8 = new JButton("Shoot!");
		b8.addActionListener(new ShootButtonHandler());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(b1);
		buttonPanel.add(b2);
		buttonPanel.add(b3);
		buttonPanel.add(b4);
		buttonPanel.add(b5);
		buttonPanel.add(b6);
		buttonPanel.add(b7);
		buttonPanel.add(b8);
		buttonPanel.setBackground(new Color(51, 255, 51));

		add(buttonPanel, BorderLayout.SOUTH);

		shapeTimer = new Timer(50, new ShapeTimerHandler());
		bulletTimer = new Timer(40, new BulletTimerHandler());
		
	}

	public void start() {
		setVisible(true);
	}

	public class CircleButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			

			canvas.createCircle();
			repaint();

		}
	}

	public class SquareButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.createSquare();
			repaint();
		}
	}

	public class RectangleButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.createRectangle();
			repaint();
		}
	}

	public class ArcButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Create arc");
			canvas.createArc();

			repaint();
		}
	}

	public class TriButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Create Tri");
			canvas.createTriangle();

			repaint();
		}
	}

	public class MoveButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Move");
			shapeTimer.start();
		}
	}

	public class StopButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			shapeTimer.stop();
		}
	}

	public class ShootButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			 URL url = this.getClass().getClassLoader().getResource("shoot.wav");
	         AudioInputStream audioIn;
	         Clip clip=null;
			try {
				audioIn = AudioSystem.getAudioInputStream(url);
				 // Get a sound clip resource.
		          clip= AudioSystem.getClip();
		         // Open audio clip and load samples from the audio input stream.
		         clip.open(audioIn);
			} catch (UnsupportedAudioFileException e1) {
				
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	         clip.start();
			bulletTimer.start();
		}
	}

	public class ShapeTimerHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			canvas.moveShapes();
			repaint();
		}
	}
	
	public class BulletTimerHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			canvas.moveBullet();
			repaint();
			if (canvas.bulletOffScreen()){
				bulletTimer.stop();
				canvas.resetBullet();
			}
		}
	}
}
