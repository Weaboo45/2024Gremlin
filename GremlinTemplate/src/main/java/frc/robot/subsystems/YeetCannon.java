package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class YeetCannon extends SubsystemBase{
    
    private WPI_TalonSRX rightCannon, leftCannon;
    private ShuffleboardTab tab;


    public YeetCannon(ShuffleboardTab tab){
        //motor controllers
        rightCannon = new WPI_TalonSRX(RIGHT_CANNON_ID);
        leftCannon = new WPI_TalonSRX(LEFT_CANNON_ID);

        leftCannon.follow(rightCannon);

        this.tab = tab;
        configureShuffleboardData();
    }

    private void configureShuffleboardData() {
    ShuffleboardLayout layout = tab.getLayout("Encoder Vals", BuiltInLayouts.kGrid).withPosition(9, 0);

    layout.addNumber("Right Cannon Encoder Pos", () -> getRightCannonEncoderPosition());
    layout.addNumber("Left Cannon Encoder Pos", () -> getLeftCannonEncoderPosition());
    }

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void configureMotorPower() {
    // voltage comp
    rightCannon.configVoltageCompSaturation(12); // "full output" will now scale to 12 Volts for all control modes when enabled.
    leftCannon.enableVoltageCompensation(true);  // turn on/off feature

    rightCannon.configVoltageCompSaturation(12); 
    leftCannon.enableVoltageCompensation(true); 

    //amp limits
    rightCannon.configPeakCurrentLimit(PEAK_LIMIT);
    leftCannon.configPeakCurrentLimit(PEAK_LIMIT);

    rightCannon.configPeakCurrentDuration(150);
    leftCannon.configPeakCurrentDuration(150);

    rightCannon.configContinuousCurrentLimit(ENABLE_LIMIT);
    leftCannon.configContinuousCurrentLimit(ENABLE_LIMIT);

    rightCannon.enableCurrentLimit(true);
    leftCannon.enableCurrentLimit(true);

    //ramp rate
    rightCannon.configOpenloopRamp(0.1);
    //frontLeftMotor.configClosedloopRamp(1.5);

    leftCannon.configOpenloopRamp(0.1);
    //frontRightMotor.configClosedloopRamp(1.5);

  }

  public void resetEncoderPositions(){
    rightCannon.setSelectedSensorPosition(0);
    leftCannon.setSelectedSensorPosition(0);
  }

  public double getRightCannonEncoderPosition() { return rightCannon.getSelectedSensorPosition();}
  public double getLeftCannonEncoderPosition() { return leftCannon.getSelectedSensorPosition();}

  public void yeetCannon(double speed){
    rightCannon.set(speed);
  }
}
