import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class FxBall implements IBall {
	
	private long time = 0;
	private double cx;
	private double cy;
	private double width;
	private double height;
	private double vx;
	private double vy;
	private double speed;
	private java.awt.Color color;
	List<Ball> ballPositions = new ArrayList<Ball>();
	  
	public FxBall(double cx, double cy, double width, double height, java.awt.Color color, double speed, double vx, double vy) {
		this.cx = cx;
		this.cy = cy;
		this.height = height;
		this.width = width;
		this.color = color;
		this.speed = speed;
		this.vx = vx;
		this.vy = vy;
	}
	
	Ball thisBall = new Ball(cx, cy, width, height, color, speed, vx, vy);
	
	public List<Ball> getList () {
		return ballPositions;
	}
	
	public long getTime () {
		return time;
	}
	
	public void setTime (long thisTime) {
		time = thisTime;
	}
	
	public Ball getBalls(int x) {
		return ballPositions.get(0);
	}
	
	@Override
	public double getCx() {
		// TODO Auto-generated method stub
		return thisBall.getCx();
	}
	@Override
	public double getCy() {
		// TODO Auto-generated method stub
		return thisBall.getCy();
	}
	
	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setCx(double cx) {
		this.cx = cx;
	}

	public void setCy(double cy) {
		this.cy = cy;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return thisBall.getWidth();
	}
	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return thisBall.getHeight();
	}
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return thisBall.getColor();
	}
	@Override
	public double getVx() {
		// TODO Auto-generated method stub
		return thisBall.getVx();
	}
	@Override
	public double getVy() {
		// TODO Auto-generated method stub
		return thisBall.getVy();
	}
	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return thisBall.getSpeed();
	}
	@Override
	public void setColor(Color arg0) {
		// TODO Auto-generated method stub
		thisBall.setColor(arg0);
	}
	@Override
	public void setSpeed(double arg0) {
		// TODO Auto-generated method stub
		thisBall.setSpeed(arg0);
	}
	@Override
	public boolean checkCollision(Player arg0) {
		// TODO Auto-generated method stub
		return thisBall.checkCollision(arg0);
	}
	@Override
	public boolean checkCollision(Wall arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean checkCollision(Target arg0) {
		// TODO Auto-generated method stub
		return thisBall.checkCollision(arg0);
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		thisBall.draw();
	}
	@Override
	public void update(long arg0) {
		// TODO Auto-generated method stub
		thisBall.update(arg0);
	}
	
}