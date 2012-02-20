package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

public class HumanDriver {
	private DriveTrain driveTrain;
	private Shooter shooter;
	private Conveyor conveyor;
	private CameraController cameraController;

	private BhavishStick leftDriveStick;
	private BhavishStick rightDriveStick;
	private XboxController operatorController;

	private DriveGearbox driveGearbox;
	private ShooterGearbox shooterGearbox;

	private Relay bridgeKnockerDowner;
	private DigitalInput leftShooterLimit;
	private DigitalInput rightShooterLimit;

	private ShooterSteeringThread sst;
	private boolean shooterRunning = false;

	public HumanDriver(DriveTrain driveTrain, Shooter shooter, Conveyor conveyor, CameraController cameraController, Components components) {
		this.driveTrain = driveTrain;
		this.shooter = shooter;
		this.conveyor = conveyor;
		this.cameraController = cameraController;
		this.driveGearbox = new DriveGearbox(Configuration.driveGears, components);
		this.shooterGearbox = new ShooterGearbox(Configuration.shooterGears, components);
		this.leftDriveStick = components.leftDriveStick;
		this.rightDriveStick = components.rightDriveStick;
		this.operatorController = components.operatorController;
		this.bridgeKnockerDowner = components.bridgeKnockerDowner;
		this.leftShooterLimit = components.leftShooterLimit;
		this.rightShooterLimit = components.rightShooterLimit;
		this.sst = new ShooterSteeringThread(shooter);
		components.drive.setSafetyEnabled(false);
	}

	public void handleActiveDriving() {
		if (Configuration.experimentalDriving) {
			double throttle = -leftDriveStick.getCoalescedY();
			double turning = rightDriveStick.getCoalescedX();
			if (turning > 0.1) {
				driveTrain.drive(throttle + turning, throttle);
			} else if (turning < 0.1) {
				driveTrain.drive(throttle, throttle - turning);
			} else {
				driveTrain.drive(throttle, throttle);
			}
		} else if (Configuration.reversedControls) {
			driveTrain.drive(rightDriveStick.getCoalescedY(), leftDriveStick.getCoalescedY());
		} else {
			driveTrain.drive(-leftDriveStick.getCoalescedY(), -rightDriveStick.getCoalescedY());
		}
	}

	public void handlePassiveDriving() {
		if (Configuration.experimentalDriving) {
			/*
			 * if (rightDriveStick.getRawButton(4)) { driveTrain.drive(0.75,
			 * -0.75); } else if (rightDriveStick.getRawButton(5)) {
			 * driveTrain.drive(-0.75, 0.75); }
			 */
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
		if (sst.isRunning() && sst.needsUpdate()) {
			try {
				sst.update(cameraController.getLowestBasket());
			} catch (Exception e) {
			}
		}
		if (operatorController.getButton(XboxController.Button.LeftClick) && !sst.isRunning()) {
			try {
				shooter.setAutosteering(false);
				sst.die();
				shooter.setAutosteering(true);
				sst = new ShooterSteeringThread(shooter);
				cameraController.update();
				sst.update(cameraController.getLowestBasket());
				sst.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (operatorController.getButton(XboxController.Button.RightClick) && !sst.isRunning()) {
			shooter.run(CameraDataCalculator.getForce(sst.getDataPacket()));
		}
		if (rightDriveStick.getRawButton(5)) {
			shooter.run(shooterGearbox.getSpeed());
			shooterRunning = true;
		} else if (leftDriveStick.getRawButton(5)) {
			shooter.stop();
			shooterRunning = false;
		}
		if (operatorController.getButton(XboxController.Button.A)) {
			conveyor.startIntake(-Configuration.intakeSpeed);
		} else if (operatorController.getButton(XboxController.Button.B)) {
			conveyor.startIntake(Configuration.intakeSpeed);
		} else if (operatorController.getButton(XboxController.Button.Y)) {
			conveyor.runElevator(Relay.Value.kReverse);
		} else if (operatorController.getButton(XboxController.Button.X)) {
			conveyor.runElevator(Relay.Value.kForward);
		} else if (operatorController.getButton(XboxController.Button.RB)) {
			conveyor.stopElevator();
		} else if (operatorController.getTriggerAxis() < -0.1) {
			conveyor.stopIntake();
		}
		if (operatorController.getTriggerAxis() > 0.1) {
			shooter.run(shooterGearbox.getSpeed());
			shooterRunning = true;
		} else if (operatorController.getButton(XboxController.Button.LB)) {
			shooter.stop();
			shooterRunning = false;
		}
		if (operatorController.getAxis(1, 2) > 0.5 && rightShooterLimit.get()) {
			shooter.adjustRotation(-0.20);
		} else if (operatorController.getAxis(1, 2) < -0.5 && leftShooterLimit.get()) {
			shooter.adjustRotation(0.20);
		} else {
			shooter.adjustRotation(0);
		}
		shooter.adjustHood(0.37 * -operatorController.getAxis(2, 1));
	}

	public void handlePassiveOperating() {
		if (operatorController.getButton(XboxController.Button.Back)) {
			bridgeKnockerDowner.set(Relay.Value.kReverse);
		} else if (operatorController.getButton(XboxController.Button.Start)) {
			bridgeKnockerDowner.set(Relay.Value.kForward);
		} else {
			bridgeKnockerDowner.set(Relay.Value.kOff);
		}
		if (rightDriveStick.getRawButton(11) && !shooterGearbox.didJustGearUp()) {
			shooterGearbox.gearUp();
		} else if (rightDriveStick.getRawButton(10) && !shooterGearbox.didJustGearDown()) {
			shooterGearbox.gearDown();
		} else if (operatorController.getDPad() > 0.1 && !shooterGearbox.wasGearJustSet()) {
			shooterGearbox.setGear(shooterGearbox.getGear() + 5);
		} else if (operatorController.getDPad() < -0.1 && !shooterGearbox.wasGearJustSet()) {
			shooterGearbox.setGear(shooterGearbox.getGear() - 5);
		} else if (!rightDriveStick.getRawButton(11) && !rightDriveStick.getRawButton(10) && operatorController.getDPad() < 0.1 && operatorController.getDPad() > -0.1) {
			shooterGearbox.resetGearControls();
		}
	}

	public double getMaxDriveSpeed() {
		return driveGearbox.getSpeed();
	}

	public int getDriveGear() {
		return driveGearbox.getGear();
	}

	public double getShooterSpeed() {
		return shooterGearbox.getSpeed();
	}

	public int getShooterGear() {
		return shooterGearbox.getGear();
	}

	public boolean isShooterRunning() {
		return shooterRunning;
	}
}