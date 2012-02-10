package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

public class HumanDriver {
	private Shooter shooter;
	private BhavishStick leftDriveStick;
	private BhavishStick rightDriveStick;
	private XboxController operatorController;
	private Gearbox driveGearbox;
	private DriveTrain driveTrain;
	private Relay bridgeKnockerDowner;

	private boolean wtf = false;
	private int shooterSpeed = 0;

	public HumanDriver(DriveTrain driveTrain, Shooter shooter,
			Components components) {
		this.driveTrain = driveTrain;
		this.shooter = shooter;
		driveGearbox = new Gearbox(Configuration.driveGears, components);
		leftDriveStick = components.leftDriveStick;
		rightDriveStick = components.rightDriveStick;
		operatorController = components.operatorController;
		bridgeKnockerDowner = components.bridgeKnockerDowner;
		components.drive.setSafetyEnabled(false);
	}

	public void handleActiveDriving() {
		if (Configuration.reversedControls) {
			driveTrain.drive(rightDriveStick.getCoalescedY(),
					leftDriveStick.getCoalescedY());
		} else {
			double left = -leftDriveStick.getCoalescedY();
			double right = -rightDriveStick.getCoalescedY();
			Output.say("L: " + left);
			Output.say("R: " + right);
			driveTrain.drive(left, right);
		}
	}

	public void handlePassiveDriving() {
		if (Configuration.gearShifter) {
			if (leftDriveStick.getButton(Joystick.ButtonType.kTrigger)
					&& !rightDriveStick.getButton(Joystick.ButtonType.kTrigger)
					&& !driveGearbox.didJustGearDown()) {
				driveGearbox.gearDown();
			} else if (rightDriveStick.getButton(Joystick.ButtonType.kTrigger)
					&& !leftDriveStick.getButton(Joystick.ButtonType.kTrigger)
					&& !driveGearbox.didJustGearUp()) {
				driveGearbox.gearUp();
			} else if (!leftDriveStick.getButton(Joystick.ButtonType.kTrigger)
					&& !rightDriveStick.getButton(Joystick.ButtonType.kTrigger)) {
				driveGearbox.resetGearControls();
			}
		} else {
			if (leftDriveStick.getButton(Joystick.ButtonType.kTrigger)
					&& rightDriveStick.getButton(Joystick.ButtonType.kTrigger)) {
				driveGearbox.setGear(6);
			} else {
				driveGearbox.setGear(0);
			}
		}
	}

	public void handleActiveOperating() {
		if (rightDriveStick.getRawButton(5)) {
			shooter.run(Configuration.shooterGears[shooterSpeed]);
		} else if (rightDriveStick.getRawButton(4)) {
			shooter.stop();
		}
		shooter.adjustHood(0.13 * operatorController.getAxis(2, 1));
	}

	public void handlePassiveOperating() {
		if (leftDriveStick.getRawButton(3)) {
			bridgeKnockerDowner.set(Relay.Value.kReverse);
		} else if (rightDriveStick.getRawButton(3)) {
			bridgeKnockerDowner.set(Relay.Value.kForward);
		} else {
			bridgeKnockerDowner.set(Relay.Value.kOff);
		}
		if (rightDriveStick.getRawButton(11)
				&& shooterSpeed < Configuration.shooterGears.length) {
			shooterSpeed++;
			wtf = true;
		} else if (rightDriveStick.getRawButton(10) && shooterSpeed > 0) {
			shooterSpeed--;
			wtf = true;
		} else if (!rightDriveStick.getRawButton(11)
				&& !rightDriveStick.getRawButton(10)) {
			wtf = false;
		}
	}
}