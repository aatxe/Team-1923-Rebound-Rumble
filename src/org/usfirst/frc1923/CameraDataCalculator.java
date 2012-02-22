package org.usfirst.frc1923;

public class CameraDataCalculator {
	private static double focalX = 802.361;
	private static double focalY = 799.476;
	private static double heightHoop3 = 98;
	private static double heightHoop2 = 61;
	// private static double heightHoop1 = 28;
	private static double heightRobot = 47;
	private static CameraDataPacket imageCenter = new CameraDataPacket(320, 240);

	protected static double exp(double b, int n) {
		int c = 1;
		for (int i = 0; i < n; i++) {
			c *= b;
		}
		return c;
	}

	public static double getForce(double x, int height) {
		try {
			x /= 12;
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
			double x = getDistance(cdp) / 12;
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

	// Returns distance in inches.
	public static double getDistance(CameraDataPacket cdp) {
		double height = 0;
		try {
			if (cdp.getBasketHeight() == 3) {
				height = heightHoop3;
			} else if (cdp.getBasketHeight() == 2) {
				height = heightHoop2;
			} else if (cdp.getBasketHeight() == 1) {
				height = heightHoop3;
			}
			// return ((focalY * (height - heightRobot))) / (cdp.getY() - imageCenter.getY());
			return Math.abs(cdp.getY() - imageCenter.getY());
			/*
			 * Fuck this, I need atan();
			 * http://locify.googlecode.com/svn/tags/1.0/src/com/locify/client/utils/math/LMath.java
			 */
			
		} catch (NullPointerException e) {
		}
		return 0;
	}

	public static double getFocalX() {
		return focalX;
	}

	public static double getFocalY() {
		return focalY;
	}
}
