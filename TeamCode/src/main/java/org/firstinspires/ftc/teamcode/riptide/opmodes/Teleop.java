package org.firstinspires.ftc.teamcode.riptide.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.riptide.Riptide;
import org.firstinspires.ftc.teamcode.riptide.commands.SwerveDriveCommand;


//values


@TeleOp(name = "Teleop")
public class Teleop extends CommandOpMode {

    private Riptide riptide;

    @Override
    public void initialize() {
        riptide = new Riptide(this, Riptide.OpModeType.TELEOP, Riptide.AllianceColor.RED);

        this.schedule(new RunCommand(() -> {
            telemetry.update();
        }));

        // Drive control
        SwerveDriveCommand driveCommand = new SwerveDriveCommand(
                riptide.drive, () -> -riptide.driverOp.getRightY(),
                () -> -riptide.driverOp.getRightX(), () -> (-riptide.driverOp.getLeftX() * .6)
        );
        riptide.drive.setDefaultCommand(driveCommand);
    }

}
