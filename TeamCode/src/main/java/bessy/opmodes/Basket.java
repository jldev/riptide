package bessy.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import bessy.Bessy;

@Config
@Autonomous(group = "drive", name = "Basket")
public class Basket extends CommandOpMode {
    BessyAuto bessyAuto;
    @Override
    public void initialize() {
        bessyAuto = new BessyAuto(this, Bessy.FieldPos.AU, Bessy.AllianceColor.RED, Bessy.Target.SAMPLES);
        bessyAuto.bessy.pushSamples = true;
    }

    @Override
    public void run(){
        bessyAuto.run();
        super.run();
    }
}
