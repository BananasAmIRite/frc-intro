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

public class Drivetrain extends SubsystemBase {
    private final CANSparkMax leftMotor1 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax leftMotor2 = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax leftMotor3 = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final MotorControllerGroup leftMotors = new MotorControllerGroup(
        leftMotor1, leftMotor2, leftMotor3
    );

    private final CANSparkMax rightMotor1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax rightMotor2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax rightMotor3 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final MotorControllerGroup rightMotors = new MotorControllerGroup(
        rightMotor1, rightMotor2, rightMotor3
    );

    public final RelativeEncoder leftEncoder = leftMotor1.getEncoder();
    public final RelativeEncoder rightEncoder = rightMotor1.getEncoder();

    private final AHRS gyro = new AHRS(SPI.Port.kMXP);

    private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(gyro.getRotation2d());

    private final SlewRateLimiter throttleFilter = new SlewRateLimiter(Constants.kThrottleFilter);
    private final SlewRateLimiter turnFilter = new SlewRateLimiter(Constants.kTurnFilter);

    public Drivetrain(){
        leftMotor1.setInverted(false);
        leftMotor2.setInverted(false);
        leftMotor3.setInverted(false);

        rightMotor1.setInverted(true);
        rightMotor2.setInverted(true);
        rightMotor3.setInverted(true);

        leftMotor1.setIdleMode(IdleMode.kBrake);
        leftMotor2.setIdleMode(IdleMode.kBrake);
        leftMotor3.setIdleMode(IdleMode.kBrake);

        rightMotor1.setIdleMode(IdleMode.kBrake);
        rightMotor2.setIdleMode(IdleMode.kBrake);
        rightMotor3.setIdleMode(IdleMode.kBrake);

        leftEncoder.setPositionConversionFactor(Constants.Trajectory.kDistPerRot);
        rightEncoder.setPositionConversionFactor(Constants.Trajectory.kDistPerRot);

        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    @Override
    public void periodic() {
        odometry.update(gyro.getRotation2d(), leftEncoder.getPosition(), rightEncoder.getPosition());
    }

    public void drive(double throttle, double turn){
        differentialDrive.arcadeDrive(
            throttleFilter.calculate(throttle),
            turnFilter.calculate(turn)
        );
    }

    public Rotation2D getRotation2D() {
        odometry.getPoseMeters().getRotation(); 
    }
}
