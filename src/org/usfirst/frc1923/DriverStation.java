package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriverStation extends SmartDashboard {
	public void updateKey(String key, Boolean value) {
		super.putBoolean(key, value.booleanValue());
	}

	public void updateKey(String key, Double value) {
		super.putDouble(key, value.doubleValue());
	}

	public void updateKey(String key, Integer value) {
		super.putInt(key, value.intValue());
	}

	public void updateKey(String key, String value) {
		super.putString(key, value);
	}

	public void updateScreen(Object[] data) {
		int shooterGear = ((Integer) data[3]).intValue();
		int drivingGear = ((Integer) data[4]).intValue();
		this.updateKey("Intake Conveyor", (Boolean) data[0]);
		this.updateKey("Elevator Conveyor", (Boolean) data[1]);
		this.updateKey("Shooter Wheel", (Boolean) data[2]);
		this.updateKey("Shooter Gear", (Integer) data[3]);
		this.updateKey("Shooter Speed", new Double(Configuration.shooterGears[shooterGear]));
		this.updateKey("Driving Gear", (Integer) data[4]);
		this.updateKey("Driving Speed", new Double(Configuration.driveGears[drivingGear]));
		this.updateKey("Left Limit Switch", (Boolean) data[5]);
		this.updateKey("Right Limit Switch", (Boolean) data[6]);
		this.updateKey("Teleop SST Distance", (Double) data[7]);
		this.updateKey("Hybrid SST Distance", (Double) data[8]);
	}
}
