package bessy.subsystems;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import bessy.Bessy;

public class OuttakeSubsystem extends SubsystemBase {
    //private final Trigger encoderStopTrigger;
    private final Bessy mBessy;
    private final CommandOpMode mOpMode;

    public enum OuttakeState {
        IDLE,
        ACTIVE,
    }

    public enum ActiveServo {
        NULL,
        LEFT,
        RIGHT,
        ALL
    }
    private OuttakeState currentState;
    public ActiveServo activeServo;

    private final MotorEx mOuttakeMotor;

    private final Servo mLeftServo;
    private final Servo mRightServo;

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

        if(mBessy.gunnerOp.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1){
            currentState = OuttakeState.ACTIVE;
        } else {
            currentState = OuttakeState.IDLE;
        }


        if(currentState == OuttakeState.ACTIVE){
            mOuttakeMotor.set(.55);
        } else {
            if(currentState == OuttakeState.IDLE){
                mOuttakeMotor.set(0);
            }
        }



        if(activeServo == ActiveServo.LEFT){
            mLeftServo.setPosition(0);
            mRightServo.setPosition(0);
        } else if(activeServo == ActiveServo.RIGHT){
            mLeftServo.setPosition(0);
            mRightServo.setPosition(0);
        } else if(activeServo == ActiveServo.ALL){
            mLeftServo.setPosition(0);
            mRightServo.setPosition(0);
        } else{
            mLeftServo.setPosition(0);
            mRightServo.setPosition(0);
        }


        mOpMode.telemetry.addData("ActiveServo: ", activeServo);
        mOpMode.telemetry.update();
    }
    public void addTelemetry(Telemetry telemetry){
        mOpMode.telemetry.addData("CurrentState: ", currentState);
    }
}