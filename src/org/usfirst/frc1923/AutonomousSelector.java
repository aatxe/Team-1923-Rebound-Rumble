package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutonomousSelector {
	private DigitalInput selector[];
	
	public AutonomousSelector(Components components) {
		this.selector = new DigitalInput[4];
		this.selector[0] = components.selectorPortOne;
		this.selector[1] = components.selectorPortTwo;
		this.selector[2] = components.selectorPortThree;
		this.selector[3] = components.selectorPortFour;
	}
	
	public int getAutonomousSelection() {
		int a = (this.selector[3].get()) ? 1 : 0;
		int b = (this.selector[2].get()) ? 1 : 0;
		int c = (this.selector[1].get()) ? 1 : 0;
		int d = (this.selector[0].get()) ? 1 : 0;
		String val = "" + (a * 1000) + (b * 100) + (c * 10) + (d * 1) + "";
		return Integer.parseInt(val, 2);
	}
}
