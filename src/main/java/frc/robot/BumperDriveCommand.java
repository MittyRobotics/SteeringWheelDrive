package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.DriveTrain;

public class BumperDriveCommand extends Command {
    private double speed;
    private double acceleration;
    private static BumperDriveCommand ourInstance = new BumperDriveCommand();

    public static BumperDriveCommand getInstance() {
        return ourInstance;
    }

    private BumperDriveCommand() {
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void initialize(){
        speed = 0;
        acceleration = 0;
    }

    @Override
    protected void execute() {

        boolean isLeftPressed = OI.getInstance().getSteeringWheel().getShifter(GenericHID.Hand.kLeft);
        boolean isRightPressed = OI.getInstance().getSteeringWheel().getShifter(GenericHID.Hand.kRight);

        if(speed < 0){
            //make if left pressed, right pressed and if nothing pressed
            // if left pressed while negative then increment speed by negative acceleration CAP speed
            // if right pressed while negative then increment speed by positive acc past 0


        }

        if (speed == 0){
            //make if left pressed, right pressed and if nothing pressed


        }

        if(speed > 0){
            //make if left pressed, right pressed and if nothing pressed


        }
        speed += acceleration;
        DriveTrain.getInstance().tankDrive(speed, speed);
    }

    @Override
    protected boolean isFinished(){
        return false;
    }
}