package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup; 
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.RunCommand; 

import frc.robot.subsystems.drivetrain.Drivetrain;

public class OuttakeBall extends SequentialCommandGroup {
    public OuttakeBall(Intake intake, double outtakeFor){

        addCommands(
          new ParallelCommandGroup(
            new WaitCommand(outtakeFor), 
            new RunCommand(() -> {
              intake.outtake(); 
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
