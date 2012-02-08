package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Jaguar;

public class Shooter {
	private Jaguar shooter;
	private Jaguar hood;
	
	public Shooter(Components components) {
		shooter = components.shooter;
		hood = components.hood;
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
}
