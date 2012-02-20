package org.usfirst.frc1923;

import edu.wpi.first.wpilibj.Joystick;

public class XboxController {
	private Joystick xboxController;

	public static class Button {
		public final int value;
		public static final int kA_val = 1;
		public static final int kB_val = 2;
		public static final int kX_val = 3;
		public static final int kY_val = 4;
		public static final int kLB_val = 5;
		public static final int kRB_val = 6;
		public static final int kStart_val = 8;
		public static final int kBack_val = 7;
		public static final int kLeftClick_val = 9;
		public static final int kRightClick_val = 10;
		public static final Button A = new Button(kA_val);
		public static final Button B = new Button(kB_val);
		public static final Button X = new Button(kX_val);
		public static final Button Y = new Button(kY_val);
		public static final Button LB = new Button(kLB_val);
		public static final Button RB = new Button(kRB_val);
		public static final Button Start = new Button(kStart_val);
		public static final Button Back = new Button(kBack_val);
		public static final Button LeftClick = new Button(kLeftClick_val);
		public static final Button RightClick = new Button(kRightClick_val);

		private Button(int value) {
			this.value = value;
		}
	}

	public XboxController(int port) {
		xboxController = new Joystick(port);
	}

	public boolean getButton(XboxController.Button inputButton) {
		return xboxController.getRawButton(inputButton.value);
	}

	public Joystick getJoystick() {
		return xboxController;
	}

	public double getAxis(int stickNumber, int axisNumber) {
		int axes[] = {2, 1, 5, 4};
		int fAxis = 0;
		if (stickNumber == 1 && axisNumber == 1) {
			fAxis = axes[0];
		} else if (stickNumber == 1 && axisNumber == 2) {
			fAxis = axes[1];
		} else if (stickNumber == 2 && axisNumber == 1) {
			fAxis = axes[2];
		} else if (stickNumber == 2 && axisNumber == 2) {
			fAxis = axes[3];
		} else {
			return -74;
		}
		return xboxController.getRawAxis(fAxis);
	}

	public double getTriggerAxis() {
		return xboxController.getRawAxis(3);
	}

	public double getDPad() {
		return xboxController.getRawAxis(6);
	}
}
