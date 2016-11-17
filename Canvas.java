import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	
	/*variables to keep track of the drag and drop mouse */
	public int trackXP = 0;
	public int trackXR = 0;
	public int trackYP = 0;
	public int trackYR = 0;

	//array lists to store different shapes
	List<Circle> circles = new ArrayList<Circle>();
	List<Square> squares = new ArrayList<Square>();
	List<Triangle> tris = new ArrayList<Triangle>();
	List<Rectangle> rects = new ArrayList<Rectangle>();
	List<Arc> arcs = new ArrayList<Arc>();
	
	Random rand = new Random(); // to generate random numbers
	private Bullet bullet;
	
	
	public Canvas() {
		addMouseListener(new MyMouseListener());
		bullet = new Bullet(800,600);
		
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(51, 255, 51));
         
		for (int i = 0; i < circles.size(); i++) {
		 circles.get(i).setCircleColor(g);        
			
			circles.get(i).draw(g);
		}

		g.setColor(Color.black);
		for (int i = 0; i < squares.size(); i++) {
		
			squares.get(i).draw(g);
		}
		for (int i = 0; i < rects.size(); i++) {
			

			rects.get(i).draw(g);
		}

		for (int i = 0; i < arcs.size(); i++) {
			arcs.get(i).draw(g);
		}
		for (int i = 0; i < tris.size(); i++) {
			tris.get(i).draw(g);
		}

		try {
			File file = new File("newTank.gif");
			Image img = ImageIO.read(file);
			g.drawImage(img, this.getWidth()/2,(this.getHeight()-65),this);
		} catch (Exception e) {
			System.out.println("File not found");
		}
		
		g.setColor(Color.BLACK);
		bullet.draw(g);
	}

	public void createArc() {
		int x = rand.nextInt(800) + 1;
		int y = rand.nextInt(500) + 1;
		int l = rand.nextInt(600) + 1;
		int b = rand.nextInt(700) + 1;
		int s = rand.nextInt(360) + 1;
		int e = rand.nextInt(360) + 1;

		arcs.add(new Arc(x, y, l, b, s, e));
		
	}

	public void createCircle() {

		int x = rand.nextInt(800) + 1;
		int y = rand.nextInt(500) + 1;
		int r = rand.nextInt(250) + 1;

	    
		circles.add(new Circle(x, y, r));

	}

	public void createCircle(int x, int y, int r) {

		circles.add(new Circle(x, y, r));

	}

	public void createTriangle() {
		int[] x = new int[3];
		int[] y = new int[3];
		for (int i = 0; i < y.length; i++) {
			x[i] = rand.nextInt(700) + 1;
			y[i] = rand.nextInt(600) + 1;
		}
		tris.add(new Triangle(x, y));

	}

	public void createCircle(int x) {

		System.out.println("hello from drag circle");
		int realX = trackXR - trackXP;
		int realY = trackYR - trackYP;
		realX = Math.abs(realX);
		realY = Math.abs(realY);
		int add = (realX * realX) + (realY * realY);
		double r = Math.sqrt(add);
		int rad = (int) r;
		circles.add(new Circle(trackXP, trackYP, rad));

	}

	public void createSquare() {
		int x = rand.nextInt(800) + 1;
		int y = rand.nextInt(500) + 1;
		int l = rand.nextInt(300) + 1;

		squares.add(new Square(l, x, y));

	}

	public void createRectangle() {
		int x = rand.nextInt(800) + 1;
		int y = rand.nextInt(500) + 1;
		int l = rand.nextInt(600) + 1;
		int b = rand.nextInt(500) + 1;
		rects.add(new Rectangle(l, b, x, y));
	}

	public void moveShapes() {

		moveCircle();
		moveTriangle();
		moveSquare();
		moveRectangle();
		moveArc();
	}

	public void moveArc() {
		for (int i = 0; i < arcs.size(); i++) {
			Arc a= arcs.get(i);
			if (a.dir == 1 && a.center_x + a.length < this.getWidth() || a.dir == -1 && a.center_x > 0) {
				a.center_x += a.dir*10;
			}  else {
				a.dir = -a.dir;
			}
			if (a.dirV == 1 && a.center_y + a.breadth < this.getHeight() || a.dirV == -1 && a.center_y > 0) {
				a.center_y += a.dirV*10;
			}  else {
				a.dirV = -a.dirV;
			}

		}
	}

	public void moveRectangle() {
		for (int i = 0; i < rects.size(); i++) {
			Rectangle r = rects.get(i);
			if (r.dir == 1 && r.x + r.length < this.getWidth()
					|| r.dir == -1 && r.x > 0) {
				r.x += r.dir * 10;
			} else {
				r.dir = -r.dir;
			}
			if (r.dirV == 1 && r.y + r.width < this.getHeight()
					|| r.dirV == -1 && r.y > 0) {
				r.y += r.dirV * 10;
			} else {
				r.dirV = -r.dirV;
			}
		}
	}

	public void moveTriangle() {
		for (int i = 0; i < tris.size(); i++) {
			Triangle t = tris.get(i);
			for (int j = 0; j < 3; j++) {
				if (t.dir == 1 && t.x[j] < this.getWidth() || t.dir == -1
						&& t.x[j] > 0) {
					t.x[j] += t.dir * 10;
				} else {
					t.dir = -t.dir;
				}
				if (t.dirV == 1 && t.y[j] < this.getHeight() || t.dirV == -1
						&& t.y[j] > 0) {
					t.y[j] += t.dirV * 10;
				} else {
					t.dirV = -t.dirV;
				}
			}

		}
	}

	public void moveSquare() {
		for (int i = 0; i < squares.size(); i++) {
			Square s = squares.get(i);
			if (s.dir == 1 && s.center_x + s.length < this.getWidth()
					|| s.dir == -1 && s.center_x > 0) {
				s.center_x += s.dir * 10;
			} else {
				s.dir = -s.dir;
			}

			if (s.dirV == 1 && s.center_y + s.length < this.getHeight()
					|| s.dirV == -1 && s.center_y > 0) {
				s.center_y += s.dirV * 10;

			} else {
				s.dirV = -s.dirV;
			}

		}
	}

	public void moveCircle() {
		for (int i = 0; i < circles.size(); i++) {
			Circle c = circles.get(i);

			if (c.dir == 1 && c.center_x + c._radius > this.getWidth()
					|| c.dir == -1 && c.center_x - c._radius < 0) {
				c.dir = -c.dir;
			} else {
				c.center_x += c.dir * 10;
			}

			if (c.dirV == 1 && c.center_y + c._radius < (this.getHeight()-30)
					|| c.dirV == -1 && c.center_y - c._radius > 0) {

				c.center_y += c.dirV * 10;

			} else {
				c.dirV = -c.dirV;

			}
			if(c.isBulletInside(bullet.x, bullet.y)){
				System.out.println("Collided");
				circles.remove(i);
			}
		}
	}
	
	public void moveBullet(){
		bullet.step();
		for(int i=0; i<circles.size(); i++){
			Circle c = circles.get(i);
			if(c.isBulletInside(bullet.x, bullet.y)){
				System.out.println("Collided!");
				circles.remove(i);
			}
		}
	}
	
	public boolean bulletOffScreen(){
		return bullet.y < 0;
	}
	public void resetBullet(){
		bullet.y=500;
	}

	class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			createCircle(e.getX(), e.getY(), 50);
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("[" + e.getX() + "," + e.getY() + "]"
					+ "Mouse Entered");

		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("[" + e.getX() + "," + e.getY() + "]"
					+ "Mouse Exited");

		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("[" + e.getX() + "," + e.getY() + "]"
					+ "Mouse pressed");
			trackXP = e.getX();
			trackYP = e.getY();

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("[" + e.getX() + "," + e.getY() + "]"
					+ "Mouse released");
			trackXR = e.getX();
			trackYR = e.getY();
			createCircle(e.getX());
			repaint();
		}

	}
}
