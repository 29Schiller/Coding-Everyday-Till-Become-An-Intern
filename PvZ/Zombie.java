import java.awt.*;

abstract class Zombie{    
	private String name;
	private int hp, damge;
    private int width, height;
    private float spd;
    private double x, y;
    private boolean isAlived = true;
    private boolean isDead = false;
    private Rectangle bound;
	private boolean isCollides;

	public Zombie(int hp, int damge, int width, int height, float spd, double x, double y, boolean isAlived,
			boolean isDead, Rectangle bound, String name) {
		this.hp = hp;
		this.damge = damge;
		this.width = width;
		this.height = height;
		this.spd = spd;
		this.x = x;
		this.y = y;
		this.isAlived = isAlived;
		this.isDead = isDead;
		this.name = name;

}

	public int getDamge() {
		return damge;
	}

	public float getSpd() {
		return spd;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isAlived() {
		return isAlived;
	}

	public boolean isDead() {
		return isDead;
	}

	public Rectangle getBound() {
		return bound;
	}
	public String getName() {
		return name;
	}
	
	public int getHp() {
		return hp;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isCollides() {
		return isCollides;
	}
	abstract boolean death();
	abstract void attack();
	abstract void move();
	abstract void getAnimation();
	abstract void update();
	abstract void render(Graphics g);
	
} 