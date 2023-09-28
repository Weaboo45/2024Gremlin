package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Drivetrain extends SubsystemBase {
  private WPI_VictorSPX frontRightMotor, rearRightMotor, rearLeftMotor, frontLeftMotor;

  private DifferentialDrive drive;
  private ShuffleboardTab tab;

  public Drivetrain(ShuffleboardTab tab) {
    // Motor controllers
    frontLeftMotor = new WPI_VictorSPX(FRONT_LEFT_TALON_ID);
    rearLeftMotor = new WPI_VictorSPX(BACK_LEFT_TALON_ID);
    frontRightMotor = new WPI_VictorSPX(FRONT_RIGHT_TALON_ID);
    rearRightMotor = new WPI_VictorSPX(BACK_RIGHT_TALON_ID);

    frontRightMotor.setInverted(true);
    rearRightMotor.setInverted(true);

    rearRightMotor.follow(frontRightMotor);
    rearLeftMotor.follow(frontLeftMotor);
    

    drive = new DifferentialDrive(frontRightMotor, frontLeftMotor);

    this.tab = tab;

    configureShuffleboardData();
  }

  public void applyBoostMultiplier(double multiplier) {
  }


  private void configureShuffleboardData() {
    ShuffleboardLayout layout = tab.getLayout("Encoder Vals", BuiltInLayouts.kGrid).withPosition(9, 0);
    //layout.add(this);
    //layout.addNumber("Front Left Encoder Pos", () -> getFrontLeftEncoderPosition());
    //layout.addNumber("Rear Left Encoder Pos", () -> getRearLeftEncoderPosition());
    //layout.addNumber("Front Right Encoder Pos", () -> getFrontRightEncoderPosition());
    //layout.addNumber("Rear Right Encoder Pos", () -> getRearRightEncoderPosition());

    layout.addNumber("Rear Left Volts", () -> rearLeftMotor.getMotorOutputVoltage());
    layout.addNumber("Rear Right Volts", () -> rearRightMotor.getMotorOutputVoltage());
    layout.addNumber("Front Left Volts", () -> frontLeftMotor.getMotorOutputVoltage());
    layout.addNumber("Front Right Volts", () -> frontRightMotor.getMotorOutputVoltage());

  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    drive.feed();
  }


  public void resetEncoderPositions() {
    frontLeftMotor.setSelectedSensorPosition(0);
    frontRightMotor.setSelectedSensorPosition(0);
    rearLeftMotor.setSelectedSensorPosition(0);
    rearRightMotor.setSelectedSensorPosition(0);
  }

  public double getFrontLeftEncoderPosition() { return frontLeftMotor.getSelectedSensorPosition() * DISTANCE_PER_PULSE; }
  public double getRearLeftEncoderPosition() { return rearLeftMotor.getSelectedSensorPosition() * DISTANCE_PER_PULSE; }
  public double getFrontRightEncoderPosition() { return frontRightMotor.getSelectedSensorPosition() * DISTANCE_PER_PULSE; }
  public double getRearRightEncoderPosition() { return rearRightMotor.getSelectedSensorPosition() * DISTANCE_PER_PULSE; }

  //Bot-oriented
  public void tankDrive(double xSpeed, double ySpeed){
    xSpeed = MathUtil.applyDeadband(xSpeed, SPEED_DEADBAND);
    ySpeed = MathUtil.applyDeadband(ySpeed, SPEED_DEADBAND);

    drive.tankDrive(xSpeed, ySpeed);
  }

  public void DriveArcade(double speed, double rotate) {
    speed = MathUtil.applyDeadband(speed, SPEED_DEADBAND);
    rotate = MathUtil.applyDeadband(rotate, ROTATION_DEADBAND);

    drive.arcadeDrive(speed, rotate, IS_ARCADEDRIVE_SQUARED);
  }

  public void DriveCurvature(double speed, double rotate, boolean onOff){
    speed = MathUtil.applyDeadband(speed, SPEED_DEADBAND);
    rotate = MathUtil.applyDeadband(rotate, ROTATION_DEADBAND);

    drive.curvatureDrive(speed, rotate, onOff);
  }
}