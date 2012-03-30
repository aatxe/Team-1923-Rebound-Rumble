package org.usfirst.frc1923;

public class ShooterHoodThread extends Thread {
	private Shooter shooter;
	private int millis;
	private boolean goUp;
	
	public ShooterHoodThread(Shooter shooter, int millis, boolean goUp) {
		this.shooter = shooter;
		this.millis = millis;
		this.goUp = goUp;
	}
	
	public void run() {
		try {
			shooter.adjustHood((this.goUp) ? -0.37 : 0.37);
			Thread.sleep(millis);
			shooter.adjustHood(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
