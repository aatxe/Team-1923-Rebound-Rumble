package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;

public class HybridDriver {
	private DriveTrain driveTrain;
	private Shooter shooter;
	private Conveyor conveyor;
	private CameraController cameraController;
	private XboxController operatorController;
	private ShooterSteeringThread sst;
	private AutonomousSelector autsel;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
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
		this.leftEncoder = components.leftEncoder;
		this.rightEncoder = components.rightEncoder;
	}
	
	public void prepare(double speed) {
		this.startShooterWheel(speed);
		this.aimShooter();
	}
	
	public void startShooterWheel(double speed) {
		shooter.run(speed);
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

	public void startElevator() {
		conveyor.runElevator(Relay.Value.kForward);
	}
	
	public void stopElevator() {
		conveyor.stopElevator();
	}

	public void stopEverything() {
		conveyor.stopElevator();
		conveyor.stopIntake();
		shooter.stop();
	}
	
	public void adjustHood(int millis, boolean goUp) {
		new ShooterHoodThread(shooter, millis, goUp).start();
	}
	
	public void drive(int distance) {
		new RobotDrivingThread(driveTrain, leftEncoder, rightEncoder, distance).start();
	}
	
	public int getAverageEncoderValue() {
		return ((this.leftEncoder.get() + this.rightEncoder.get()) / 2);
	}

	public int getSelectedAutonomous() {
		return this.autsel.getAutonomousSelection();
	}

	public boolean isShooterRunning() {
		return shooter.isShooterRunning();
	}

	public ShooterSteeringThread getSST() {
		return sst;
	}
}