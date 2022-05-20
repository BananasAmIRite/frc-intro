package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup; 
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.RunCommand; 

import frc.robot.subsystems.drivetrain.Drivetrain;

public class TurnToAngle extends Command {
    // put subsystem definitions here
    private Drivetrain drivetrain; 
    private Rotation2d target; 
    private final double ERROR_DEGREES = 2; 
    private boolean isEnded = false; 

    public TurnToAngle(Drivetrain drivetrain, Rotation2d target) {
        addRequirements(drivetrain);
    }

    @Override
    protected void execute() {
        Rotation2d rotation = drivetrain.getRotation2D(); 
        if (Math.abs(target.getDegrees() - rotation.getDegrees()) > ERROR_DEGREES) {
            drivetrain.drive(
                0, 
                ((target.getDegrees() - rotation.getDegrees()) / (Math.abs(target.getDegrees() - rotation.getDegrees()))) // get sign
                 * 0.1 // power to turn at
            ); 
        } else {
            isEnded = true; 
        }
    }

    @Override
    protected boolean isFinished() {
        return isEnded; 
    }

    /*  runs once the command has finished/been interrupted  */
    @Override
    public void end(boolean interrupted){
        
    }
}
