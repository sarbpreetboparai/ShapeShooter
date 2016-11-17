import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Circle {

	public int _radius;
	public int dir = 1;
	public int dirV = 1;
	public int center_x;
	public int center_y;
    public int[] color= new int[3];
    Random r = new Random();
	public Circle(int x, int y, int radius) {
		this.center_x = x;
		this.center_y = y;
		this._radius = radius;
		this.color[0]=r.nextInt(255)+1;
		this.color[1]=r.nextInt(255)+1;
		this.color[2]=r.nextInt(255)+1;
	}


	public void draw(Graphics g) {
		
		
		g.fillOval(center_x - _radius, center_y - _radius, _radius * 2,
				_radius * 2);
		
	}
	public void setCircleColor(Graphics g){
		g.setColor(new Color(color[0],color[1],color[2]));
	}
	
	public boolean isBulletInside(int bullet_x, int bullet_y){
		
		//calc distance;
		int distX1 = center_x+_radius;
		int distX2 = center_x-_radius;
		
		int distY1 = center_y + _radius;
		int distY2 = center_y - _radius;
		
		if(bullet_x < distX1 && bullet_x > distX2 && bullet_y < distY1 && bullet_y > distY2){
			return true;
		}
		else{
			return false;
		}
		// if distance < radius return true;
		// if distance > radius return false
		
	
	}
	
	
}


