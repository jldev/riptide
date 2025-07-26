package org.firstinspires.ftc.teamcode.riptide.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.riptide.subsystems.SwerveDriveSubsystem;

public class SimpleDriveCommand extends CommandBase {

    private final SwerveDriveSubsystem drive;
    private final SwerveDriveSubsystem.DriveDirection direction;
    private final double distanceInches;


    public SimpleDriveCommand(SwerveDriveSubsystem drive, SwerveDriveSubsystem.DriveDirection direction, double inches ) {
        this.drive = drive;
        this.direction = direction;
        this.distanceInches = inches;

        addRequirements(drive);
    }

    @Override
    public void initialize() { this.drive.driveDirection(direction, distanceInches);}

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }

    @Override
    public boolean isFinished() {
        return Thread.currentThread().isInterrupted() || !drive.isBusy();
    }
}
