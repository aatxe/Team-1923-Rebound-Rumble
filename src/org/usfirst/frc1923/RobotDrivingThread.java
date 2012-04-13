package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Encoder;

public class RobotDrivingThread extends Thread {
	private DriveTrain driveTrain;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private int distance;
	private boolean shouldDie;
	
	public RobotDrivingThread(DriveTrain driveTrain, Encoder leftEncoder, Encoder rightEncoder, int distance) {
		this.driveTrain = driveTrain;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.distance = distance;
	}
	
	protected int getAverageEncoderValue() {
		return ((this.leftEncoder.get() + this.rightEncoder.get()) / 2);
	}
	
	public void run() {
		this.shouldDie = false;
		while (this.getAverageEncoderValue() <= distance && !this.shouldDie()) {
			if (this.leftEncoder.get() > this.rightEncoder.get()) {
				driveTrain.drive(Configuration.autonomousDriveSpeed, Configuration.autonomousDriveSpeed + .0375);
			} else if (this.leftEncoder.get() < this.rightEncoder.get()) {
				driveTrain.drive(Configuration.autonomousDriveSpeed + .0375, Configuration.autonomousDriveSpeed);
			} else {
				driveTrain.drive(Configuration.autonomousDriveSpeed, Configuration.autonomousDriveSpeed);
			}
		}
		if (this.getAverageEncoderValue() >= distance || this.shouldDie()) {
			driveTrain.stop();
		}
	}
	
	protected boolean shouldDie() {
		return (this.shouldDie);
	}
	
	public void die() {
		this.shouldDie = true;
	}
}
