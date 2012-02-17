package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;

public class Conveyor {
	private Jaguar pickupConveyorLeft;
	private Jaguar pickupConveyorRight;
	private Relay middleConveyor;

	public Conveyor(Components components) {
		pickupConveyorLeft = components.pickupConveyorLeft;
		pickupConveyorRight = components.pickupConveyorRight;
		middleConveyor = components.middleConveyor;
	}

	public void pickupBalls(double value) {
		pickupConveyorLeft.set(value);
		pickupConveyorRight.set(value);
	}

	public void stopPickup() {
		pickupConveyorLeft.stopMotor();
		pickupConveyorRight.stopMotor();
	}

	public void runMidLevel(Relay.Value value) {
		middleConveyor.set(value);
	}

	public void stopMidLevel() {
		middleConveyor.set(Relay.Value.kOff);
	}
}
