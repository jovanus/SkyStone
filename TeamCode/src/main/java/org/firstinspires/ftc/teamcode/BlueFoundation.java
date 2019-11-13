package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue Foundation")

public class BlueFoundation extends BaseAuto {
    @Override
    public void runOpMode() throws InterruptedException {
        Initialize_nocam();
        waitForStart();
        Drive.EnableSensors();
        Drive.ResetSensors();
        tapeMeasure.ResetEnc();

        // Drive to be in position for reeling in foundation
        DrivetoPosition(10);

        // Extend to foundation
        PositionTapeMeasure(-6750);
        tapeMeasure.Drop(true);
        sleep(1000);
        PositionTapeMeasure(-100);
        tapeMeasure.Drop(false);

        // Move Away from wall before claw release
        DrivetoPosition(0);

        // Release arm before driving under bridge
        OpenClaw();

        //Drive to underneath bridge
        DrivetoPosition(-30);
        Drive.DisableSensors();

        while(opModeIsActive());

    }
}
