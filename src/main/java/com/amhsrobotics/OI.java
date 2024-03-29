package com.amhsrobotics;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;


public class OI {
	private Joystick joystick;
	private SteeringWheel wheel;
	private XboxController xboxController;
	private static OI ourInstance = new OI();

	public static OI getInstance() {
		return ourInstance;
	}

	private OI() {
		driveControls();
	}

	public Joystick getJoystick(){
		if(joystick == null){
			joystick = new Joystick(1);
		}
		return joystick;
	}

	public XboxController getXboxController(){
		if(xboxController == null) {
			xboxController = new XboxController(2);
		}
		return xboxController;
	}

	public SteeringWheel getSteeringWheel(){
		if(wheel == null){
			wheel = new SteeringWheel(0);
		}
		return wheel;
	}

	private void driveControls(){

	}
}