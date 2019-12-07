package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	public double ticksPerInches = 2101.0/(4*Math.PI);
	private WPI_TalonSRX[] leftDrive = new WPI_TalonSRX[2];
	private WPI_TalonSRX[] rightDrive = new WPI_TalonSRX[2];
	private static DriveTrain ourInstance = new DriveTrain();
//	private PIDController controller = new PIDController(PID.TURN[0], PID.TURN[1], PID.TURN[2], Gyro.getInstance(), leftDrive[0]);

	public static DriveTrain getInstance() {
		return ourInstance;
	}


	private DriveTrain() {
		super("DriveTrain");
	}

	public void initHardware() {
		leftDrive[0] = new WPI_TalonSRX(20);
		leftDrive[1] = new WPI_TalonSRX(21);
		rightDrive[0] = new WPI_TalonSRX(23);
		rightDrive[1] = new WPI_TalonSRX(22);

		leftDrive[0].setInverted(false);
		leftDrive[1].setInverted(false);
		rightDrive[0].setInverted(true);
		rightDrive[1].setInverted(true);

		leftDrive[0].configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
		rightDrive[0].configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

		leftDrive[0].setSensorPhase(false);
		rightDrive[0].setSensorPhase(true);

		leftDrive[1].set(ControlMode.Follower, leftDrive[0].getDeviceID());
		rightDrive[0].set(ControlMode.Follower, rightDrive[1].getDeviceID());

		leftDrive[0].config_kP(0, PID.DRIVE_VELOCITY[0]);
		leftDrive[0].config_kI(0, PID.DRIVE_VELOCITY[1]);
		leftDrive[0].config_kD(0, PID.DRIVE_VELOCITY[2]);
		rightDrive[1].config_kP(0, PID.DRIVE_VELOCITY[0]);
		rightDrive[1].config_kI(0, PID.DRIVE_VELOCITY[1]);
		rightDrive[1].config_kD(0, PID.DRIVE_VELOCITY[2]);

		leftDrive[0].setNeutralMode(NeutralMode.Brake);
		leftDrive[1].setNeutralMode(NeutralMode.Brake);
		rightDrive[0].setNeutralMode(NeutralMode.Brake);
		rightDrive[1].setNeutralMode(NeutralMode.Brake);
	}

	@Override
	public void initDefaultCommand() {

	}

	public void tankVelocity(double left, double right) {
		left *=  2101.0/(4*Math.PI);
		right *= 2101.0/(4*Math.PI);
		leftDrive[0].set(ControlMode.Velocity, (left / 10));
		rightDrive[1].set(ControlMode.Velocity, (right/ 10));
	}

	public void tankPosition(double left, double right) {
		left *=  2101.0/(4*Math.PI);
		right *= 2101.0/(4*Math.PI);
		leftDrive[0].set(ControlMode.Position, left);
		rightDrive[1].set(ControlMode.Position, right);
	}

	public double getLeftEncoderPosition(){

		return leftDrive[0].getSelectedSensorPosition()/ticksPerInches;

	}

	public double getRightEncoderPosition(){
		return rightDrive[1].getSelectedSensorPosition()/ticksPerInches;
	}

	public double getLeftEncoderVelocity(){

		return leftDrive[0].getSelectedSensorVelocity()/ticksPerInches;

	}

	public double getRightEncoderVelocity(){

		return rightDrive[1].getSelectedSensorVelocity()/ticksPerInches;
	}

	public void resetLeftEncoder(){
		leftDrive[0].setSelectedSensorPosition(0);
	}

	public void resetRightEncoder(){
		rightDrive[1].setSelectedSensorPosition(0);
	}



}