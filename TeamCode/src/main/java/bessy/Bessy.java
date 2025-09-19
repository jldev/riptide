package bessy;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;

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

    //           BUTTONSSSSS
    // Gunner
    public GamepadButton verticleSlideUp;
    public GamepadButton verticleSlideDown;
    public GamepadButton horizontalSlideOut;
    public GamepadButton horizontalSlideIn;

    public GamepadButton home_slidePreset;
    public GamepadButton wall_slidePreset;
    public GamepadButton hang_slidePreset;
    public GamepadButton basket_slidePreset;
    public GamepadButton speed_switch_switcher;
    public GamepadTriggerAsButton horizontalClawButton;
    public GamepadTriggerAsButton verticalClawButton;

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

    public Bessy(CommandOpMode opMode, OpModeType opModeType, AllianceColor ac) {
        mOpMode = opMode;
        mOpModeType = opModeType;
        allianceColor = ac;
//        Pose2d initialPose = new Pose2d(12, -62, Math.toRadians(90));
//        drive = new MecanumDriveSubsystem(new MecanumDrive(opMode.hardwareMap, initialPose), false);
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(opMode.hardwareMap);
        driverOp = new GamepadEx(opMode.gamepad1);
        gunnerOp = new GamepadEx(opMode.gamepad2);

        //       gunner setup
        //slide manual
        verticleSlideUp = new GamepadButton(gunnerOp, GamepadKeys.Button.DPAD_UP);
        verticleSlideDown = new GamepadButton(gunnerOp, GamepadKeys.Button.DPAD_DOWN);

        horizontalSlideOut = new GamepadButton(gunnerOp, GamepadKeys.Button.DPAD_LEFT);
        horizontalSlideIn = new GamepadButton(gunnerOp, GamepadKeys.Button.DPAD_RIGHT);

//        horizontalClawDown = new GamepadButton(gunnerOp, GamepadKeys.Button.LEFT_BUMPER);

           // presets
        home_slidePreset = new GamepadButton(gunnerOp, GamepadKeys.Button.A);
        wall_slidePreset = new GamepadButton(gunnerOp, GamepadKeys.Button.X);
        hang_slidePreset = new GamepadButton(gunnerOp, GamepadKeys.Button.B);
        basket_slidePreset = new GamepadButton(gunnerOp, GamepadKeys.Button.Y);

        horizontalClawButton = new GamepadTriggerAsButton(gunnerOp, GamepadKeys.Trigger.LEFT_TRIGGER, 0.5);
        verticalClawButton = new GamepadTriggerAsButton(gunnerOp, GamepadKeys.Trigger.RIGHT_TRIGGER, 0.5);
        //     driver setup

        speed_switch_switcher = new GamepadButton(driverOp, GamepadKeys.Button.DPAD_UP);

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
