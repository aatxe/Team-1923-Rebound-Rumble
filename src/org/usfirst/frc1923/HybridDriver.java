package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Relay;

public class HybridDriver {
	private DriveTrain driveTrain;
	private Shooter shooter;
	private Conveyor conveyor;
	private CameraController cameraController;
	private ShooterSteeringThread sst;

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
		this.sst = new ShooterSteeringThread(shooter);
	}

	private void prepareShooter() {
		conveyor.runElevator(Relay.Value.kForward);
	}

	private void aimShooter() {
		try {
			sst = new ShooterSteeringThread(shooter);
			cameraController.update();
			sst.update(cameraController.getLowestBasket());
			sst.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void shoot() { 
		shooter.run(CameraDataCalculator.getForce(sst.getDataPacket()));
	}

	private void GetInPositionToGetBalls() {
//		driveTrain.drive(5, 5); 
//		//Knocker-Downer Down
//		bridgeKnockerDowner.set(Relay.Value.kForward);
//		//Intake on
//		conveyor.startIntake(-Configuration.intakeSpeed);
//		//Intake off
//		conveyor.stopIntake();
//		//Drive to Key
//		
//		//Elevator on
//		this.prepareShooter();
//		//Aim and shoot
//		this.aimShooter();
//		this.shoot();
	}

	public boolean isShooterRunning() {
		return shooter.isShooterRunning();
	}
}