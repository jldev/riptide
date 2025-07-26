package org.firstinspires.ftc.teamcode.riptide.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.riptide.subsystems.SwerveDriveSubsystem;

import java.util.function.DoubleSupplier;

public class SwerveDriveCommand extends CommandBase {

    private final SwerveDriveSubsystem drive;
    private final DoubleSupplier leftY, leftX, rightX;

    public SwerveDriveCommand(SwerveDriveSubsystem drive, DoubleSupplier leftY,
                               DoubleSupplier leftX, DoubleSupplier rightX) {
        this.drive = drive;
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
        addRequirements(drive);
    }

    @Override
    public void execute() {
            drive.drive(leftY.getAsDouble(), leftX.getAsDouble(), rightX.getAsDouble());
    }
}