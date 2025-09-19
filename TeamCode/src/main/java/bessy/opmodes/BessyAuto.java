package bessy.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.pedropathing.localization.Pose;

import bessy.Bessy;

public class BessyAuto {

    public Bessy bessy;

    private CommandOpMode opMode;
    public Task currentState = Task.PRELOAD_SPECIMEN;

//    public Pose2d desiredPosition;

    public enum Task{
        // specimen
        PRELOAD_SPECIMEN,
        PUSH_SAMPLES,
        GRAB_SAMPLES,
        HANG_SPECIMEN,
        HANG_LAST_SPECIMEN,
        RETRIEVE_SPECIMEN,
        PARK,

        // basket
        PRELOAD_BASKET,
        RETRIEVE_SAMPLE,
        DEPOSIT_SAMPLE,
        PARK_BASKET,

        // also this
        WAIT_FOR_TASK
    }

    private  int additionalCycles;

    private int runCount = 0;

    private Pose startPos;

    public BessyAuto(CommandOpMode commandOpMode, Bessy.FieldPos startingPosition, Bessy.AllianceColor allianceColor, Bessy.Target target) {
        opMode = commandOpMode;
        bessy = new Bessy(opMode, Bessy.OpModeType.AUTO, allianceColor, this);
        bessy.setStartPosition(startingPosition, allianceColor);
        bessy.target = target;
        if(bessy.target == Bessy.Target.SPECIMENS)
        {
            currentState = Task.PRELOAD_SPECIMEN;
            startPos = new Pose(0, 0, Math.toRadians(180));
        } else
        {
            currentState = Task.PRELOAD_BASKET;
            startPos = new Pose(0, 0, Math.toRadians(270));
        }
        bessy.setStartPosition(startPos);
        additionalCycles = 0;
    }

    public void run() {
        opMode.telemetry.addData("Current State", currentState);
        opMode.telemetry.addData("Run Count", runCount++);
        opMode.telemetry.addData("Cycle Count", additionalCycles);
//        opMode.telemetry.addLine(String.format("Pose X: %.2f, Y: %.2f, Rot: %.2f", riptide.drive.getPoseEstimate().position.x,
//                riptide.drive.getPoseEstimate().position.y, Math.toDegrees(riptide.drive.getPoseEstimate().heading.toDouble())));
        opMode.telemetry.update();

        switch (currentState) {

            // specimen

            case PRELOAD_SPECIMEN:
                break;
            case GRAB_SAMPLES:
                currentState = Task.WAIT_FOR_TASK;
                break;
            case HANG_SPECIMEN:
                currentState = Task.WAIT_FOR_TASK;
                break;
            case HANG_LAST_SPECIMEN:
                currentState = Task.WAIT_FOR_TASK;
                break;
            case RETRIEVE_SPECIMEN:
            case PARK:
                currentState = Task.WAIT_FOR_TASK;
                break;
            case PRELOAD_BASKET:
                currentState = Task.WAIT_FOR_TASK;
                break;
            case RETRIEVE_SAMPLE:
                currentState = Task.WAIT_FOR_TASK;
                break;
            case DEPOSIT_SAMPLE:
                currentState = Task.WAIT_FOR_TASK;
                break;
            case PARK_BASKET:
                currentState = Task.WAIT_FOR_TASK;
                break;


            case WAIT_FOR_TASK:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentState);
        }
    }
}
