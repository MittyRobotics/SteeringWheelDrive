package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.DriveTrain;
import frc.robot.OI;

public class JoystickDriveCommand extends Command {

    private static JoystickDriveCommand ourInstance = new JoystickDriveCommand();

    public static JoystickDriveCommand getInstance() {
        return ourInstance;
    }

    private JoystickDriveCommand() {
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void execute() {

        double leftSpeed = OI.getInstance().getJoystick().getY();
        double rightSpeed = OI.getInstance().getJoystick().getY();

        DriveTrain.getInstance().tankDrive(leftSpeed, rightSpeed);
    }

    @Override
    protected boolean isFinished(){
        return false;
    }
}