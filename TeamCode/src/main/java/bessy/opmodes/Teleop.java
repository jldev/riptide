package bessy.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import bessy.Bessy;


//values


@TeleOp(name = "Teleop")
public class Teleop extends CommandOpMode {

    private Bessy bessy;

    @Override
    public void initialize() {
        bessy = new Bessy(this, Bessy.OpModeType.TELEOP, Bessy.AllianceColor.RED);

//        this.schedule(new RunCommand(() -> {
//            riptide.vertical.addTelemetry(telemetry);
//            telemetry.addLine("                this is a space");
//            riptide.horizontal.addTelemetry(telemetry);
//            telemetry.addData("Mag switch 1 - ", riptide.magSwitchButton1.get());
//            telemetry.addData("Mag switch 3 - ", riptide.magSwitchButton3.get());
//
//            telemetry.update();
//        }));

        // Drive control
//        MecanumDriveCommand driveCommand = new MecanumDriveCommand(
//                riptide.drive, () -> -riptide.driverOp.getRightY(),
//                () -> -riptide.driverOp.getRightX(), () -> (-riptide.driverOp.getLeftX() * .6)
//        );
//        riptide.drive.setDefaultCommand(driveCommand);

        bessy.follower.startTeleopDrive();

        // THIS IS WHERE ALL THE BUTTONS FOR TELEOP GO
    }

    @Override
    public void run(){
        super.run();

        /* Update Pedro to move the robot based on:
        - Forward/Backward Movement: -gamepad1.left_stick_y
        - Left/Right Movement: -gamepad1.left_stick_x
        - Turn Left/Right Movement: -gamepad1.right_stick_x
        - Robot-Centric Mode: true
        */

        bessy.follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, true);
        bessy.follower.update();

        /* Telemetry Outputs of our Follower */
        telemetry.addData("X", bessy.follower.getPose().getX());
        telemetry.addData("Y", bessy.follower.getPose().getY());
        telemetry.addData("Heading in Degrees", Math.toDegrees(bessy.follower.getPose().getHeading()));

        /* Update Telemetry to the Driver Hub */
        telemetry.update();

    }
}
