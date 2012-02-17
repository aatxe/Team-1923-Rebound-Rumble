package org.usfirst.frc1923;

class CameraDataPacket {
	private int x, y;

	public CameraDataPacket() {
		x = 0;
		y = 0;
	}

	public CameraDataPacket(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getCoordinates() {
		return "(" + x + "," + y + ")";
	}
}