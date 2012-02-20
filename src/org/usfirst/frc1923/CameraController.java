package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.camera.AxisCamera;

public class CameraController {
	private AxisCamera camera;
	private TargetFinder chercheur = new TargetFinder();
	private TargetPrioritizer prioritizer = new TargetPrioritizer();
	
	public CameraController(Components components) {
		camera = components.camera;
	}
	
	public void update() {
		try {
			chercheur.update(camera.getImage());
			prioritizer.update(chercheur.getTargets());
		} catch (Exception e) {}
	}
	
	public CameraDataPacket getHighestBasket() {
		CameraDataPacket[] targets = prioritizer.getTargets();
		for (int i = 0; i < targets.length - 1; i++) {
			if (targets[i].getBasketHeight() == 3) {
				return targets[i];
			}
		}
		return null;
	}
	
	public CameraDataPacket getMiddleBasket(char position) {
		CameraDataPacket[] targets = prioritizer.getTargets();
		CameraDataPacket bhavish = new CameraDataPacket(320, 200);
		if (targets.length == 2) {
			bhavish = targets[1];
		} else if (targets.length == 4) {
			if (position == 'l') {
				bhavish = targets[1];
			} else if (position == 'r') {
				bhavish = targets[2];
			}
		} else if (targets.length == 3) {
			if (targets[2].getBasketHeight() == 2) {
				if (position == 'l') {
					bhavish = targets[1];
				} else if (position == 'r') {
					bhavish = targets[2];
				}
			} else {
				bhavish = targets[1];
			}
		}
		return bhavish;
	}
	
	public CameraDataPacket getLowestBasket() {
		prioritizer.update(chercheur.getTargets());
		CameraDataPacket[] targets = prioritizer.getTargets();
		return targets[0];
	}
}
