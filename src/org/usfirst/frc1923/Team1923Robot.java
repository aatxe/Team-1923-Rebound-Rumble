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

	public void robotInit() {
		Output.say("Robot Initialized.");
		components.kaynine = getWatchdog();
		driverStation.updateScreen(this.getDriverStationData());
	}

	public void disabledInit() {
		Output.say("Robot Disabled.");
		driverStation.updateScreen(this.getDriverStationData());
	}

	public void disabledPeriodic() {
		// do shit.
	}

	public void disabledContinuous() {
		// do shit.
	}

	public void autonomousInit() {
		Output.say("Robot Enabled:: Hybrid Mode Initialized.");
		driverStation.updateScreen(this.getDriverStationData());
	}

	public void autonomousPeriodic() {
		driverStation.updateScreen(this.getDriverStationData());
	}

	public void autonomousContinuous() {
		// do shit.
	}

	public void teleopInit() {
		Output.say("Robot Enabled:: Tele-Op Mode Initialized.");
		driverStation.updateScreen(this.getDriverStationData());
	}

	public void teleopPeriodic() {
		humanDriver.handlePassiveDriving();
		humanDriver.handlePassiveOperating();
		driverStation.updateScreen(this.getDriverStationData());
	}

	public void teleopContinuous() {
		humanDriver.handleActiveDriving();
		humanDriver.handleActiveOperating();
	}
	
	public Object[] getDriverStationData() {
		Object[] data = new Object[5];
		data[0] = (Object) new Boolean(conveyor.isIntakeRunning());
		data[1] = (Object) new Boolean(conveyor.isElevatorRunning());
		data[2] = (Object) new Boolean((humanDriver.isShooterRunning() || hybridDriver.isShooterRunning()));
		data[3] = (Object) new Integer(humanDriver.getShooterGear());
		data[4] = (Object) new Integer(humanDriver.getDriveGear());
		return data;
	}
}
