package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class CarDrive_CarSteering extends Command {

    private double speed;
    private double acceleration;
    private double turn;
    private double e = 0.3; //emphasis var

    public CarDrive_CarSteering(){
        requires(DriveTrain.getInstance());
    }
    @Override
    protected void initialize(){

        speed = 0;
        acceleration = 0;

    }

    @Override
    protected void execute(){

        //turn = OI.getInstance().getXboxController().getX(GenericHID.Hand.kLeft);
        double turn = OI.getInstance().getSteeringWheel().getX()*4;

        boolean isLeftPressed = OI.getInstance().getSteeringWheel().getShifter(GenericHID.Hand.kRight);
        boolean isRightPressed = OI.getInstance().getSteeringWheel().getShifter(GenericHID.Hand.kLeft);
        boolean brake = OI.getInstance().getSteeringWheel().getAButton();

        //boolean isLeftPressed = OI.getInstance().getXboxController().getBumper(GenericHID.Hand.kLeft);
        //boolean isRightPressed = OI.getInstance().getXboxController().getBumper(GenericHID.Hand.kRight);
        //boolean brake = OI.getInstance().getXboxController().getAButton();

        if (brake) {
            speed = 0;
            acceleration = 0;
            turn = 0;
        }
        else if(speed < 0){ //if going backward
            //make if left pressed, right pressed and if nothing pressed
            if (isLeftPressed){ //left bumper
                if(speed > -1){ //capped speed
                    acceleration = -0.07;
                } else {
                    acceleration = 0;
                }
            }
            else if (isRightPressed){ //right bumper
                acceleration = 0.05;
            }
            else { //let go both bumpers
                if (speed < 0){
                    acceleration = 0.07;
                } else {
                    acceleration = 0;
                }
            }
        }

        else if (speed == 0){ //robot at rest
            //make if left pressed, right pressed and if nothing pressed
            if (isLeftPressed){ //left bumper
                acceleration = -0.07;
            }
            else if (isRightPressed){ //right bumper
                acceleration = 0.07;
            }
            else { //let go both
                acceleration = 0;
            }
        }

        else if(speed > 0){ //going forward
            //make if left pressed, right pressed and if nothing pressed
            if (isLeftPressed){ //left bumper
                acceleration = -0.07;
            }
            else if (isRightPressed){ //right bumper
                if (speed < 1){ //capped speed
                    acceleration = 0.07;
                } else {
                    acceleration = 0;
                }
            }
            else { //let go both
                if (speed > 0) {
                    acceleration = -0.07;

                } else {
                    acceleration = 0;
                }
            }
        }

        speed += acceleration;

        double newSpeed = speed * e;
        double newTurn = turn * (1-e);

        if(Math.abs(speed) < 0.1){
            DriveTrain.getInstance().tankDrive(turn, -turn);
        }

        if (speed > 0) {
            DriveTrain.getInstance().tankDrive((newSpeed) - newTurn, (newSpeed) + newTurn);
        }
        else if (speed < 0) {
            DriveTrain.getInstance().tankDrive((newSpeed) + newTurn, (newSpeed) - newTurn);
        }

        //System.out.println("Speed" + newSpeed);
        //System.out.println("Turn" + newTurn);


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