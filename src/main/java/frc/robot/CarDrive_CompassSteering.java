package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class CarDrive_CompassSteering extends Command {

    PIDController controller;

    private double speed;
    private double acceleration;
    private double turn;
    private double e = 0.7; //emphasis var

    public CarDrive_CompassSteering(){
        requires(DriveTrain.getInstance());
    }
    @Override
    protected void initialize(){

        speed = 0;
        acceleration = 0;

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
    protected void execute(){

        double steerWheelValue = (OI.getInstance().getSteeringWheel().getX()) * 450;
        if (steerWheelValue > 360)  {
            steerWheelValue = steerWheelValue - 360;
        }
        else if (steerWheelValue < 0){
            steerWheelValue = 360 + steerWheelValue;
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

        boolean isLeftPressed = OI.getInstance().getXboxController().getBumper(GenericHID.Hand.kLeft);
        boolean isRightPressed = OI.getInstance().getXboxController().getBumper(GenericHID.Hand.kRight);

        if(speed < 0){ //if going backward
            //make if left pressed, right pressed and if nothing pressed
            if (isLeftPressed){ //left bumper
                if(speed > -1){ //capped speed
                    acceleration = -0.01;
                } else {
                    acceleration = 0;
                }
            }
            else if (isRightPressed){ //right bumper
                acceleration = 0.01;
            }
            else { //let go both bumpers
                if (speed < 0){
                    acceleration = 0.01;
                } else {
                    acceleration = 0;
                }
            }
        }

        if (speed == 0){ //robot at rest
            //make if left pressed, right pressed and if nothing pressed
            if (isLeftPressed){ //left bumper
                acceleration = -0.01;
            }
            else if (isRightPressed){ //right bumper
                acceleration = 0.01;
            }
            else { //let go both
                acceleration = 0;
            }
        }

        if(speed > 0){ //going forward
            //make if left pressed, right pressed and if nothing pressed
            if (isLeftPressed){ //left bumper
                acceleration = -0.01;
            }
            else if (isRightPressed){ //right bumper
                if (speed < 1){ //capped speed
                    acceleration = 0.01;
                } else {
                    acceleration = 0;
                }
            }
            else { //let go both
                if (speed > 0) {
                    acceleration = -0.01;

                } else {
                    acceleration = 0;
                }
            }
        }

        speed += acceleration;

        turn = controller.get();

        double newSpeed = speed * e;
        double newTurn = turn * (1 - e);


        DriveTrain.getInstance().tankDrive(newSpeed - newTurn, newSpeed + newTurn);


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