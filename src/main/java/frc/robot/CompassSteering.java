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
        double steerWheelValue = (OI.getInstance().getSteeringWheel().getX()) * 450;
        if (steerWheelValue > 360)  {
            steerWheelValue = steerWheelValue - 360;
        }
        else if (steerWheelValue < 0){
            steerWheelValue = 360 + steerWheelValue;
        }
        int s = 5;
        if ( (steerWheelValue - Gyro.getInstance().getAngle() ) > s){
            controller.setSetpoint(Gyro.getInstance().getAngle() + s);

        }
        else {
            controller.setSetpoint(steerWheelValue);
        }
        //Gyro.getInstance().getAngle()
        DriveTrain.getInstance().tankDrive(controller.get(), -controller.get());
    }


}
