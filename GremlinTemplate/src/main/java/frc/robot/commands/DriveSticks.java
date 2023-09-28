package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.Supplier;

import frc.robot.subsystems.Drivetrain;

public class DriveSticks extends CommandBase{
    
    private Drivetrain drivetrain;
    private Supplier<Double> speed, rotation;

    public DriveSticks(Drivetrain drivetrain, Supplier<Double> speed, Supplier<Double> rotation){
        addRequirements(drivetrain);
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.rotation = rotation;
    }

    // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double driveSpeed = speed.get();
    double rotate = rotation.get();

    drivetrain.DriveArcade(driveSpeed, rotate);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {;
    drivetrain.DriveArcade(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
