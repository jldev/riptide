package bessy.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import bessy.Bessy;

@Config
@Autonomous(group = "drive", name = "Red")
public class Red extends CommandOpMode {
    BessyAuto bessyAuto;
    @Override
    public void initialize() {
        bessyAuto = new BessyAuto(this, Bessy.FieldPos.AU, Bessy.AllianceColor.RED);
        bessyAuto.bessy.pushSamples = true;
    }

    @Override
    public void run(){
        bessyAuto.run();
        super.run();
    }
}
