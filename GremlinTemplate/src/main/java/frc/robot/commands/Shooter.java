package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.YeetCannon;

public class Shooter extends CommandBase{
    
    private YeetCannon yeet;
    private Supplier<Boolean> onOff;
    private Double shootSpeed;

    public Shooter(YeetCannon yeet, Supplier<Boolean> onOff){
        addRequirements(yeet);
        this.yeet = yeet;
        this.onOff = onOff;
    }

    @Override
    public void initialize(){
        yeet.configureMotorPower();
        shootSpeed = 0.0;
    }

    @Override
    public void execute(){
        
        if(onOff.get()){
            shootSpeed = 1.0;
        }

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
