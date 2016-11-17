import java.awt.Graphics;

public class Rectangle {

	public int length, width;
	public int x, y;
	public int dir = 1;
	public int dirV = 1;

	public Rectangle(){
		
	}
	
	public Rectangle(int l, int b, int x, int y) {
		this.length = l;
		this.width = b;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.drawRect(x, y, length, width);
	}

}
