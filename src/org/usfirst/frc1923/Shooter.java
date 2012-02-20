package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Jaguar;

public class Shooter {
	private Jaguar shooter;
	private Jaguar hood;
	private Jaguar rotaryControl;

	public Shooter(Components components) {
		shooter = components.shooter;
		hood = components.hood;
		rotaryControl = components.rotaryControl;
	}

	public void run(double value) {
		shooter.set(value);
	}

	public void stop() {
		shooter.stopMotor();
	}

	public void adjustHood(double value) {
		hood.set(value);
	}

	public void adjustRotation(double value) {
		rotaryControl.set(value);
	}
}
