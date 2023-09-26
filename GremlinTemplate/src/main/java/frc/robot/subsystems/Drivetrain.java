package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Drivetrain extends SubsystemBase {
  private WPI_TalonSRX frontRightMotor, rearRightMotor, rearLeftMotor, frontLeftMotor;
  private boolean coastMode;

  private DifferentialDrive drive;
  private ShuffleboardTab tab;

  public Drivetrain(ShuffleboardTab tab) {
    // Motor controllers
    frontLeftMotor = new WPI_TalonSRX(FRONT_LEFT_TALON_ID);
    rearLeftMotor = new WPI_TalonSRX(BACK_LEFT_TALON_ID);
    frontRightMotor = new WPI_TalonSRX(FRONT_RIGHT_TALON_ID);
    rearRightMotor = new WPI_TalonSRX(BACK_RIGHT_TALON_ID);

    rearRightMotor.follow(frontRightMotor);
    rearLeftMotor.follow(frontLeftMotor);
    
    toggleMotorMode(false);
    coastMode = false;

    drive = new DifferentialDrive(frontRightMotor, frontLeftMotor);

    this.tab = tab;

    configureShuffleboardData();
  }

  public void applyBoostMultiplier(double multiplier) {
  }

  public void toggleMotorMode(boolean modeSwitch) {
    if (modeSwitch) {
      coastMode = !coastMode;
    }
    if (coastMode) {//coast mode
      frontLeftMotor.setNeutralMode(NeutralMode.Coast);
      frontRightMotor.setNeutralMode(NeutralMode.Coast);
      rearLeftMotor.setNeutralMode(NeutralMode.Coast);
      rearRightMotor.setNeutralMode(NeutralMode.Coast);
    }
    else {
      frontLeftMotor.setNeutralMode(NeutralMode.Brake);
      frontRightMotor.setNeutralMode(NeutralMode.Brake);
      rearLeftMotor.setNeutralMode(NeutralMode.Brake);
      rearRightMotor.setNeutralMode(NeutralMode.Brake);
    }
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

    layout.addBoolean("Coast Mode", () -> coastMode);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    drive.feed();
  }

  public void configureMotorPower() {
    // voltage comp
    frontRightMotor.configVoltageCompSaturation(12); // "full output" will now scale to 12 Volts for all control modes when enabled.
    frontRightMotor.enableVoltageCompensation(true);  // turn on/off feature

    rearRightMotor.configVoltageCompSaturation(12); 
    rearRightMotor.enableVoltageCompensation(true); 

    frontLeftMotor.configVoltageCompSaturation(12); 
    frontLeftMotor.enableVoltageCompensation(true); 

    rearLeftMotor.configVoltageCompSaturation(12); 
    rearLeftMotor.enableVoltageCompensation(true); 

    //amp limits
    frontLeftMotor.configPeakCurrentLimit(PEAK_LIMIT);
    frontRightMotor.configPeakCurrentLimit(PEAK_LIMIT);
    rearRightMotor.configPeakCurrentLimit(PEAK_LIMIT);
    rearLeftMotor.configPeakCurrentLimit(PEAK_LIMIT);

    frontLeftMotor.configPeakCurrentDuration(150);
    frontRightMotor.configPeakCurrentDuration(150);
    rearRightMotor.configPeakCurrentDuration(150);
    rearLeftMotor.configPeakCurrentDuration(150);
    

    frontLeftMotor.configContinuousCurrentLimit(ENABLE_LIMIT);
    frontRightMotor.configContinuousCurrentLimit(ENABLE_LIMIT);
    rearRightMotor.configContinuousCurrentLimit(ENABLE_LIMIT);
    rearLeftMotor.configContinuousCurrentLimit(ENABLE_LIMIT);

    frontLeftMotor.enableCurrentLimit(true);
    frontRightMotor.enableCurrentLimit(true);
    rearLeftMotor.enableCurrentLimit(true);
    rearRightMotor.enableCurrentLimit(true);

    //ramp rate
    frontLeftMotor.configOpenloopRamp(0.1);
    //frontLeftMotor.configClosedloopRamp(1.5);

    frontRightMotor.configOpenloopRamp(0.1);
    //frontRightMotor.configClosedloopRamp(1.5);

    rearLeftMotor.configOpenloopRamp(0.1);
    //rearLeftMotor.configClosedloopRamp(1.5);

    rearRightMotor.configOpenloopRamp(0.1);
    //rearRightMotor.configClosedloopRamp(1.5);
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