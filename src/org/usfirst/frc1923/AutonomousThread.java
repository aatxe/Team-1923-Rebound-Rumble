package org.usfirst.frc1923;

public class AutonomousThread extends Thread {
	private HybridDriver hybridDriver;
	private boolean hasRan = false;
	
	public AutonomousThread(HybridDriver hybridDriver) {
		this.hybridDriver = hybridDriver;
	}
	
	public void reset() {
		this.hasRan = false;
	}
	
	public void die() {
		this.hasRan = true;
		this.hybridDriver.die();
		this.interrupt();
	}

	public void run() {
		this.hasRan = false;
		if (!hasRan) {
			switch (hybridDriver.getSelectedAutonomous()) { 
				case 1:
					try {
						int distance = 1160;
						double shooterPower = Configuration.sFenderTop;
						hybridDriver.adjustHood(3000, false);
						hybridDriver.drive(distance);
						Thread.sleep(3000);
						hybridDriver.startShooterWheel(shooterPower);
						Thread.sleep(3500);
						hybridDriver.stopDriving();
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
					break;
				case 2:
					try {
						int distance = 1160;
						double shooterPower = Configuration.sFenderMiddle;
						hybridDriver.adjustHood(3000, false);
						hybridDriver.drive(distance);
						Thread.sleep(3000);
						hybridDriver.startShooterWheel(shooterPower);
						Thread.sleep(3500);
						hybridDriver.stopDriving();
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
					break;
				case 3:
					try {
						int delayMillis = 3000;
						double shooterSpeed = Configuration.sKeyTop;
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
					break;
				case 4:
					try {
						int delayMillis = 5000;
						double shooterSpeed = Configuration.sKeyTop;
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
					break;
				case 7:
					try {
						int delayMillis = 0;
						double shooterSpeed = Configuration.sKeyTop;
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
					break;
				case 8: 
					try {
						int delayMillis = 0;
						double shooterSpeed = Configuration.sKeyMiddle;
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
					break;
				case 9:
					try {
						int delayMillis = 0;
						double shooterSpeed = Configuration.sKeyTop;
						// hybridDriver.aimShooter();
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
					break;
			}
		}
	}
}
