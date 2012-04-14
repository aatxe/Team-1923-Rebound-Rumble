package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Team1923Robot extends IterativeRobot {
	private Components components = new Components();

	private CameraController cameraController = new CameraController(components);
	private Conveyor conveyor = new Conveyor(components);
	private DriveTrain driveTrain = new DriveTrain(components);
	private DriverStation driverStation = new DriverStation();
	private Shooter shooter = new Shooter(components);

	private HumanDriver humanDriver = new HumanDriver(driveTrain, shooter, conveyor, cameraController, components);
	private HybridDriver hybridDriver = new HybridDriver(driveTrain, shooter, conveyor, cameraController, components);

	private AutonomousThread at;
	
	public void robotInit() {
		Output.queue("Robot Initialized.");
		components.kaynine = getWatchdog();
		driverStation.updateScreen(this.getDriverStationData());
		cameraController.update();
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					Output.speak();
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {}
				}
			}
		}).start();
	}

	public void disabledInit() {
		Output.queue("Robot Disabled.");
		driverStation.updateScreen(this.getDriverStationData());
		cameraController.update();
	}

	public void disabledPeriodic() {
		// do shit.
	}

	public void disabledContinuous() {
		// do shit.
	}

	public void autonomousInit() {
		Output.queue("Robot Enabled:: Hybrid Mode Initialized.");
		driverStation.updateScreen(this.getDriverStationData());
		cameraController.update();
		at = new AutonomousThread(hybridDriver);
		at.start();
	}

	public void autonomousPeriodic() {
		driverStation.updateScreen(this.getDriverStationData());
	}

	public void autonomousContinuous() {
		// do shit.
	}

	public void teleopInit() {
		Output.queue("Robot Enabled:: Tele-Op Mode Initialized.");
		driverStation.updateScreen(this.getDriverStationData());
		cameraController.update();
		try {
			at.die(); // Kill autonomous.
			at.die(); // Die. seriously.
			at.die(); // seriously.
		} catch (NullPointerException e) {}
		humanDriver.stopEverything();
	}

	public void teleopPeriodic() {
		humanDriver.handlePassiveDriving();
		humanDriver.handlePassiveOperating();
		driverStation.updateScreen(this.getDriverStationData());
	}

	public void teleopContinuous() {
		humanDriver.handleActiveDriving();
		humanDriver.handleActiveOperating();
		humanDriver.runShooter();
	}

	public Object[] getDriverStationData() {
		Object[] data = new Object[10];
		data[0] = new Boolean(conveyor.isIntakeRunning());
		data[1] = new Boolean(conveyor.isElevatorRunning());
		data[2] = new Boolean((humanDriver.isShooterRunning() || hybridDriver.isShooterRunning()));
		data[3] = new Integer(humanDriver.getShooterGear());
		data[4] = new Integer(humanDriver.getDriveGear());
		data[5] = new Boolean(components.leftShooterLimit.get());
		data[6] = new Boolean(components.rightShooterLimit.get());
		data[7] = new Double(CameraDataCalculator.getDistance(humanDriver.getSST().getDataPacket()));
		data[8] = new Double(CameraDataCalculator.getDistance(hybridDriver.getSST().getDataPacket()));
		data[9] = new Integer(hybridDriver.getSelectedAutonomous());
		return data;
	}
}
