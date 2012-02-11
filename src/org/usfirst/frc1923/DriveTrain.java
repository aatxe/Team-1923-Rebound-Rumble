package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveTrain {
	private RobotDrive drive;

	public DriveTrain(Components components) {
		drive = components.drive;
	}

	public void drive(double leftValue, double rightValue) {
		drive.tankDrive(leftValue, rightValue);
	}

	public void drive(GenericHID leftStick, GenericHID rightStick) {
		drive.tankDrive(leftStick, rightStick);
	}

	public void stop() {
		drive.stopMotor();
	}
}
