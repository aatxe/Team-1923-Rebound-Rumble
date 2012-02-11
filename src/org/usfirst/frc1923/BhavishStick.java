package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Joystick;

public class BhavishStick extends Joystick {
	Coalescor coalescor = new Coalescor();

	public BhavishStick(int port) {
		super(port);
	}

	public BhavishStick(int port, double epsilon) {
		super(port);
		coalescor.setEpsilon(epsilon);
	}

	public BhavishStick(int port, int numAxisTypes, int numButtonTypes) {
		super(port, numAxisTypes, numButtonTypes);
	}

	public BhavishStick(int port, int numAxisTypes, int numButtonTypes, double epsilon) {
		super(port, numAxisTypes, numButtonTypes);
		coalescor.setEpsilon(epsilon);
	}

	public double getCoalescedX() {
		return coalescor.coalesce(super.getX());
	}

	public double getCoalescedY() {
		return coalescor.coalesce(super.getY());
	}

	public double getAxis(Joystick.AxisType axis) {
		return coalescor.coalesce(super.getAxis(axis));
	}

	public double getRawAxis(int axis) {
		return coalescor.coalesce(super.getRawAxis(axis));
	}

	public double getCoalescorEpsilon() {
		return coalescor.getEpsilon();
	}

	public void setCoalescorEpsilon(double epsilon) {
		coalescor.setEpsilon(epsilon);
	}
}
