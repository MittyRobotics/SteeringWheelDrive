package frc.robot;

import edu.wpi.first.wpilibj.command.Command;


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

        double speed = OI.getInstance().getJoystick().getY();

        DriveTrain.getInstance().tankDrive(speed, speed);

    }

    @Override
    protected boolean isFinished(){
        return false;
    }
}