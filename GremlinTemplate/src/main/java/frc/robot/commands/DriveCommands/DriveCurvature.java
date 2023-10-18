package frc.robot.commands.DriveCommands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveCurvature extends CommandBase {
    
    private Drivetrain drivetrain;
    private Supplier<Double> speed, rotation;
    private Supplier<Boolean> curve;
    private boolean curveOnOff;

    public DriveCurvature(Drivetrain drivetrain, Supplier<Double> speed, Supplier<Double> rotation, Supplier<Boolean> curve){
        addRequirements(drivetrain);
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.rotation = rotation;
        this.curve = curve;
    }

    // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    curveOnOff = true;
  }

  @Override
  public void execute() {
    double driveSpeed = speed.get();
    double rotate = rotation.get();
    
     if(curve.get()){
        curveOnOff = !curveOnOff;
     }

    drivetrain.DriveCurvature(driveSpeed, rotate, curveOnOff);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {;
    drivetrain.DriveCurvature(0,0, curveOnOff);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
