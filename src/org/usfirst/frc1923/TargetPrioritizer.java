package org.usfirst.frc1923;

public class TargetPrioritizer {
	CameraDataPacket[] targets;

	public void update(CameraDataPacket[] targets) {
		this.targets = targets;
		if (targets.length > 0) {
			this.prioritize();
			this.determineHeights();
		}
	}

	protected void prioritize() {
		CameraDataPacket[] prioritizedTargets = new CameraDataPacket[targets.length];
		boolean shouldContinue = true;
		while (shouldContinue) {
			shouldContinue = false;
			for (int i = 0; i < targets.length - 1; i++) {
				if (targets[i].getY() > targets[i + 1].getY()) {
					CameraDataPacket tmpTarget = targets[i];
					targets[i] = targets[i + 1];
					targets[i + 1] = tmpTarget;
					shouldContinue = true;
				}
				prioritizedTargets[i] = targets[i];
			}
			if (prioritizedTargets[prioritizedTargets.length - 1] == null) {
				prioritizedTargets[prioritizedTargets.length - 1] = targets[targets.length - 1];
			}
		}
		for (int i = 0; i < prioritizedTargets.length; i++) {
			targets[i] = prioritizedTargets[i];
		}
	}

	protected void determineHeights() {
		if (targets.length == 1) {
			targets[0].setBasketHeight(1);
		} else if (targets.length == 2) {
			targets[0].setBasketHeight(1);
			targets[0].setBasketHeight(2);
		} else if (targets.length == 3) {
			if (targets[1].getY() + 35 > targets[2].getY() && targets[1].getY() < targets[2].getY() - 35) {
				targets[0].setBasketHeight(1);
				targets[1].setBasketHeight(2);
				targets[2].setBasketHeight(2);
			} else {
				targets[0].setBasketHeight(1);
				targets[1].setBasketHeight(2);
				targets[2].setBasketHeight(3);
			}
		} else if (targets.length == 4) {
			targets[0].setBasketHeight(1);
			targets[1].setBasketHeight(2);
			targets[2].setBasketHeight(2);
			targets[3].setBasketHeight(3);
		}
	}

	public CameraDataPacket[] getTargets() {
		return targets;
	}

	public CameraDataPacket getTarget(int targetNumber) {
		return targets[targetNumber];
	}
}
