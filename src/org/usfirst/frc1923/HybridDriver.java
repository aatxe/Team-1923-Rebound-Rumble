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
	private AutonomousSelector autsel;
	

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
		this.autsel = new AutonomousSelector(components);
	}
	
	public int getSelectedAutonomous() {
		return this.autsel.getAutonomousSelection();
	}
	
	public void prepareShooter() {
		switch (this.autsel.getAutonomousSelection()) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
		}
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