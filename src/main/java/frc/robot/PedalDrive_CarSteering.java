package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.DriveTrain;
import frc.robot.OI;

public class PedalDrive_CarSteering extends Command { //example command

    private double turn;
    private double e = 0.7;

    public PedalDrive_CarSteering(){
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){

        turn = OI.getInstance().getSteeringWheel().getX();
        //DriveTrain.getInstance().tankDrive(turn, -turn);

        double speed = 1 - OI.getInstance().getSteeringWheel().getGas();

        double newSpeed = speed*e;
        double newTurn = turn * (1-e);

        if(Math.abs(speed) < 0.1){
            DriveTrain.getInstance().tankDrive(turn, -turn);
        }

        if (speed > 0) {
            DriveTrain.getInstance().tankDrive((newSpeed) - newTurn, (newSpeed) + newTurn);
        }
        else if (speed < 0) {
            DriveTrain.getInstance().tankDrive((newSpeed) + newTurn, (newSpeed) - newTurn);
        }

        //DriveTrain.getInstance().tankDrive(newSpeed + newTurn, newSpeed - newTurn);
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