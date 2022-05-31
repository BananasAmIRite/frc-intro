package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup; 
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.RunCommand; 

import frc.robot.subsystems.intake.Intake;

public class StopRunBall extends CommandBase {
    private final Indexer indexer; 
    public StopRunBall(Indexer indexer) {
        addRequirements(indexer);
    }

    @Override
    public void execute() {
        indexer.stop(); 
    }

    /*  runs once the command has finished/been interrupted  */
    @Override
    public void end(boolean interrupted) {
        return true; 
    }
}
