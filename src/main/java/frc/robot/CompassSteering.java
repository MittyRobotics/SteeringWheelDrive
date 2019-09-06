package frc.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class CompassSteering extends Command {
    PIDController controller;
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void initialize(){
        PIDOutput pidOutput = new PIDOutput() {
            @Override
            public void pidWrite(double output) {

            }
        };
        controller = new PIDController(PID.TURN[0], PID.TURN[1], PID.TURN[2], Gyro.getInstance(), pidOutput);
        controller.setInputRange(0, 360);
        controller.setOutputRange(-1, 1);
        controller.enable();
    }

    @Override
    protected void execute() {
        double SteerWheelValue = (OI.getInstance().getSteeringWheel().getX()) * 360;
        if (SteerWheelValue > 360)  {
            SteerWheelValue = SteerWheelValue - 360;
        }
        else if (SteerWheelValue < 0){
            SteerWheelValue = 360 + SteerWheelValue;
        }

        DriveTrain.getInstance().tankDrive(SteerWheelValue, -SteerWheelValue);

    }


}
