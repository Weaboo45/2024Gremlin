package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.YeetCannon;

public class Shooter extends CommandBase{
    
    private YeetCannon yeet;
    private Supplier<Double> speed;

    public Shooter(YeetCannon yeet, Supplier<Double> speed){
        addRequirements(yeet);
        this.yeet = yeet;
        this.speed = speed;
    }

    @Override
    public void initialize(){
        yeet.configureMotorPower();
    }

    @Override
    public void execute(){
        double shootSpeed = speed.get();
        yeet.yeetCannon(shootSpeed);
    }

    @Override
    public void end(boolean interrupted){
        yeet.yeetCannon(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
