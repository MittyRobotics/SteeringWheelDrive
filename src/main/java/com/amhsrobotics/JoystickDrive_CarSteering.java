package com.amhsrobotics;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickDrive_CarSteering extends Command {

    JoystickDrive_CarSteering(){
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){

        double turn = OI.getInstance().getSteeringWheel().getX() * 450 / 120;

        double speed = -OI.getInstance().getJoystick().getY();
        boolean brake = OI.getInstance().getJoystick().getTrigger();

        if(Math.abs(turn) > 1){
            turn = Math.signum(turn);
        }
        double e = 1 - turn;

        if(brake){
            speed = 0;
            turn = 0;
        }

        double newSpeed = speed*e;
        double newTurn = turn;
        if(Math.abs(speed) < 0.05){
            DriveTrain.getInstance().tankDrive(newTurn, - newTurn);
        }
        else if(speed >= 0){
            DriveTrain.getInstance().tankDrive(newSpeed + newTurn, newSpeed - newTurn);
        } else {
            DriveTrain.getInstance().tankDrive(newSpeed - newTurn, newSpeed + newTurn);
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