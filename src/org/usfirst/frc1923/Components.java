package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.camera.AxisCamera;

public class Components {
	public Watchdog kaynine;
	public final AxisCamera camera = AxisCamera.getInstance();
	public final RobotDrive drive = new RobotDrive(2, 1, 4, 3);
	public final Jaguar shooter = new Jaguar(5);
	public final Jaguar hood = new Jaguar(6);
	public final Jaguar pickupConveyorLeft = new Jaguar(9);
	public final Jaguar pickupConveyorRight = new Jaguar(10);
	public final Relay middleConveyor = new Relay(2);
	public final BhavishStick leftDriveStick = new BhavishStick(1);
	public final BhavishStick rightDriveStick = new BhavishStick(2);
	public final XboxController operatorController = new XboxController(3);
	public final Relay bridgeKnockerDowner = new Relay(1);
	public final Gyro gyroscope = new Gyro(1);
}
