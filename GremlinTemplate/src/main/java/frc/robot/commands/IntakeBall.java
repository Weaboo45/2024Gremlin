package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.Supplier;
import frc.robot.subsystems.InTake;

public class IntakeBall extends CommandBase{
    private InTake inTake;
    private Supplier<Boolean> top, bottom;
    private Double topSpeed, bottomSpeed;

    public  IntakeBall(InTake intake, Supplier<Boolean> top, Supplier<Boolean> bottom){
        addRequirements(inTake);
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public void initialize(){
        topSpeed = 0.0;
        bottomSpeed = 0.0;
    }

    @Override
    public void execute(){
        
        if(top.get()){
            topSpeed = 1.0;
        } else {
            topSpeed = 0.0;
        }

        inTake.moveTopIntake(topSpeed);

        if(bottom.get()){
            bottomSpeed = 1.0;
        } else {
            bottomSpeed = 0.0;
        }

        inTake.moveLowerIntake(bottomSpeed);
    }

    @Override
    public void end(boolean interrupted){
        inTake.moveLowerIntake(0);
        inTake.moveTopIntake(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
