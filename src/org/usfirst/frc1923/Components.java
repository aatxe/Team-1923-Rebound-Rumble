package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.camera.AxisCamera;

public class Components {
	public Watchdog kaynine;
	public final AxisCamera camera = AxisCamera.getInstance("10.19.23.11");
	public final RobotDrive drive = new RobotDrive(2, 1, 4, 3);
	public final Jaguar shooter = new Jaguar(5);
	public final Jaguar hood = new Jaguar(8);
	public final Jaguar rotaryControl = new Jaguar(6);
	public final DigitalInput leftShooterLimit = new DigitalInput(1);
	public final DigitalInput rightShooterLimit = new DigitalInput(2);
	public final Jaguar pickupConveyorLeft = new Jaguar(9);
	public final Jaguar pickupConveyorRight = new Jaguar(10);
	public final Relay middleConveyor = new Relay(2);
	public final BhavishStick leftDriveStick = new BhavishStick(1);
	public final BhavishStick rightDriveStick = new BhavishStick(2);
	public final XboxController operatorController = new XboxController(3);
	public final Jaguar bridgeKnockerDowner = new Jaguar(7);
	public final DigitalInput selectorPortOne = new DigitalInput(11);
	public final DigitalInput selectorPortTwo = new DigitalInput(12);
	public final DigitalInput selectorPortThree = new DigitalInput(13);
	public final DigitalInput selectorPortFour = new DigitalInput(14);
}
