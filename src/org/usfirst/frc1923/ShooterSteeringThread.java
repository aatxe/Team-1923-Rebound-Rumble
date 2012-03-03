package org.usfirst.frc1923;

public class ShooterSteeringThread extends Thread {
	private Shooter shooter;
	private XboxController operatorController;
	private CameraDataPacket cdp;
	private CameraDataPacket ocdp;
	private double distance;
	private int height;
	private boolean needsUpdate;
	private boolean isRunning;
	private boolean justRan;
	private boolean die;

	public ShooterSteeringThread(Shooter shooter, XboxController operatorController) {
		this.shooter = shooter;
		this.operatorController = operatorController;
		this.cdp = null;
		this.needsUpdate = true;
		justRan = false;
	}

	public ShooterSteeringThread(Shooter shooter, XboxController operatorController, CameraDataPacket cdp) {
		this.shooter = shooter;
		this.operatorController = operatorController;
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
		while ((!this.isCentered()) && !die && shooter.getAutosteering() && operatorController.getAxis(1, 2) < 0.1 && operatorController.getAxis(1, 2) > -0.1) {
			distance = CameraDataCalculator.getDistance(cdp);
			height = cdp.getBasketHeight();
			if (!this.needsUpdate()) {
				if (this.cdp.getX() > 320 + Configuration.autorotationMargin) {
					shooter.adjustRotation(-Configuration.autorotationSpeed);
					needsUpdate = true;
				} else if (this.cdp.getX() < 320 - Configuration.autorotationMargin) {
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
				Output.queue("[SST] " + isRunning);
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
		Output.queue("[SST] " + isRunning);
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
