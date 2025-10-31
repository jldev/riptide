package pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;

public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches = .001;
        ThreeWheelConstants.strafeTicksToInches = .004625;
        ThreeWheelConstants.turnTicksToInches = .001;
        ThreeWheelConstants.leftY = 6.25;
        ThreeWheelConstants.rightY = -6;
        ThreeWheelConstants.strafeX = 3.0;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "leftFront";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "rightRear";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "strafeEncoder";
        ThreeWheelConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.rightEncoderDirection = Encoder.FORWARD;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.REVERSE;
    }
}




