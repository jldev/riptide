package bessy.opmodes;

import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.pedropathing.commands.FollowPath;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import bessy.Bessy;

@Config
@Autonomous(group = "drive", name = "BacknForth")
public class BacknForth extends CommandOpMode {
    Bessy bessy;
    private boolean started = false;

    private final Pose startPose = new Pose(60, 120, Math.toRadians(90)); // Start Pose of our robot.
    private final Pose scorePose = new Pose(60, 96, Math.toRadians(90)); // Scoring Pose of our robot. It is facing the goal at a 135 degree angle.

    private final Pose testWaypointA = new Pose(0, 0, Math.toRadians(0));
    private final Pose testWaypointB = new Pose(0, 10, Math.toRadians(0));

    @Override
    public void initialize() {
        bessy = new Bessy(this, Bessy.OpModeType.AUTO, Bessy.AllianceColor.RED);
//        Pose2d startPos = new Pose2d(0, 0, Math.toRadians(180));
//        riptide.setStartPosition(startPos);
        bessy.setStartPosition(new Pose(0, 0, 0));
        started = false;
    }

    @Override
    public void run(){
        if(!started)
        {
            started = true;
            telemetry.addLine("Started");
            telemetry.update();

//            Path forward = new Path(new BezierLine(new Point(startPose), new Point(scorePose)));
//            Path forward = new Path(new BezierLine(new Point(bessy.follower.getPose().getX(), bessy.follower.getPose().getY()), new Point(bessy.follower.getPose().getX(), bessy.follower.getPose().getY() + 10)));

            Path forward = new Path(new BezierLine(new Point(testWaypointA), new Point(testWaypointB)));
            this.schedule(new FollowPath(bessy.follower, forward)); // josh has this
//            bessy.follower.followPath(forward);
        }

//        telemetry.addLine(String.format("Pose X: %.2f, Y: %.2f, Rot: %.2f", riptide.drive.getPoseEstimate().position.x,
//                riptide.drive.getPoseEstimate().position.y, Math.toDegrees(riptide.drive.getPoseEstimate().heading.toDouble())));
        telemetry.update();
        bessy.follower.update();
        super.run();
    }
}
