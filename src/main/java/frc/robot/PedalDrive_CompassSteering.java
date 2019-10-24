package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.DriveTrain;
import frc.robot.OI;

public class PedalDrive_CompassSteering extends Command { //example command
    PIDController controller;

    private double turn;
    private double e = 0.7;

    public PedalDrive_CompassSteering(){
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void initialize(){
        PIDOutput pidOutput = new PIDOutput() {
            @Override
            public void pidWrite(double output) {

            }
        };
        controller = new PIDController(PID.TURN[0], PID.TURN[1], PID.TURN[2], Gyro.getInstance(), pidOutput);
        controller.setInputRange(0, 360);
        controller.setOutputRange(-1, 1);
        controller.enable();

    }

    @Override
    protected void execute(){
        double speed = OI.getInstance().getSteeringWheel().getGas();

        double steerWheelValue = (OI.getInstance().getSteeringWheel().getX()) * 450;

        if (steerWheelValue > 360)  {
            steerWheelValue = steerWheelValue - 360;
        }
        else if (steerWheelValue < 0){
            steerWheelValue = 360 + steerWheelValue;
        }

        int step = 5;
        if (steerWheelValue > Gyro.getInstance().getAngle() + step) {
            controller.setSetpoint(Gyro.getInstance().getAngle() + step);
        }
        else if (steerWheelValue < Gyro.getInstance().getAngle() - step) {
            controller.setSetpoint(Gyro.getInstance().getAngle() - step);
        }
        else {
            controller.setSetpoint(steerWheelValue);
        }

        turn = controller.get();

        double newSpeed = speed * e;
        double newTurn = turn * (1 - e);

        DriveTrain.getInstance().tankDrive(newSpeed + newTurn, newSpeed - newTurn);
    }

    @Override
    protected void end(){

    }

    @Override
    protected void interrupted(){

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}