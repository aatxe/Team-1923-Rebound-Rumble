package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Jaguar;

public class Conveyor {
	private Jaguar pickupConveyor;
	private Jaguar middleConveyor;

	public Conveyor(Components components) {
	}

	public void pickupBalls(double value) {
		pickupConveyor.set(value);
	}
	
	public void stopPickup() {
		pickupConveyor.stopMotor();
	}
	
	public void runMidLevel(double value) {
		middleConveyor.set(value);
	}

	public void stopMidLevel() {
		middleConveyor.stopMotor();
	}
}
