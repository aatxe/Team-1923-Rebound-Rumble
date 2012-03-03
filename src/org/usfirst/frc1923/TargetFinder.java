package org.usfirst.frc1923;

import java.util.Vector;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

public class TargetFinder {
	private Vector targets = new Vector();
	private int redLow = 0;
	private int redHigh = 80;
	private int greenLow = 110;
	private int greenHigh = 205;
	private int blueLow = 55;
	private int blueHigh = 220;

	public final double areaThreshold = 444;
	public final double rectangularityThreshold = 70;
	public final double aspectThreshold = 0;

	public double rectangularityScore(double area, double width, double height) {
		double rectangularityScore = area;
		rectangularityScore /= width;
		rectangularityScore /= height;
		rectangularityScore *= 100;
		return rectangularityScore;
	}

	public double aspectRatioScore(double width, double height) {
		double aspectRatioScore = width / height;
		aspectRatioScore /= 1.33;
		aspectRatioScore = Math.abs(1.0 - aspectRatioScore);
		aspectRatioScore = 100.0 * (1.0 - aspectRatioScore);
		if (aspectRatioScore > 100) {
			aspectRatioScore = 100;
		} else if (aspectRatioScore < 0) {
			aspectRatioScore = 0;
		}
		return aspectRatioScore;
	}

	public void update(ColorImage image) {
		BinaryImage masked;
		BinaryImage hulled;
		BinaryImage filtered;
		ParticleAnalysisReport[] all;
		targets.removeAllElements();
		try {
			masked = image.thresholdRGB(redLow, redHigh, greenLow, greenHigh, blueLow, blueHigh);
			hulled = masked.convexHull(true);
			filtered = hulled.removeSmallObjects(true, 2);
			all = filtered.getOrderedParticleAnalysisReports(10);
			image.free();
			filtered.free();
			hulled.free();
			masked.free();
			for (int i = 0; i < all.length; i++) {
				if (all[i].particleArea > areaThreshold) {
					double rectangularityScore = rectangularityScore(all[i].particleArea, all[i].boundingRectWidth, all[i].boundingRectHeight);
					double aspectRatioScore = aspectRatioScore(all[i].boundingRectWidth, all[i].boundingRectHeight);
					if (rectangularityScore > rectangularityThreshold && aspectRatioScore > aspectThreshold) {
						targets.addElement(all[i]);
					}
				}
			}
		} catch (NIVisionException e) {
			Output.queue("TargetFinder:: Failed to update()");
			e.printStackTrace();
		}
		Output.queue("[TargetFinder] " + targets.size());
	}

	public int getNumberOfTargets() {
		return targets.size();
	}

	public CameraDataPacket[] getTargets() {
		CameraDataPacket[] targets = new CameraDataPacket[this.targets.size()];
		for (int i = 0; i < targets.length; i++) {
			targets[i] = new CameraDataPacket(((ParticleAnalysisReport) this.targets.elementAt(i)).center_mass_x, ((ParticleAnalysisReport) this.targets.elementAt(i)).center_mass_y, ((ParticleAnalysisReport) this.targets.elementAt(i)).boundingRectHeight, ((ParticleAnalysisReport) this.targets.elementAt(i)).boundingRectWidth);
			Output.queue("[TargetFinder] targets[" + i + "] (" + ((ParticleAnalysisReport) this.targets.elementAt(i)).center_mass_x + ", " + ((ParticleAnalysisReport) this.targets.elementAt(i)).center_mass_y + ")");
		}
		return targets;
	}

	public CameraDataPacket getTarget(int targetNumber) {
		return new CameraDataPacket(((ParticleAnalysisReport) this.targets.elementAt(targetNumber)).center_mass_x, ((ParticleAnalysisReport) this.targets.elementAt(targetNumber)).center_mass_y);
	}
}
