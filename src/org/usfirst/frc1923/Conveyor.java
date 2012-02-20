package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;

public class Conveyor {
	private Jaguar pickupConveyorLeft;
	private Jaguar pickupConveyorRight;
	private Relay middleConveyor;

	private boolean intakeRunning = false;
	private boolean elevatorRunning = false;

	public Conveyor(Components components) {
		pickupConveyorLeft = components.pickupConveyorLeft;
		pickupConveyorRight = components.pickupConveyorRight;
		middleConveyor = components.middleConveyor;
	}

	public void startIntake(double value) {
		pickupConveyorLeft.set(value);
		pickupConveyorRight.set(value);
		intakeRunning = true;
	}

	public void stopIntake() {
		pickupConveyorLeft.stopMotor();
		pickupConveyorRight.stopMotor();
		intakeRunning = false;
	}

	public void runElevator(Relay.Value value) {
		middleConveyor.set(value);
		elevatorRunning = true;
	}

	public void stopElevator() {
		middleConveyor.set(Relay.Value.kOff);
		elevatorRunning = false;
	}

	public boolean isIntakeRunning() {
		return intakeRunning;
	}

	public boolean isElevatorRunning() {
		return elevatorRunning;
	}
}
