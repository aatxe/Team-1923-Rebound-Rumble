package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Team1923Robot extends IterativeRobot {
	private Components components = new Components();
	private DriveTrain driveTrain = new DriveTrain(components);
	private Shooter shooter = new Shooter(components);
	private Conveyor conveyor = new Conveyor(components);
	private HumanDriver humanDriver = new HumanDriver(driveTrain, shooter, conveyor, components);

	public void robotInit() {
		Output.say("Robot Initialized");
		components.kaynine = getWatchdog();
	}

	public void disabledInit() {
		// do shit.
	}

	public void disabledPeriodic() {
		// do shit.
	}

	public void disabledContinuous() {
		// do shit.
	}

	public void autonomousInit() {
		Output.say("Hybrid Mode Initialized");
	}

	public void autonomousPeriodic() {
		// do shit.
	}

	public void autonomousContinuous() {
		// do shit.
	}

	public void teleopInit() {
		Output.say("Tele-Op Mode Initialized");
	}

	public void teleopPeriodic() {
		humanDriver.handlePassiveDriving();
		humanDriver.handlePassiveOperating();
	}

	public void teleopContinuous() {
		// components.kaynine.feed();
		humanDriver.handleActiveDriving();
		humanDriver.handleActiveOperating();
	}
}
