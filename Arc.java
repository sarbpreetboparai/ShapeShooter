import java.awt.Graphics;
public class Arc {

	public int length,breadth, start, end;
	public int center_x, center_y;
	public int dir=1;
	public int dirV=1;
	public Arc(int l,int b,int x,int y, int s, int e){
		this.length=l;
		this.breadth=b;
		this.center_x=x;
		this.center_y=y;
		this.start= s;
		this.end=e;
	}
	
	public void draw(Graphics g){
		System.out.println("Drawing arc");
		g.drawArc(center_x,center_y, length, breadth, start, end);
	}
	
}
