package org.usfirst.frc1923;

public class ShooterGearbox {
	private int gear = 0;
	private int oldGear = 0;
	private double[] gears = {};
	private boolean justGearedUp = false;
	private boolean justGearedDown = false;

	public ShooterGearbox(double[] gears, Components components) {
		this.gears = gears;
	}

	public ShooterGearbox(int start, int end, int increment, Components components) {
		int length = ((end - start) / increment) + 1;
		double[] gears = new double[length];
		for (int i = 0; i < length; i++) {
			gears[i] = (start + (i * increment)) / 100;
		}
		this.gears = gears;
	}

	public double getSpeed() {
		return gears[gear];
	}
	
	public int getGear() {
		return gear;
	}
	
	public double getPreviousSpeed() {
		return gears[oldGear];
	}
	
	public int getPreviousGear() {
		return oldGear;
	}
	
	public void setGear(int gear) {
		if (this.gear != gear && gear < (gears.length - 1) && gear > 0) {
			this.oldGear = this.gear;
			this.gear = gear;
			Output.say("[Shooter] Gear changed to " + gear + ".");
		}
	}

	public void gearDown() {
		if (gear > 0) {
			oldGear = gear;
			--gear;
			Output.say("[Shooter] Geared down to " + gear + ".");
			justGearedDown = true;
		}
	}

	public void gearUp() {
		if (gear < (gears.length - 1)) {
			oldGear = gear;
			++gear;
			Output.say("[Shooter] Geared up to " + gear + ".");
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
