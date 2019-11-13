package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red Foundation")

public class RedFoundation extends BaseAuto {
    @Override
    public void runOpMode() throws InterruptedException {
        Initialize_nocam();
        waitForStart();
        Drive.EnableSensors();

        // Drive to be in position for reeling in foundation
        DrivetoPosition(-12);

        // Extend to foundation
        PositionTapeMeasure(-6750);
        tapeMeasure.Drop(true);
        sleep(1000);
        PositionTapeMeasure(-100);
        tapeMeasure.Drop(false);

        // Release arm before driving under bridge
        OpenClaw();

        //Drive to underneath bridge
        DrivetoPosition(30);
        Drive.DisableSensors();

        while(opModeIsActive());
    }

}
