package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;

public class InTake extends SubsystemBase{
    private WPI_VictorSPX topIntake, lowerIntake;
    private ShuffleboardTab tab;

    public InTake (ShuffleboardTab tab){

        topIntake = new WPI_VictorSPX(TOP_INTAKE_MOTOR_ID);
        lowerIntake = new WPI_VictorSPX(LOWER_INTAKE_MOTOR_ID);
    
        this.tab = tab;
    
        configureShuffleboardData();
      }
    
      private void configureShuffleboardData() {
        ShuffleboardLayout layout = tab.getLayout("Encoder Vals", BuiltInLayouts.kGrid).withPosition(9, 0);
    
        layout.addNumber("Top Intake Encoder Pos", () -> getRightCannonEncoderPosition());
        layout.addNumber("Lower Intake Encoder Pos", () -> getLeftCannonEncoderPosition());
        }
    
      @Override
      public void periodic() {
        // This method will be called once per scheduler run  
      }

      public void resetEncoderPositions(){
        topIntake.setSelectedSensorPosition(0);
        lowerIntake.setSelectedSensorPosition(0);
      }
    
      public double getRightCannonEncoderPosition() { return topIntake.getSelectedSensorPosition();}
      public double getLeftCannonEncoderPosition() { return lowerIntake.getSelectedSensorPosition();}
    
    
      public void moveTopIntake(double speed) {
        topIntake.set(speed);
      }
    
      public void moveLowerIntake(double speed) {
        lowerIntake.set(speed);
      }
    }

