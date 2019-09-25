package frc.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import jdk.jfr.Frequency;

public class CompassSteering extends Command {
    PIDController controller;
    @Override
    protected boolean isFinished() {
        return false;
    }

    public CompassSteering(){
        requires(DriveTrain.getInstance());
    }

    @Override
    protected void initialize(){
        PIDOutput pidOutput = new PIDOutput() {
            @Override
            public void pidWrite(double output) {

            }
        };
        controller = new PIDController(PID.TURN[0], PID.TURN[1], PID.TURN[2], Gyro.getInstance(), pidOutput);
        controller.setInputRange(-180, 180);
        controller.setOutputRange(-1, 1);
        controller.enable();
    }

    @Override
    protected void execute() {
        double steerWheelValue = (OI.getInstance().getSteeringWheel().getX()) * 450;
        if ((steerWheelValue > 180) && (steerWheelValue < 360)) {
            steerWheelValue = (360 - steerWheelValue) * -1;
        }
        else if (steerWheelValue < -180){
            steerWheelValue = 360 + steerWheelValue;
        }
        if (steerWheelValue > 360) {
            steerWheelValue = 360 - steerWheelValue;
        }

        int step = 5;
        if (steerWheelValue > Gyro.getInstance().getAngle() + step) {
                controller.setSetpoint(Gyro.getInstance().getAngle() + step);
        }
        else if (steerWheelValue < Gyro.getInstance().getAngle() - step) {
                controller.setSetpoint(Gyro.getInstance().getAngle() - step);
        }
        else {
            controller.setSetpoint(steerWheelValue);
        }

        DriveTrain.getInstance().tankDrive(controller.get(), -controller.get());
    }


}
