/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DrivetrainConstants.*;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private WPI_TalonSRX m_leftMasterMotor;
  private WPI_VictorSPX m_leftSlaveMotor;
  private WPI_TalonSRX m_rightMasterMotor;
  private WPI_VictorSPX m_rightSlaveMotor;

  private DifferentialDrive m_drive;
  
  public Drivetrain() {
    
    //set up drive motors;
    m_leftMasterMotor = new WPI_TalonSRX(leftID);
    m_leftSlaveMotor = new WPI_VictorSPX(leftID);

    m_leftSlaveMotor.follow(m_leftMasterMotor);

    m_rightMasterMotor = new WPI_TalonSRX(rightID);
    m_rightSlaveMotor = new WPI_VictorSPX(rightID);

    m_rightSlaveMotor.follow(m_rightMasterMotor);

    m_drive = new DifferentialDrive(m_leftMasterMotor, m_rightMasterMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void resetEncoderPositions() {
    m_leftMasterMotor.setSelectedSensorPosition(0);
    m_rightMasterMotor.setSelectedSensorPosition(0);
  }

  public void tankDrive(double left, double right) {
    m_drive.tankDrive(left, right, isTankDriveSquared);
  }

  public void arcadeDrive(double speed, double rotation) {

    speed = (Math.abs(speed) < speedDeadband)?
            0 : (speed < 0)?
            (speed + speedDeadband) / (1 - speedDeadband):
            (speed - speedDeadband) / (1 - speedDeadband);
    rotation = (Math.abs(rotation) < rotationDeadband)?
               0 : (rotation < 0)?
               (rotation + rotationDeadband) / (1 - rotationDeadband):
               (rotation - rotationDeadband) / (1 -rotationDeadband);

    m_drive.arcadeDrive(speed, rotation, isArcadeDriveSquared);
  }

  public void cheesyDrive(double speed, double rotation) {

    speed = (Math.abs(speed) < speedDeadband)?
            0 : (speed < 0)?
            (speed + speedDeadband) / (1 - speedDeadband):
            (speed - speedDeadband) / (1 - speedDeadband);
    rotation = (Math.abs(rotation) < rotationDeadband)?
               0 : (rotation < 0)?
               (rotation + rotationDeadband) / (1 - rotationDeadband):
               (rotation - rotationDeadband) / (1 - rotationDeadband);

    m_drive.curvatureDrive(speed, rotation, doesCheesyDrivePivot);
  }
}
