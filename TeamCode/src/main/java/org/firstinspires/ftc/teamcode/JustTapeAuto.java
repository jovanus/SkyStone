package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Just Tape")

public class JustTapeAuto extends BaseAuto {
    @Override
    public void runOpMode() throws InterruptedException {
        Initialize_nocam();
        waitForStart();
        tapeMeasure.ResetEnc();

        PositionTapeMeasure(-7500);
    }
}
