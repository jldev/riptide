package bessy.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import bessy.Bessy;
import bessy.commands.PedroFollowPath;
import bessy.subsystems.OuttakeSubsystem;

@Config
@Autonomous(group = "drive", name = "BacknForth")
public class BacknForth extends CommandOpMode {
    Bessy bessy;
    private boolean started = false;

    private final Pose startPose = new Pose(60, 120, Math.toRadians(90)); // Start Pose of our robot.
    private final Pose scorePose = new Pose(60, 96, Math.toRadians(90)); // Scoring Pose of our robot. It is facing the goal at a 135 degree angle.

//    private final Pose testWaypointA = new Pose(20, 20, Math.toRadians(90));
//    private final Pose testWaypointB = new Pose(20, 44, Math.toRadians(0));
//    private final Pose testWaypointC = new Pose(44, 44, Math.toRadians(0));

    private final Pose testWaypointA = new Pose(20, 20, Math.toRadians(90));
    private final Pose testWaypointB = new Pose(20, -4, Math.toRadians(135));
    private final Pose testWaypointC = new Pose(30, -4, Math.toRadians(135));

    @Override
    public void initialize() {
        bessy = new Bessy(this, Bessy.OpModeType.AUTO, Bessy.AllianceColor.RED);
//        Pose2d startPos = new Pose2d(0, 0, Math.toRadians(180));
//        riptide.setStartPosition(startPos);
        bessy.setStartPosition(testWaypointA);
        started = false;
    }

    @Override
    public void run(){
        if(!started)
        {
            started = true;
            telemetry.addLine("Started");
            telemetry.update();

            Path testA = new Path(new BezierLine(testWaypointA, testWaypointB));
            Path testB = new Path(new BezierLine(testWaypointB, testWaypointC));
            PathChain pathChain = bessy.follower.pathBuilder().addPath(testA).addPath(testB).build();
            // you can keep adding paths here check https://pedropathing.com/docs/pathing/reference/path-builder
            this.schedule(new PedroFollowPath(bessy.follower, pathChain));
//            this.schedule(bessy.Launch(OuttakeSubsystem.ActiveServo.ALL));
        }

        telemetry.addLine(String.format("Pose X: %.2f, Y: %.2f, Rot: %.2f", bessy.follower.getPose().getX(),
                bessy.follower.getPose().getY(), Math.toDegrees(bessy.follower.getPose().getHeading())));
        telemetry.update();
        bessy.follower.update();
        super.run();
    }
}