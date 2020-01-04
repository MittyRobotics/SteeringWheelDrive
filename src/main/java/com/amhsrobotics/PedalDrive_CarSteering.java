package com.amhsrobotics;

import edu.wpi.first.wpilibj.command.Command;

public class PedalDrive_CarSteering extends Command {

    public PedalDrive_CarSteering(){
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){

        double turn = OI.getInstance().getSteeringWheel().getX() * 450 / 180.0;
        double speed = 1 - OI.getInstance().getSteeringWheel().getGas();
        boolean back = OI.getInstance().getSteeringWheel().getBButton();
        double brake = OI.getInstance().getSteeringWheel().getBrake();

        if ((brake > 0) && (brake < 1)) {
            speed = 0;
            turn = 0;
        }

        if(back){
            speed *= -1;
        }

        if (turn > 1) {
            turn = 1;
        }
        if (turn < -1) {
            turn = -1;
        }

        final double DRIVE_EMPHASIS = 0.5;
        double newSpeed = speed* DRIVE_EMPHASIS;
        double newTurn = turn * (1- DRIVE_EMPHASIS);

        if(Math.abs(speed) < 0.1){
            DriveTrain.getInstance().tankDrive(turn, -turn);
        }

        else if (speed > 0) {
            DriveTrain.getInstance().tankDrive((newSpeed) + newTurn, (newSpeed) - newTurn);
        }
        else if (speed < 0) {
            DriveTrain.getInstance().tankDrive((newSpeed) - newTurn, (newSpeed) + newTurn);
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