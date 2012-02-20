package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.RobotDrive;

public class DriveGearbox {
	private RobotDrive drive;
	private int gear = 0;
	private double[] gears = {};
	private boolean justGearedUp = false;
	private boolean justGearedDown = false;

	public DriveGearbox(double[] gears, Components components) {
		this.gears = gears;
		this.drive = components.drive;
	}

	public DriveGearbox(int start, int end, int increment, Components components) {
		int length = ((end - start) / increment) + 1;
		double[] gears = new double[length];
		for (int i = 0; i < length; i++) {
			gears[i] = (start + (i * increment)) / 100;
		}
		this.gears = gears;
		this.drive = components.drive;
	}

	public double getSpeed() {
		return gears[gear];
	}

	public int getGear() {
		return gear;
	}

	public void setGear(int gear) {
		if (this.gear != gear && gear < (gears.length - 1) && gear > 0) {
			this.gear = gear;
			Output.say("[Drive] Gear changed to " + gear + ".");
			drive.setMaxOutput(gears[gear]);
		}
	}

	public void gearDown() {
		if (gear > 0) {
			--gear;
			Output.say("[Drive] Geared down to " + gear + ".");
			drive.setMaxOutput(gears[gear]);
			justGearedDown = true;
		}
	}

	public void gearUp() {
		if (gear < (gears.length - 1)) {
			++gear;
			Output.say("[Drive] Geared up to " + gear + ".");
			drive.setMaxOutput(gears[gear]);
			justGearedUp = true;
		}
	}

	public boolean didJustGearUp() {
		return this.justGearedUp;
	}

	public boolean didJustGearDown() {
		return this.justGearedDown;
	}

	public void resetGearControls() {
		justGearedDown = false;
		justGearedUp = false;
	}
}
