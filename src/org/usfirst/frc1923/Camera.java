package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

public class Camera {
	private ImageMath imageMath;
	private CameraDataPacket center;
	private int hueLow = 1;
	private int hueHigh = 1;
	private int saturationLow = 1;
	private int saturationHigh = 1;
	private int intansityLow = 1;
	private int intensityHigh = 1;

	public Camera(ColorImage image) throws NIVisionException {
		imageMath = new ImageMath();
		update(image);

	}

	public void update(ColorImage image) throws NIVisionException {
		ParticleAnalysisReport[] report = image.thresholdHSI(hueLow, hueHigh, saturationLow, saturationHigh, intansityLow, intensityHigh).getOrderedParticleAnalysisReports();
		center = new CameraDataPacket(report[0].center_mass_x, report[0].center_mass_y);
	}

	public double getForce() throws NIVisionException {
		return imageMath.getForce(center);
	}

	public double getAngle() throws NIVisionException {
		return imageMath.getAngle(center);
	}

}
