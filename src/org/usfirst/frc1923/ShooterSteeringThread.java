package org.usfirst.frc1923;

public class ShooterSteeringThread extends Thread {
	private Shooter shooter;
	private CameraDataPacket cdp;
	private CameraDataPacket ocdp;
	private double distance;
	private int height;
	private boolean needsUpdate;
	private boolean isRunning;
	private boolean justRan;
	private boolean die;

	public ShooterSteeringThread(Shooter shooter) {
		this.shooter = shooter;
		this.cdp = null;
		this.needsUpdate = true;
		justRan = false;
	}

	public ShooterSteeringThread(Shooter shooter, CameraDataPacket cdp) {
		this.shooter = shooter;
		this.cdp = cdp;
		this.needsUpdate = false;
		justRan = false;
	}

	public boolean needsUpdate() {
		return needsUpdate;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public boolean justRan() {
		return justRan;
	}

	public boolean didThreadDie() {
		return die;
	}

	public void die() {
		die = true;
	}

	public boolean isCentered() {
		return (!(this.cdp.getX() > 323) && !(this.cdp.getX() < 317));
	}

	public void update(CameraDataPacket cdp) {
		if (this.cdp != cdp) {
			this.cdp = cdp;
			needsUpdate = false;
		} else {
			needsUpdate = true;
		}
	}

	public void run() {
		while ((!this.isCentered()) && !die && shooter.getAutosteering()) {
			distance = CameraDataCalculator.getDistance(cdp);
			height = cdp.getBasketHeight();
			if (!this.needsUpdate()) {
				if (this.cdp.getX() > 326) {
					shooter.adjustRotation(-Configuration.autorotationSpeed);
					needsUpdate = true;
				} else if (this.cdp.getX() < 316) {
					shooter.adjustRotation(Configuration.autorotationSpeed);
					needsUpdate = true;
				} else {
					break;
				}
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
				}
				shooter.adjustRotation(0);
				Output.say("[SST] " + isRunning);
			}
		}
		if (this.isCentered() || die || !shooter.getAutosteering()) {
			distance = CameraDataCalculator.getDistance(cdp);
			height = cdp.getBasketHeight();
			ocdp = cdp;
			cdp = null;
			needsUpdate = true;
			isRunning = false;
			justRan = true;
		}
		Output.say("[SST] " + isRunning);
	}
	
	public int getHeight() {
		return height;
	}
	
	public double getDistance() {
		return distance;
	}

	public CameraDataPacket getDataPacket() {
		return cdp;
	}
	
	public CameraDataPacket getFinalDataPacket() {
		return ocdp;
	}

	public void start() {
		isRunning = true;
		super.start();
	}
}
