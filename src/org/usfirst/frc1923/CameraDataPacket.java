package org.usfirst.frc1923;

class CameraDataPacket {
	private int x, y;
	private int height, width;
	private int basketHeight;

	public CameraDataPacket() {
		this(0, 0);
	}

	public CameraDataPacket(int x, int y) {
		this(x, y, 0, 0);
	}
	
	public CameraDataPacket(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.basketHeight = -1;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
		basketHeight = -1;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setBasketHeight(int basketHeight) {
		this.basketHeight = basketHeight;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getBasketHeight() {
		return basketHeight;
	}

	public String getCoordinates() {
		return "(" + x + "," + y + ")";
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
}