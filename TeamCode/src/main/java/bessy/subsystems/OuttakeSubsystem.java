package bessy.subsystems;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import bessy.Bessy;
import bessy.BessyConstants;

public class OuttakeSubsystem extends SubsystemBase {
    //private final Trigger encoderStopTrigger;
    private final Bessy mBessy;
    private final CommandOpMode mOpMode;

    public enum OuttakeState {
        IDLE,
        ACTIVE,
        REVERSE
    }

    public enum ActiveServo {
        NULL,
        LEFT,
        RIGHT,
        ALL
    }
    public OuttakeState currentState;
    public ActiveServo activeServo;

    private final MotorEx mOuttakeMotor;

    private final Servo mLeftServo;
    private final Servo mRightServo;

    public double outtakePower = 0.62;

    public OuttakeSubsystem(Bessy bessy, MotorEx outtakeMotor, Servo leftServo, Servo rightServo, CommandOpMode opmode) {
        mBessy = bessy;
        mOuttakeMotor = outtakeMotor;
        mLeftServo = leftServo;
        mRightServo = rightServo;
        mOpMode = opmode;

        mOuttakeMotor.stopAndResetEncoder();
        mOuttakeMotor.setRunMode(MotorEx.RunMode.RawPower);
        mOuttakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        mOuttakeMotor.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        mOuttakeMotor.encoder.setDirection(Motor.Direction.REVERSE);

        opmode.telemetry.addLine("Outtake Init");
        opmode.telemetry.update();

        currentState = OuttakeState.IDLE;
        activeServo = ActiveServo.NULL;
    }

    @Override
    public void periodic() {

        if(mBessy.mOpModeType == Bessy.OpModeType.TELEOP)  //purely teleop control
        {
            if (mBessy.gunnerOp.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1) {
                currentState = OuttakeState.ACTIVE;
            } else if (mBessy.gunnerOp.getButton(GamepadKeys.Button.RIGHT_BUMPER)){
            currentState = OuttakeState.REVERSE;
            } else {
            currentState = OuttakeState.IDLE;
        }
        }


        if(currentState == OuttakeState.ACTIVE){
            mOuttakeMotor.motor.setDirection(DcMotorSimple.Direction.REVERSE);
            mOuttakeMotor.set(.62);
        } else if(currentState == OuttakeState.REVERSE){
            mOuttakeMotor.motor.setDirection(DcMotorSimple.Direction.FORWARD);
            mOuttakeMotor.set(.5);
        }else {
            if(currentState == OuttakeState.IDLE){
                mOuttakeMotor.set(0);
            }
        }



        if(activeServo == ActiveServo.LEFT){
            mLeftServo.setPosition(BessyConstants.LEFT_SERVO_LAUNCH_POSITION);
            mRightServo.setPosition(BessyConstants.RIGHT_SERVO_IDLE_POSITION);
        } else if(activeServo == ActiveServo.RIGHT){
            mLeftServo.setPosition(BessyConstants.LEFT_SERVO_IDLE_POSITION);
            mRightServo.setPosition(BessyConstants.RIGHT_SERVO_LAUNCH_POSITION);
        } else if(activeServo == ActiveServo.ALL){
            mLeftServo.setPosition(BessyConstants.LEFT_SERVO_LAUNCH_POSITION);
            mRightServo.setPosition(BessyConstants.RIGHT_SERVO_LAUNCH_POSITION);
        } else{
            mLeftServo.setPosition(BessyConstants.LEFT_SERVO_IDLE_POSITION);
            mRightServo.setPosition(BessyConstants.RIGHT_SERVO_IDLE_POSITION);
        }


        mOpMode.telemetry.addData("ActiveServo: ", activeServo);
        mOpMode.telemetry.update();
    }


    public Command ActivateServo(ActiveServo servo){
        return new SequentialCommandGroup(
                new InstantCommand(() -> activeServo = servo),
                new WaitCommand(350),
                new InstantCommand(() -> activeServo = OuttakeSubsystem.ActiveServo.NULL)
        );
    }

    public  Command ChangePowerBy(double amount){
        return new InstantCommand(() -> outtakePower += amount);
    }

    public void addTelemetry(Telemetry telemetry){
        mOpMode.telemetry.addData("CurrentState: ", currentState);
    }
}