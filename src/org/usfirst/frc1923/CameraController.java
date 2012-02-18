package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.camera.AxisCamera;


public class CameraController {
	private AxisCamera camera;
	private Camera imageController;

	public CameraController(){
		try {
			camera = AxisCamera.getInstance();
			imageController = new Camera(camera.getImage());
		} catch (Exception e) {}
	}
	
	public CameraController(Components components) {
		try {
			// camera = components.camera;
			camera = null;
			imageController = new Camera(camera.getImage());
		} catch (Exception e) {}
	}

	public double getForce() {
		try {
			imageController.update(camera.getImage());
			return imageController.getForce();
		} catch (Exception e) {
			return -1;
		}
	}

	public double getAngle() {
		try {
			imageController.update(camera.getImage());
			return imageController.getAngle();
		} catch (Exception e) {
			return -1;
		}
	}

}
