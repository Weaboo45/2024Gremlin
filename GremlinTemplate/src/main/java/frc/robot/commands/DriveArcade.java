package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Drivetrain;

public class DriveArcade extends CommandBase {

    private Drivetrain drivetrain;
    private Supplier<Double> drive, rotation;

    public DriveArcade(Drivetrain drivetrain, Supplier<Double> drive, Supplier<Double> rotation){
        addRequirements(drivetrain);
        this.drivetrain = drivetrain;
        this.drive = drive;
        this.rotation = rotation;
    }

    // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.configureMotorPower();
  }

  @Override
  public void execute() {
    double driveSpeed = drive.get();
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
