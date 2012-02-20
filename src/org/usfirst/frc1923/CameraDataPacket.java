package org.usfirst.frc1923;

class CameraDataPacket {
	private int x, y;
	private int basketHeight;

	public CameraDataPacket() {
		x = 0;
		y = 0;
		basketHeight = -1;
	}

	public CameraDataPacket(int x, int y) {
		this.x = x;
		this.y = y;
		basketHeight = -1;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
		basketHeight = -1;
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