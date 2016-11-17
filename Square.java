import java.awt.Graphics;


public class Square {

	public int length;
	public int center_x, center_y;
	public int dir=1;
	public int dirV=1;
	public Square(int l,int x,int y){
		this.length=l;
		this.center_x=x;
		this.center_y=y;
		
	}
	
	public void draw(Graphics g){
		System.out.println("Draw square");
		g.drawRect(center_x, center_y, length, length);
		
	}
}
