package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class YeetCannon extends SubsystemBase{
    
    private WPI_VictorSPX rightCannon, leftCannon;
    private ShuffleboardTab tab;


    public YeetCannon(ShuffleboardTab tab){
        //motor controllers
        rightCannon = new WPI_VictorSPX(RIGHT_CANNON_ID);
        leftCannon = new WPI_VictorSPX(LEFT_CANNON_ID);

        leftCannon.setInverted(true);
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
