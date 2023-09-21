// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class SimpleAutonomous extends CommandBase {
  private Drivetrain drivetrain;
  private Timer timer = new Timer();

  /** Creates a new SimpleAutonomous. */
  public SimpleAutonomous(Drivetrain drivetrain) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timedAutoSequence();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  private void timedAutoSequence() {
    if(timer.get() < 2){
      drivetrain.tankDrive(.5, .5);
    } else {
      drivetrain.tankDrive(0, 0);
    }
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
