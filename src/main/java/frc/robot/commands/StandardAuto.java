package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup; 
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.RunCommand; 

import frc.robot.subsystems.drivetrain.Drivetrain;

public class StandardAuto extends SequentialCommandGroup {
    // put subsystem definitions here

    public StandardAuto(Drivetrain drivetrain){

        addCommands(
          new ParallelCommandGroup(
            new WaitCommand(1), 
            new RunCommand(() -> {
              drivetrain.drive(1, 0); 
            })
          ),
          new TurnToAngle(drivetrain, Rotation2d.fromDegrees(90)),
          new ParallelCommandGroup(
            new WaitCommand(1), 
            new RunCommand(() -> {
              drivetrain.drive(1, 0); 
            })
          ),
          new TurnToAngle(drivetrain, Rotation2d.fromDegrees(180)),
          new ParallelCommandGroup(
            new WaitCommand(1), 
            new RunCommand(() -> {
              drivetrain.drive(1, 0); 
            })
          )
        );

        addRequirements(drivetrain);

    }

    /*  runs once the command has finished/been interrupted  */
    @Override
    public void end(boolean interrupted){
        
    }
}
