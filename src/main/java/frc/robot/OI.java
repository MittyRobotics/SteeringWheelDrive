package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;


public class OI {
	private Joystick joystick;
	private SteeringWheel wheel;
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

	public SteeringWheel getSteeringWheel(){
		if(wheel == null){
			wheel = new SteeringWheel(0);
		}
		return wheel;
	}

	private void driveControls(){

	}
}