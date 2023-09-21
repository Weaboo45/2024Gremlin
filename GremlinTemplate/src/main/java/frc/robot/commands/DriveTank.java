/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveTank extends CommandBase {
  /*
   * Creates a new DriveMecanum.
   */

  private Drivetrain drivetrain;
  private Supplier<Double>  right, left;
  

  public DriveTank(Drivetrain drivetrain, Supplier<Double> right, Supplier<Double> left) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    this.right = right;
    this.left = left;
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.configureMotorPower();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double ySpeed = right.get();
    double xSpeed = left.get();

    drivetrain.tankDrive(xSpeed, ySpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {;
    drivetrain.tankDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
