package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

import javax.naming.ldap.Control;


public class tankCommand extends Command {



    public tankCommand() {
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void execute() {

//        double speed = OI.getInstance().getJoystick().getY();
//
//        DriveTrain.getInstance().tankVelocity(speed, speed);

        System.out.println("Left: " + DriveTrain.getInstance().getLeftEncoder());
        System.out.println("Right: " + DriveTrain.getInstance().getRightEncoder());

    }

    @Override
    protected boolean isFinished(){
        return false;
    }
}