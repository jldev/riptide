package bessy.subsystems;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.riptide.Riptide;
//import org.firstinspires.ftc.teamcode.riptide.RiptideConstants;

import bessy.Bessy;

public class IntakeSubsystem extends SubsystemBase {
    //private final Trigger encoderStopTrigger;
    private final Bessy mBessy;
    private final CommandOpMode mOpMode;

    public enum IntakeState {
        IDLE,
        INTAKE,
        REVERSE
    }
    private IntakeState currentState;

    private final MotorEx mIntakeMotor;

    public IntakeSubsystem(Bessy bessy, MotorEx intakeMotor, CommandOpMode opmode) {
        mBessy = bessy;
        mIntakeMotor = intakeMotor;
        mOpMode = opmode;

        mIntakeMotor.stopAndResetEncoder();
        mIntakeMotor.setRunMode(MotorEx.RunMode.RawPower);
        mIntakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        mIntakeMotor.motor.setDirection(DcMotorSimple.Direction.FORWARD);
        mIntakeMotor.encoder.setDirection(Motor.Direction.REVERSE);

        opmode.telemetry.addLine("Intake Init");
        opmode.telemetry.update();

        currentState = IntakeState.IDLE;
    }

    @Override
    public void periodic() {

        if(mBessy.gunnerOp.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1){
            currentState = IntakeState.INTAKE;
        } else {
            currentState = IntakeState.IDLE;
        }
        if(mBessy.gunnerOp.getButton(GamepadKeys.Button.DPAD_DOWN))
            currentState = IntakeState.REVERSE;



        if(currentState == IntakeState.INTAKE){
            mIntakeMotor.set(1);
        } else {
            if(currentState == IntakeState.IDLE){
                mIntakeMotor.set(0);
            } else{
                mIntakeMotor.set(-1);
            }
        }
    }

    public void addTelemetry(Telemetry telemetry){
        mOpMode.telemetry.addData("CurrentState: ", currentState);
    }
}