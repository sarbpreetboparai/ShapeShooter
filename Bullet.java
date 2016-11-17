
public class Bullet extends Rectangle {

	public Bullet(int w, int l){
		System.out.println(""+w+" "+l);
		x = 420;
		y = 600;
		width = 20;
		length = 10;
	}
	
	public void step(){
		y -= 20;
	}
	
}
