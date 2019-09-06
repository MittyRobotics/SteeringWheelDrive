package frc.robot;

import edu.wpi.first.wpilibj.command.Command;

public class CarSteering extends Command {
    public CarSteering(){
        requires(DriveTrain.getInstance());
    }
    @Override
    protected void initialize(){

    }
    @Override
    protected void execute(){
        double speed = OI.getInstance().getSteeringWheel().getX();
        DriveTrain.getInstance().tankDrive(speed, -speed);
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
