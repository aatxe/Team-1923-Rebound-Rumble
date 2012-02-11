package org.usfirst.frc1923;

public class Coalescor {
	private double oldValue;
	private double epsilon = 0.08;

	public Coalescor() {
		this.oldValue = 0;
	}

	public Coalescor(double oldValue) {
		this.oldValue = oldValue;
	}

	public double coalesce(double x) {
		if (x > oldValue) {
			if (oldValue + epsilon > x) {
				oldValue = x;
			} else if (oldValue + epsilon < x) {
				oldValue = oldValue + epsilon;
			}
		} else if (x < oldValue) {
			if (oldValue - epsilon < x) {
				oldValue = x;
			} else if (oldValue - epsilon > x) {
				oldValue = oldValue - epsilon;
			}
		}
		return (Math.floor(this.oldValue * 1000) / 1000);
	}

	public double getEpsilon() {
		return this.epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}
}
