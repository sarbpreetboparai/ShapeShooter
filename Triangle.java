import java.awt.Graphics;


public class Triangle {

	int[] x= new int[3];
	int[] y= new int[3];
	public int dir=1;
	public int dirV=1;
	public Triangle(int[] xP, int[]yP){
		
		for (int i = 0; i < x.length; i++) {
			x[i]=xP[i];
		}

		for (int i = 0; i < y.length; i++) {
			y[i]=yP[i];
		}
		
	}
	
	public void draw(Graphics g){
		g.drawPolygon(x, y, 3);
	}
	
}
