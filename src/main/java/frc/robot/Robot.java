/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private WPI_TalonSRX[] leftDrive = new WPI_TalonSRX[2];
  private WPI_TalonSRX[] rightDrive = new WPI_TalonSRX[2];
  private SpeedControllerGroup left = new SpeedControllerGroup(leftDrive[0], leftDrive[1]);
  private SpeedControllerGroup right = new SpeedControllerGroup(rightDrive[0], rightDrive[1]);
  private SteeringWheel steeringWheel = new SteeringWheel(0);
  private Joystick joystick = new Joystick(1);
  private DoubleSolenoid gearShift = new DoubleSolenoid(0, 1);
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    leftDrive[0] = new WPI_TalonSRX(20);
    leftDrive[1] = new WPI_TalonSRX(21);
    rightDrive[0] = new WPI_TalonSRX(22);
    rightDrive[1] = new WPI_TalonSRX(23);
    left.setInverted(false);
    right.setInverted(true);
  }


  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    System.out.println(steeringWheel.getX());
    if(steeringWheel.getShifterPressed(GenericHID.Hand.kLeft)){
      gearShift.set(DoubleSolenoid.Value.kForward);
    } else if(steeringWheel.getShifterPressed(GenericHID.Hand.kRight)){
      gearShift.set(DoubleSolenoid.Value.kReverse);
    }
  }


  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
