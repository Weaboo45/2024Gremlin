package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.Supplier;
import frc.robot.subsystems.InTake;

public class IntakeBall extends CommandBase{
    private InTake inTakeSub;
    private Supplier<Boolean> top, bottom;
    private Double topSpeed, bottomSpeed;

    public  IntakeBall(InTake inTakeSub, Supplier<Boolean> top, Supplier<Boolean> bottom){
        addRequirements(inTakeSub);
        //addRequirements(inTakeSub);
        this.top = top;
        this.bottom = bottom;
        this.inTakeSub = inTakeSub;
    }

    @Override
    public void initialize(){
        topSpeed = 0.0;
        bottomSpeed = 0.0;
    }

    @Override
    public void execute(){
        
        if(top.get()){
            topSpeed = 0.5;
            inTakeSub.moveTopIntake(topSpeed);
        } else {
            topSpeed = 0.0;
            inTakeSub.moveTopIntake(topSpeed);
        }
        

        if(bottom.get()){
            bottomSpeed = 0.5;
            inTakeSub.moveLowerIntake(bottomSpeed);
        } else {
            bottomSpeed = 0.0;
            inTakeSub.moveLowerIntake(bottomSpeed);
        }

    }

    @Override
    public void end(boolean interrupted){
        inTakeSub.moveLowerIntake(0);
        inTakeSub.moveTopIntake(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
