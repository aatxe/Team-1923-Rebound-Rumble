package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Relay;

public class HybridDriver {
	private DriveTrain driveTrain;
	private Shooter shooter;
	private Conveyor conveyor;
	private CameraController cameraController;

	private DriveGearbox driveGearbox;
	private Relay bridgeKnockerDowner;

	public HybridDriver(DriveTrain driveTrain, Shooter shooter, Conveyor conveyor, CameraController cameraController, Components components) {
		this.driveTrain = driveTrain;
		this.shooter = shooter;
		this.conveyor = conveyor;
		this.cameraController = cameraController;
		this.driveGearbox = new DriveGearbox(Configuration.driveGears, components);
		this.bridgeKnockerDowner = components.bridgeKnockerDowner;
		components.drive.setSafetyEnabled(false);
	}

	private void prepareShooter() {
		// conveyor shit
		// does opposite of drop balls (ie: takes them to the shooter) ps: so
		// that they can be shot
	}

	private void aimShooter() { // self explanatory
		// Do when tracker-camera thing is made
		// Could also change power if needed
	}

	private void shoot() { // moar self explanatory
		// need to change zero later, it would be bad if we tried shooting at 0
		// power, the ball wouldn't go anywhere EX: 0.4,0.5,0.6,0.7,0.3
		shooter.run(Configuration.shooterGears[0]);
	}

	private void GetInPositionToGetBalls() {
		// from bridge, not ball hole (saves
		// time, time is money, saves money,
		// budget problem fixed!)

		driveTrain.drive(0, 0); // set up later, need position of robot relative
								// to more balls
		// use the sensors to judge position, and then go/stop/turn/pick up
		// balls
	}

	public boolean isShooterRunning() {
		return false;
	}
}