/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.DriveCheesy;
import frc.robot.commands.DriveTank;
import frc.robot.subsystems.Drivetrain;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  
  private final ShuffleboardTab m_tab = Shuffleboard.getTab("Practice Robot");

  //SUBSYSTEMS
  private final Drivetrain m_drivetrain = new Drivetrain();
  
  //CONTROLLERS & BUTTONS
  private final XboxController m_xbox = new XboxController(0);
  
  //COMMANDS
    private final DriveTank m_driveTank = new DriveTank(m_drivetrain, () -> -m_xbox.getY(Hand.kLeft), () -> -m_xbox.getY(Hand.kRight));

    private final DriveCheesy m_driveCheesySticks = new DriveCheesy(m_drivetrain, () -> -m_xbox.getY(Hand.kRight), () -> m_xbox.getX(Hand.kLeft));
    private final DriveCheesy m_driveCheesyTriggers = new DriveCheesy(m_drivetrain, () -> m_xbox.getTriggerAxis(Hand.kRight) - m_xbox.getTriggerAxis(Hand.kLeft) , () -> m_xbox.getX(Hand.kLeft));

    private final DriveArcade m_driveArcadeSticks = new DriveArcade(m_drivetrain, () -> -m_xbox.getY(Hand.kRight), () -> m_xbox.getX(Hand.kLeft));
    private final DriveArcade m_driveArcadeTriggers = new DriveArcade(m_drivetrain, () -> m_xbox.getTriggerAxis(Hand.kRight) - m_xbox.getTriggerAxis(Hand.kLeft) , () -> m_xbox.getX(Hand.kLeft));

  public RobotContainer() {
    // Configure the button bindings
    configureInitialDefaultCommands();
    configureControllerButtonBindings();
    configureShuffleboardData();
  }
  
  private void configureShuffleboardData() {
    m_tab.add("Tank Drive", new InstantCommand(() -> m_drivetrain.setDefaultCommand(m_driveTank), m_drivetrain));
    m_tab.add("Cheesy Drive with Sticks", new InstantCommand(() -> m_drivetrain.setDefaultCommand(m_driveCheesySticks), m_drivetrain));
    m_tab.add("Cheesy Drive with Triggers", new InstantCommand(() -> m_drivetrain.setDefaultCommand(m_driveCheesyTriggers), m_drivetrain));
    m_tab.add("Arcade Drive with Sticks", new InstantCommand(() -> m_drivetrain.setDefaultCommand(m_driveArcadeSticks), m_drivetrain));
    m_tab.add("Arcade Drive with Triggers", new InstantCommand(() -> m_drivetrain.setDefaultCommand(m_driveArcadeTriggers), m_drivetrain));

    m_tab.addNumber("left x", () -> m_xbox.getX(Hand.kLeft));     
    m_tab.addNumber("left y", () -> -m_xbox.getY(Hand.kLeft));     
    m_tab.addNumber("left trigger", () -> m_xbox.getTriggerAxis(Hand.kRight));
    m_tab.addNumber("right x", () -> m_xbox.getX(Hand.kRight));     
    m_tab.addNumber("right y", () -> -m_xbox.getY(Hand.kRight));
    m_tab.addNumber("right trigger", () -> m_xbox.getTriggerAxis(Hand.kRight));
  }
  
  private void configureInitialDefaultCommands() {
    m_drivetrain.setDefaultCommand(m_driveCheesyTriggers);
  }
  
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureControllerButtonBindings() {
  }
  
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
