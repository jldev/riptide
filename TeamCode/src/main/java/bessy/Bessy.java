package bessy;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.hardware.Servo;

import bessy.subsystems.IntakeSubsystem;
import bessy.subsystems.OuttakeSubsystem;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
import bessy.opmodes.BessyAuto;

public class Bessy {

    public final OpModeType mOpModeType;

//    public final MecanumDriveSubsystem drive;
    public final Follower follower;

    public GamepadEx driverOp;
    public GamepadEx gunnerOp;
    public Pose currentPos;


//    public final SwitchReader magSwitchButton1;
//    public final SwitchReader magSwitchButton3;
//
//    //subsystems
//    public final VerticalSubsystem vertical;
//    public final HorizontalSubsystem horizontal;

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

    public IntakeSubsystem intakeSubsystem;
    public OuttakeSubsystem outtakeSubsystem;

    //           BUTTONSSSSS
    // Gunner
    public GamepadButton leftOuttakeServo;
    public GamepadButton rightOuttakeServo;
    public GamepadButton allOuttakeServo;

    // Driver



    public enum OpModeType {
        TELEOP,
        AUTO
    }
public BessyAuto auto;

    public Bessy(CommandOpMode opMode, OpModeType opModeType, AllianceColor ac, BessyAuto auto) {
        this(opMode, opModeType, ac);
    this.auto = auto;
    }

    public
    Bessy(CommandOpMode opMode, OpModeType opModeType, AllianceColor ac) {
        mOpMode = opMode;
        mOpModeType = opModeType;
        allianceColor = ac;
//        Pose2d initialPose = new Pose2d(12, -62, Math.toRadians(90));
//        drive = new MecanumDriveSubsystem(new MecanumDrive(opMode.hardwareMap, initialPose), false);
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(opMode.hardwareMap);
        driverOp = new GamepadEx(opMode.gamepad1);
        gunnerOp = new GamepadEx(opMode.gamepad2);


        intakeSubsystem = new IntakeSubsystem(this, new MotorEx(opMode.hardwareMap, "intakeMotor", Motor.GoBILDA.RPM_1150), mOpMode);
        outtakeSubsystem = new OuttakeSubsystem(this, new MotorEx(opMode.hardwareMap,
                "outtakeMotor", Motor.GoBILDA.RPM_1150),opMode.hardwareMap.get(Servo.class, "leftServo"),
                opMode.hardwareMap.get(Servo.class, "rightServo"), mOpMode);



        //       GUNNER SETUP

        //outtake servos
        leftOuttakeServo = new GamepadButton(gunnerOp, GamepadKeys.Button.X);
        rightOuttakeServo = new GamepadButton(gunnerOp, GamepadKeys.Button.B);
        allOuttakeServo = new GamepadButton(gunnerOp, GamepadKeys.Button.Y);

        //     DRIVER SETUP

    }



    public void setStartPosition(Pose pos){
        this.currentPos = pos;
        follower.setStartingPose(this.currentPos);
    }
        //Start positions for each auto placement
    public void setStartPosition(FieldPos fp, AllianceColor ac) {

        this.fieldPos = fp;
        this.allianceColor = ac;

        this.currentPos = new Pose(12, -62, Math.toRadians(90));
        follower.setStartingPose(this.currentPos);
    }

    //preset commands and whatever else can go here
}
