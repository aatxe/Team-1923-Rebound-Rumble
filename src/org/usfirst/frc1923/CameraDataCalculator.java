package org.usfirst.frc1923;

public class CameraDataCalculator {
	private static double focalX = 802.361;
	private static double focalY = 799.476;
	private static double targetWidth = 2;
	
	protected static double exp(double b, int n) {
		int c = 1;
		for (int i = 0; i < n; i++) {
			c *= b;
		}
		return c;
	}

	public static double getForce(double x, int height) {
		try {
			if (height == 2) {
				return 0.1667 * exp(x, 3) - 4.7857 * exp(x, 2) + 31.048 * x + 13.6;
			} else if (height == 3 || height == 1) {
				return -0.002 * exp(x, 5) + 0.0572 * exp(x, 4) - 0.564 * exp(x, 3) + 2.5493 * exp(x, 2) - 3.9604 * x + 54.045;
			} else {
				return 0;
			}
		} catch (NullPointerException e) {
		}
		return 0;
	}

	public static double getForce(CameraDataPacket cdp) {
		try {
			double x = getDistance(cdp);
			if (cdp.getBasketHeight() == 2) {
				return 0.1667 * exp(x, 3) - 4.7857 * exp(x, 2) + 31.048 * x + 13.6;
			} else if (cdp.getBasketHeight() == 3 || cdp.getBasketHeight() == 1) {
				return -0.002 * exp(x, 5) + 0.0572 * exp(x, 4) - 0.564 * exp(x, 3) + 2.5493 * exp(x, 2) - 3.9604 * x + 54.045;
			} else {
				return -0.002 * exp(x, 5) + 0.0572 * exp(x, 4) - 0.564 * exp(x, 3) + 2.5493 * exp(x, 2) - 3.9604 * x + 54.045;
			}
		} catch (NullPointerException e) {
		}
		return 0;
	}
	
	public static double getDistance(CameraDataPacket cdp) {
		try {
			return ((targetWidth * 640) / cdp.getWidth()) / Math.tan(21.75); // returns d feet.
		} catch (NullPointerException e) {}
		return 0;
	}

	public static double getFocalX() {
		return focalX;
	}

	public static double getFocalY() {
		return focalY;
	}
}
