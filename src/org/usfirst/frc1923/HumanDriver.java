package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

public class HumanDriver {
	private DriveTrain driveTrain;
	private Shooter shooter;
	private Conveyor conveyor;
	
	private BhavishStick leftDriveStick;
	private BhavishStick rightDriveStick;
	private XboxController operatorController;
	private Gearbox driveGearbox;
	private Relay bridgeKnockerDowner;

	private boolean didShooterGearChange = false;
	private int shooterSpeed = 0;

	public HumanDriver(DriveTrain driveTrain, Shooter shooter, Conveyor conveyor, Components components) {
		this.driveTrain = driveTrain;
		this.shooter = shooter;
		this.conveyor = conveyor;
		driveGearbox = new Gearbox(Configuration.driveGears, components);
		leftDriveStick = components.leftDriveStick;
		rightDriveStick = components.rightDriveStick;
		operatorController = components.operatorController;
		bridgeKnockerDowner = components.bridgeKnockerDowner;
		components.drive.setSafetyEnabled(false);
	}

	public void handleActiveDriving() {
		if (Configuration.experimentalDriving) {
			double throttle = leftDriveStick.getCoalescedY();
			double turning = rightDriveStick.getCoalescedX();
			if (turning < 0) {
				driveTrain.drive(throttle + turning, throttle);
			} else if (turning < 0) {
				driveTrain.drive(throttle, throttle - turning);
			}
		} else if (Configuration.reversedControls) {
			driveTrain.drive(rightDriveStick.getCoalescedY(), leftDriveStick.getCoalescedY());
		} else {
			driveTrain.drive(-leftDriveStick.getCoalescedY(), -rightDriveStick.getCoalescedY());
		}
	}

	public void handlePassiveDriving() {
		if (Configuration.experimentalDriving) {
			if (rightDriveStick.getRawButton(4)) {
				driveTrain.drive(0.75, -0.75);
			} else if (rightDriveStick.getRawButton(5)) {
				driveTrain.drive(-0.75, 0.75);
			}
		}
		if (Configuration.gearShifter) {
			if (leftDriveStick.getButton(Joystick.ButtonType.kTrigger) && !rightDriveStick.getButton(Joystick.ButtonType.kTrigger) && !driveGearbox.didJustGearDown()) {
				driveGearbox.gearDown();
			} else if (rightDriveStick.getButton(Joystick.ButtonType.kTrigger) && !leftDriveStick.getButton(Joystick.ButtonType.kTrigger) && !driveGearbox.didJustGearUp()) {
				driveGearbox.gearUp();
			} else if (!leftDriveStick.getButton(Joystick.ButtonType.kTrigger) && !rightDriveStick.getButton(Joystick.ButtonType.kTrigger)) {
				driveGearbox.resetGearControls();
			}
		} else {
			if (leftDriveStick.getButton(Joystick.ButtonType.kTrigger) && rightDriveStick.getButton(Joystick.ButtonType.kTrigger)) {
				driveGearbox.setGear(6);
			} else {
				driveGearbox.setGear(0);
			}
		}
	}

	public void handleActiveOperating() {
		if (rightDriveStick.getRawButton(5)) {
			shooter.run(Configuration.shooterGears[shooterSpeed]);
		} else if (leftDriveStick.getRawButton(5)) {
			shooter.stop();
		}
		if (operatorController.getButton(XboxController.Button.X)) {
			conveyor.pickupBalls(-0.40);
		} else if (operatorController.getButton(XboxController.Button.Y)) {
			conveyor.pickupBalls(0.40);
		} else if (operatorController.getButton(XboxController.Button.B)) {
			conveyor.runMidLevel(Relay.Value.kReverse);
		} else if (operatorController.getButton(XboxController.Button.A)) { 
			conveyor.runMidLevel(Relay.Value.kForward);
		} else if (operatorController.getButton(XboxController.Button.RB)) {
			conveyor.stopPickup();
			conveyor.stopMidLevel();
		}
		// shooter.adjustHood(0.13 * operatorController.getAxis(2, 1));
	}

	public void handlePassiveOperating() {
		if (operatorController.getButton(XboxController.Button.Back)) {
			bridgeKnockerDowner.set(Relay.Value.kReverse);
		} else if (operatorController.getButton(XboxController.Button.Start)) {
			bridgeKnockerDowner.set(Relay.Value.kForward);
		} else {
			bridgeKnockerDowner.set(Relay.Value.kOff);
		}
		if (rightDriveStick.getRawButton(11) && shooterSpeed < Configuration.shooterGears.length && !didShooterGearChange) {
			shooterSpeed++;
			Output.say("Up.");
			didShooterGearChange = true;
		} else if (rightDriveStick.getRawButton(10) && shooterSpeed > 0 && !didShooterGearChange) {
			shooterSpeed--;
			Output.say("Down.");
			didShooterGearChange = true;
		} else if (!rightDriveStick.getRawButton(11) && !rightDriveStick.getRawButton(10)) {
			didShooterGearChange = false;
		}
	}
}