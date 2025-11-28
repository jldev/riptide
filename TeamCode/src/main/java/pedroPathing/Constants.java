package pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(9.979024)
            .forwardZeroPowerAcceleration(-27)
            .lateralZeroPowerAcceleration(-100.4008)
            .useSecondaryTranslationalPIDF(false)
            .useSecondaryHeadingPIDF(false)
            .useSecondaryDrivePIDF(false)
            .centripetalScaling(0.0005)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.1, 0, 0.01, 0))
            .headingPIDFCoefficients(new PIDFCoefficients(3.5, 0, 0.25, 0))
            .drivePIDFCoefficients(
                    new FilteredPIDFCoefficients(0.02, 0, 0.002, 0.6, 0)
            );

    public static MecanumConstants driveConstants = new MecanumConstants()
            .leftFrontMotorName("leftFront")
            .leftRearMotorName("leftRear")
            .rightFrontMotorName("rightFront")
            .rightRearMotorName("rightRear")
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .xVelocity(78.6167)
            .yVelocity(63.3578);

    public static ThreeWheelConstants localizerConstants =
            new ThreeWheelConstants()
                    .forwardTicksToInches(.0011)
                    .strafeTicksToInches(.0011)
                    .turnTicksToInches(.001)
                    .leftPodY(6.25)
                    .rightPodY(-6)
                    .strafePodX(-3.0)
                    .leftEncoder_HardwareMapName("leftFront")
                    .rightEncoder_HardwareMapName("rightRear")
                    .strafeEncoder_HardwareMapName("strafeEncoder")
                    .leftEncoderDirection(Encoder.FORWARD)
                    .rightEncoderDirection(Encoder.REVERSE)
                    .strafeEncoderDirection(Encoder.REVERSE);

    public static PathConstraints pathConstraints = new PathConstraints(
            0.995,
            500,
            1.5,
            1
    );

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .threeWheelLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .build();
    }

    //    com.pedropathing.localization.constants.ThreeWheelConstants.forwardTicksToInches = .0011;
//    com.pedropathing.localization.constants.ThreeWheelConstants.strafeTicksToInches = -.0011;
//    com.pedropathing.localization.constants.ThreeWheelConstants.turnTicksToInches = .001;
//    com.pedropathing.localization.constants.ThreeWheelConstants.leftY = 6.25;
//    com.pedropathing.localization.constants.ThreeWheelConstants.rightY = -6;
//    com.pedropathing.localization.constants.ThreeWheelConstants.strafeX = -3.0;
//    com.pedropathing.localization.constants.ThreeWheelConstants.leftEncoder_HardwareMapName = "leftFront";
//    com.pedropathing.localization.constants.ThreeWheelConstants.rightEncoder_HardwareMapName = "rightRear";
//    com.pedropathing.localization.constants.ThreeWheelConstants.strafeEncoder_HardwareMapName = "strafeEncoder";
//    com.pedropathing.localization.constants.ThreeWheelConstants.leftEncoderDirection = com.pedropathing.localization.Encoder.REVERSE;
//    com.pedropathing.localization.constants.ThreeWheelConstants.rightEncoderDirection = com.pedropathing.localization.Encoder.FORWARD;
//    com.pedropathing.localization.constants.ThreeWheelConstants.strafeEncoderDirection = com.pedropathing.localization.Encoder.FORWARD;
}
