package bessy.opmodes;

import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import bessy.Bessy;

@Config
@Autonomous(group = "drive", name = "Strafe")
public class Strafe extends CommandOpMode {
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
//            this.schedule(
//                    new SequentialCommandGroup(
//                            new RoadRunnerDrive(0, 48, riptide.drive),
//                            new RoadRunnerDrive(0, -48, riptide.drive),
//                            new RoadRunnerDrive(0, 48, riptide.drive),
//                            new RoadRunnerDrive(0, -48, riptide.drive),
//                            new RoadRunnerDrive(0, 48, riptide.drive),
//                            new RoadRunnerDrive(0, -48, riptide.drive)
//                    )
//            );
        }
        started = true;
//        telemetry.addLine(String.format("Pose X: %.2f, Y: %.2f, Rot: %.2f", riptide.drive.getPoseEstimate().position.x,
//                riptide.drive.getPoseEstimate().position.y, Math.toDegrees(riptide.drive.getPoseEstimate().heading.toDouble())));
        telemetry.update();
        super.run();
    }
}
