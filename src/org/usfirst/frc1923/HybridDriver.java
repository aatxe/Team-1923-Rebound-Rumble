package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Jaguar;

public class HybridDriver {
	private DriveTrain driveTrain;
	private Shooter shooter;
	private Conveyor conveyor;
	private CameraController cameraController;
	private XboxController operatorController;
	private ShooterSteeringThread sst;

	private DriveGearbox driveGearbox;
	private Jaguar bridgeKnockerDowner;

	public HybridDriver(DriveTrain driveTrain, Configuration config, Shooter shooter, Conveyor conveyor, CameraController cameraController, Components components) {
		this.driveTrain = driveTrain;
		this.shooter = shooter;
		this.conveyor = conveyor;
		this.cameraController = cameraController;
		this.driveGearbox = new DriveGearbox(Configuration.driveGears, components);
		this.bridgeKnockerDowner = components.bridgeKnockerDowner;
		this.operatorController = components.operatorController;
		components.drive.setSafetyEnabled(false);
		this.sst = new ShooterSteeringThread(shooter, operatorController);
	}
	
	public void prepareShooter() {
		//conveyor.startIntake(config.intakeSpeed);
		//if (sst.isCentered()) {
		// shooter.run(CameraDataCalculator.getForce(sst.getDataPacket())); // automatic
		// shooter.run(0.55); // middle hoops front-of-line
		shooter.run(0.66); // top hoops front-of-line
		// shooter.run(0.71); // top hoops foul-line
		// shooter.run(0.83); // top hoops back-of-key (requires hood up)
		//}
	}

	public void aimShooter() {
		try {
			sst = new ShooterSteeringThread(shooter, operatorController);
			cameraController.update();
			sst.update(cameraController.getLowestBasket());
			sst.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shoot() {
		conveyor.runElevator(Relay.Value.kForward);
	}
	
	public void hold() {
		conveyor.runElevator(Relay.Value.kOff);
	}

	public void cleanUp() {
		conveyor.stopElevator();
		conveyor.stopIntake();
		shooter.stop();
	}

	public void getInPositionToGetBalls() {
		// from bridge, not ball hole (saves
		// time, time is money, saves money,
		// budget problem fixed!)
		driveTrain.drive(5, 5); // set up later, need position of robot relative
								// to more balls
		// use the sensors to judge position, and then go/stop/turn/pick up
		// balls
	}

	public boolean isShooterRunning() {
		return shooter.isShooterRunning();
	}

	public ShooterSteeringThread getSST() {
		return sst;
	}
}