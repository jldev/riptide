package bessy.opmodes;

import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.pedropathing.commands.FollowPath;
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
    @Override
    public void initialize() {
        bessy = new Bessy(this, Bessy.OpModeType.AUTO, Bessy.AllianceColor.BLUE);
//        Pose2d startPos = new Pose2d(0, 0, Math.toRadians(180));
//        riptide.setStartPosition(startPos);
        started = false;
    }

    @Override
    public void run(){
        if(!started)
        {
            started = true;
            Path forward = new Path(new BezierLine(new Point(0,0, Point.CARTESIAN), new Point(60,0, Point.CARTESIAN)));
            this.schedule(new FollowPath(bessy.follower, forward));
//            this.schedule(
//                    new SequentialCommandGroup(
//                            new RoadRunnerDrive(48, 0, riptide.drive),
//                            new RoadRunnerDrive(-48, 0, riptide.drive),
//                            new RoadRunnerDrive(48, 0, riptide.drive),
//                            new RoadRunnerDrive(-48, 0, riptide.drive),
//                            new RoadRunnerDrive(48, 0, riptide.drive),
//                            new RoadRunnerDrive(-48, 0, riptide.drive)
//                    )
//            );
        }

//        telemetry.addLine(String.format("Pose X: %.2f, Y: %.2f, Rot: %.2f", riptide.drive.getPoseEstimate().position.x,
//                riptide.drive.getPoseEstimate().position.y, Math.toDegrees(riptide.drive.getPoseEstimate().heading.toDouble())));
        telemetry.update();
        super.run();
    }
}
