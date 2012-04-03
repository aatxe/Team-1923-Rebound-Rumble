package org.usfirst.frc1923;

public class AutonomousThread extends Thread {
	private HybridDriver hybridDriver;
	
	public AutonomousThread(HybridDriver hybridDriver) {
		this.hybridDriver = hybridDriver;
	}
	
	public void die() {
		this.hybridDriver.die();
		this.interrupt();
	}

	public void run() {
		switch (hybridDriver.getSelectedAutonomous()) { 
			case 1:
				try {
					int distance = 1000;
					double shooterPower = 0.50;
					hybridDriver.adjustHood(3000, true);
					hybridDriver.drive(distance);
					Thread.sleep(3000);
					hybridDriver.startShooterWheel(shooterPower);
					Thread.sleep(3500);
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopElevator();
					Thread.sleep(3500);
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopEverything();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			case 2:
				try {
					int distance = 1000;
					double shooterPower = 0.38;
					hybridDriver.adjustHood(3000, true);
					hybridDriver.drive(distance);
					Thread.sleep(3000);
					hybridDriver.startShooterWheel(shooterPower);
					Thread.sleep(3500);
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopElevator();
					Thread.sleep(3500);
					hybridDriver.startElevator();
					Thread.sleep(1500);
					hybridDriver.stopEverything();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			case 3:
				try {
					int delayMillis = 3000;
					double shooterSpeed = 0.59;
					// hybridDriver.aimShooter();
					hybridDriver.adjustHood(3000, true);
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
					double shooterSpeed = 0.59;
					// hybridDriver.aimShooter();
					hybridDriver.adjustHood(3000, true);
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
			case 7:
				try {
					int delayMillis = 0;
					double shooterSpeed = 0.62;
					// hybridDriver.aimShooter(false);
					hybridDriver.adjustHood(3000, true);
					Thread.sleep(delayMillis);
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
			case 8: 
				try {
					int delayMillis = 0;
					double shooterSpeed = 0.50;
					// hybridDriver.aimShooter(false);
					hybridDriver.adjustHood(3000, true);
					Thread.sleep(delayMillis);
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
			case 9:
				try {
					int delayMillis = 0;
					double shooterSpeed = 0.59;
					Thread.sleep(delayMillis);
					// hybridDriver.aimShooter();
					hybridDriver.adjustHood(3000, true);
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
