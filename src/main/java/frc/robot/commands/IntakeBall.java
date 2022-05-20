package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup; 
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.RunCommand; 

import frc.robot.subsystems.intake.Intake;

public class IntakeBall extends SequentialCommandGroup {
    public IntakeBall(Intake intake, double intakeFor){

        addCommands(
          new ParallelCommandGroup(
            new WaitCommand(intakeFor), 
            new RunCommand(() -> {
              intake.intake(); 
            })
          ),
          new RunCommand(() -> {
            intake.stop(); 
          })
        );

        addRequirements(intake);

    }

    /*  runs once the command has finished/been interrupted  */
    @Override
    public void end(boolean interrupted){
        
    }
}
