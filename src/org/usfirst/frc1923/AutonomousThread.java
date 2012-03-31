package org.usfirst.frc1923;

public class AutonomousThread extends Thread {
	private HybridDriver hybridDriver;
	
	public AutonomousThread(HybridDriver hybridDriver) {
		this.hybridDriver = hybridDriver;
	}

	public void run() {
		switch (hybridDriver.getSelectedAutonomous()) { 
			case 1:
				try {
					int distance = 1514;
					int shooterDistance = 500;
					double shooterPower = 0.53;
					hybridDriver.adjustHood(3000, true);
					hybridDriver.drive(distance);
					while (hybridDriver.getAverageEncoderValue() < shooterDistance) {
						Thread.sleep(25);
					}
					hybridDriver.startShooterWheel(shooterPower);
					while (hybridDriver.getAverageEncoderValue() < distance) {
						Thread.sleep(25);
					}
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopElevator();
					Thread.sleep(4000);
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopEverything();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			case 2:
				try {
					int distance = 1514;
					int shooterDistance = 500;
					double shooterPower = 0.38;
					hybridDriver.adjustHood(3000, true);
					hybridDriver.drive(distance);
					while (hybridDriver.getAverageEncoderValue() < shooterDistance) {
						Thread.sleep(25);
					}
					hybridDriver.startShooterWheel(shooterPower);
					while (hybridDriver.getAverageEncoderValue() < distance) {
						Thread.sleep(25);
					}
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopElevator();
					Thread.sleep(4000);
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopEverything();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			case 3:
				try {
					int delayMillis = 3000;
					double shooterSpeed = 0.63;
					hybridDriver.aimShooter();
					Thread.sleep(delayMillis);
					hybridDriver.startShooterWheel(shooterSpeed);
					Thread.sleep(3000);
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopElevator();
					Thread.sleep(4000);
					hybridDriver.startElevator();
					Thread.sleep(3000);
					hybridDriver.stopEverything();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			case 4:
				try {
					int delayMillis = 5000;
					double shooterSpeed = 0.63;
					hybridDriver.aimShooter();
					Thread.sleep(delayMillis);
					hybridDriver.startShooterWheel(shooterSpeed);
					Thread.sleep(3000);
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopElevator();
					Thread.sleep(4000);
					hybridDriver.startElevator();
					Thread.sleep(3000);
					hybridDriver.stopEverything();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			case 9:
				try {
					int delayMillis = 0;
					double shooterSpeed = 0.66;
					Thread.sleep(delayMillis);
					hybridDriver.aimShooter();
					hybridDriver.startShooterWheel(shooterSpeed);
					Thread.sleep(4000);
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopElevator();
					Thread.sleep(4000);
					hybridDriver.startElevator();
					Thread.sleep(3000);
					hybridDriver.stopEverything();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
}
