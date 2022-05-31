package frc.robot.subsystems.drivetrain;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
    private final CANSparkMax indexMotor;
    private float rotationPower = 1; 

    public Intake(int port) {
        indexMotor = new CANSparkMax(port, CANSparkMaxLowLevel.MotorType.kBrushless); 
    }

    public void setRotationPower(float pow) {
        this.rotationPower = pow;  
    }

    public void runUp() { // these may be reversed depending on motor placement
        this.intakeMotor.setPower(this.rotationPower); 
    }

    public void runDown() {
        this.intakeMotor.setPower(-this.rotationPower); 
    }

    public void stop() {
        this.intakeMotor.setPower(0); 
    }
}
