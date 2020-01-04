package com.amhsrobotics;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class CarDrive_CompassSteering extends Command {

    private PIDController controller;

    private double speed;
    private double acceleration;

    public CarDrive_CompassSteering(){
        requires(DriveTrain.getInstance());
    }
    @Override
    protected void initialize(){

        speed = 0;
        acceleration = 0;

        PIDOutput pidOutput = output -> {

        };
        controller = new PIDController(PID.TURN[0], PID.TURN[1], PID.TURN[2], Gyro.getInstance(), pidOutput);
        controller.setOutputRange(-1, 1);
        controller.enable();

    }

    @Override
    protected void execute(){

//CompassSteering
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

//BumperDriveCommand
        boolean isLeftPressed = OI.getInstance().getSteeringWheel().getShifter(GenericHID.Hand.kRight);
        boolean isRightPressed = OI.getInstance().getSteeringWheel().getShifter(GenericHID.Hand.kLeft);
        boolean brake = OI.getInstance().getSteeringWheel().getBButton();

        double turn;
        if(speed < 0){ //if going backward
            //make if left pressed, right pressed and if nothing pressed
            if (isLeftPressed){ //left bumper
                if(speed > -1){ //capped speed
                    acceleration = -0.07;
                } else {
                    acceleration = 0;
                }
            }
            else if (isRightPressed){ //right bumper
                acceleration = 0.05;
            }
            else { //let go both bumpers
                if (speed < 0){
                    acceleration = 0.07;
                } else {
                    acceleration = 0;
                }
            }
        }

        else if (speed == 0){ //robot at rest
            //make if left pressed, right pressed and if nothing pressed
            if (isLeftPressed){ //left bumper
                acceleration = -0.07;
            }
            else if (isRightPressed){ //right bumper
                acceleration = 0.07;
            }
            else { //let go both
                acceleration = 0;
            }
        }

        else if(speed > 0){ //going forward
            //make if left pressed, right pressed and if nothing pressed
            if (isLeftPressed){ //left bumper
                acceleration = -0.07;
            }
            else if (isRightPressed){ //right bumper
                if (speed < 1){ //capped speed
                    acceleration = 0.07;
                } else {
                    acceleration = 0;
                }
            }
            else { //let go both
                if (speed > 0) {
                    acceleration = -0.07;

                } else {
                    acceleration = 0;
                }
            }
        }

        speed += acceleration;

        turn = controller.get();

        //emphasis var
        final double DRIVE_EMPHASIS = 0.7;
        double newSpeed = speed * DRIVE_EMPHASIS;
        double newTurn = turn * (1 - DRIVE_EMPHASIS);

        if(brake){
            DriveTrain.getInstance().tankDrive(0, 0);
        } else if(Math.abs(speed) < 0.1){
            DriveTrain.getInstance().tankDrive(turn, -turn);
        } else if (speed > 0) {
            DriveTrain.getInstance().tankDrive((newSpeed) - newTurn, (newSpeed) + newTurn);
        } else {
            DriveTrain.getInstance().tankDrive((newSpeed) + newTurn, (newSpeed) - newTurn);
        }
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