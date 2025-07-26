package org.firstinspires.ftc.teamcode.riptide;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.riptide.commands.ServoPositionCommand;
import org.firstinspires.ftc.teamcode.riptide.commands.SwerveDriveCommand;
import org.firstinspires.ftc.teamcode.riptide.subsystems.SwerveDriveSubsystem;
import org.firstinspires.ftc.teamcode.riptide.subsystems.WFISwerveDrive;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class Riptide {

    public final OpModeType mOpModeType;

    public final SwerveDriveSubsystem drive;

    public GamepadEx driverOp;
    public GamepadEx gunnerOp;
    public Pose2d currentPos;

    public enum FieldPos {
        AU,
        BD
    }

    public FieldPos fieldPos;
    public AllianceColor allianceColor;

    public final CommandOpMode mOpMode;
    public enum AllianceColor {
        RED,
        BLUE
    }
    public enum Target {
        SPECIMENS,
        SAMPLES
    }
    public Target target = Target.SPECIMENS;
    public boolean pushSamples = true;

    //           BUTTONSSSSS
    // Gunner

    // Driver



    public enum OpModeType {
        TELEOP,
        AUTO
    }

    public Riptide(CommandOpMode opMode, OpModeType opModeType, AllianceColor ac) {
        mOpMode = opMode;
        mOpModeType = opModeType;
        allianceColor = ac;
        Pose2d initialPose = new Pose2d(12, -62, Math.toRadians(90));
        drive = new SwerveDriveSubsystem(new WFISwerveDrive(
                opMode.hardwareMap, initialPose), false);
        driverOp = new GamepadEx(opMode.gamepad1);
        gunnerOp = new GamepadEx(opMode.gamepad2);
    }



    public void setStartPosition(Pose2d pos){
        this.currentPos = pos;
        drive.setPoseEstimate(this.currentPos);
    }
        //Start positions for each auto placement
    public void setStartPosition(FieldPos fp, AllianceColor ac) {

        this.fieldPos = fp;
        this.allianceColor = ac;

        this.currentPos = new Pose2d(12, -62, Math.toRadians(90));

        drive.setPoseEstimate(this.currentPos);
    }
}
