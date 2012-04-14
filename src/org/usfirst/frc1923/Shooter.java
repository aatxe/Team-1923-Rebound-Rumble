package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Jaguar;

public class Shooter {
	private Jaguar shooter;
	private Jaguar hood;
	private Jaguar rotaryControl;
	private boolean shooterRunning;
	private boolean autosteering;

	public Shooter(Components components) {
		shooter = components.shooter;
		hood = components.hood;
		rotaryControl = components.rotaryControl;
		shooterRunning = false;
		autosteering = true;
	}

	public void run(double value) {
		shooter.set(-Math.abs(value));
		shooterRunning = true;
	}

	public void stop() {
		shooter.stopMotor();
	}

	public void adjustHood(double value) {
		hood.set(-value);
	}

	public void adjustRotation(double value) {
		rotaryControl.set(value);
	}

	public boolean isShooterRunning() {
		return shooterRunning;
	}

	public boolean getAutosteering() {
		return autosteering;
	}

	public void setAutosteering(boolean autosteering) {
		this.autosteering = autosteering;
	}
}
