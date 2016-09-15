public class Ship {

	private String name;
	private int length;
	private int frontx;
	private int fronty;
	private int backx;
	private int backy;
	private boolean destroyed;
	private int hit_count;
	
	public Ship(String type, int size, int front_x, int front_y, int back_x, int back_y, boolean destroy, int hits){
	name = type;
	length = size;
	frontx = front_x;
	fronty = front_y;
	backx = back_x;
	backy = back_y;
	destroyed = destroy;
	hit_count = hits;
	
	}
	
	public int getHit_count() {
		return hit_count;
	}

	public void setHit_count(int hit_count) {
		this.hit_count = hit_count;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getFrontx() {
		return frontx;
	}

	public void setFrontx(int frontx) {
		this.frontx = frontx;
	}

	public int getFronty() {
		return fronty;
	}

	public void setFronty(int fronty) {
		this.fronty = fronty;
	}

	public int getBackx() {
		return backx;
	}

	public void setBackx(int backx) {
		this.backx = backx;
	}

	public int getBacky() {
		return backy;
	}

	public void setBacky(int backy) {
		this.backy = backy;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	public boolean getDestroyed() {
		return destroyed;
	}

}