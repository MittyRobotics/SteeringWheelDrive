package com.amhsrobotics;


import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickDrive_CompassSteering extends Command {
    private PIDController controller;

    public JoystickDrive_CompassSteering(){
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void initialize(){
        PIDOutput pidOutput = output -> {

        };
        controller = new PIDController(PID.TURN[0], PID.TURN[1], PID.TURN[2], Gyro.getInstance(), pidOutput);
        controller.setOutputRange(-1, 1);
        controller.enable();

    }

    @Override
    protected void execute(){
        double speed = -OI.getInstance().getJoystick().getY();

        double steerWheelValue = (OI.getInstance().getSteeringWheel().getX()) * 450;

        int step = 15;
        if (steerWheelValue > Gyro.getInstance().getAngle() + step) {
            controller.setSetpoint(Gyro.getInstance().getAngle() + step);
        }
        else if (steerWheelValue < Gyro.getInstance().getAngle() - step) {
            controller.setSetpoint(Gyro.getInstance().getAngle() - step);
        }
        else {
            controller.setSetpoint(steerWheelValue);
        }
        double newSpeed = speed * (1-controller.get());
        double newTurn = controller.get();

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