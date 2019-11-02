package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.DriveTrain;
import frc.robot.OI;

public class JoystickDrive_CarSteering extends Command { //example command

    private double turn;
    private double prevValue;
    public JoystickDrive_CarSteering(){
        prevValue = 1;
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){

        turn = OI.getInstance().getSteeringWheel().getX() * 450/120;
        //DriveTrain.getInstance().tankDrive(turn, -turn);

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
        if(prevValue < 0 && speed == 0){
            DriveTrain.getInstance().tankDrive(newSpeed-newTurn, newSpeed+newTurn);
        }
        else if(speed >= 0){
            DriveTrain.getInstance().tankDrive(newSpeed + newTurn, newSpeed - newTurn);
        } else {
            DriveTrain.getInstance().tankDrive(newSpeed - newTurn, newSpeed + newTurn);
        }
        if(speed != 0){
            prevValue = speed;
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