package bessy.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import bessy.Bessy;
import bessy.commands.PedroFollowPath;

public class BessyAuto {

    public Bessy bessy;

    private CommandOpMode opMode;
    public Task currentState = Task.PRELOAD_SPECIMEN;

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

    //  RED? idk do some fancy translation later or something
    private final Pose startRed = new Pose(128, 113, Math.toRadians(90));
    private final Pose launchRed = new Pose(104, 113, Math.toRadians(90));

    private boolean started = false;
    public BessyAuto(CommandOpMode commandOpMode, Bessy.FieldPos startingPosition, Bessy.AllianceColor allianceColor) {
        opMode = commandOpMode;
        bessy = new Bessy(opMode, Bessy.OpModeType.AUTO, allianceColor, this);
//        bessy.setStartPosition(startingPosition, allianceColor);
        additionalCycles = 0;

        bessy.setStartPosition(startRed);
        started = false;
    }

    public void run() {
//        opMode.telemetry.addData("Current State", currentState);
//        opMode.telemetry.addData("Run Count", runCount++);
//        opMode.telemetry.addData("Cycle Count", additionalCycles);
//        opMode.telemetry.update();

        if(!started) {
            started = true;
            Path testA = new Path(new BezierLine(startRed, launchRed));
            PathChain pathChain = bessy.follower.pathBuilder().addPath(testA).setLinearHeadingInterpolation(startRed.getHeading(), launchRed.getHeading()) .build();
            opMode.schedule(new PedroFollowPath(bessy.follower, pathChain));
        }

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
        opMode.telemetry.addLine(String.format("Pose X: %.2f, Y: %.2f, Rot: %.2f", bessy.follower.getPose().getX(),
                bessy.follower.getPose().getY(), Math.toDegrees(bessy.follower.getPose().getHeading())));
        opMode.telemetry.update();
        bessy.follower.update();
    }
}
