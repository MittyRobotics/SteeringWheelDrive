package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.DriveTrain;
import frc.robot.OI;

public class JoystickDrive_CompassSteering extends Command { //example command

    private double turn;
    private double e = 0.7;

    public JoystickDrive_CompassSteering(){
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){

        turn = OI.getInstance().getSteeringWheel().getX();
        DriveTrain.getInstance().tankDrive(turn, -turn);

        double speed = OI.getInstance().getJoystick().getY();

        double newSpeed = speed*e;
        double newTurn = turn * (1-e);


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