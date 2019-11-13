package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Red Block")
@Disabled

public class RedBlockAuto extends BaseAuto {
    @Override
    public void runOpMode() throws InterruptedException {

        Initialize();
        telemetry.addData("Status", "0");
        telemetry.update();
        waitForStart();
        camera.activateTracking();
        Drive.EnableSensors();
        Drive.ResetSensors();
        Drive.GyroPID.setSetPoint(0);
        telemetry.addData("Status", "1");
        telemetry.update();
        DrivetoPosition(10.0);
        sleep(1000);
        boolean stoneSeen = camera.getStoneData()[1] != 0;

        // cycle over the possible positions
        int i = 0;
        while (!stoneSeen && opModeIsActive()){
            Drive.ResetEnc();
            sleep(20);
            DriveSidewaystoPosition(-11);
            sleep(1000);
            stoneSeen = camera.getStoneData()[1] != 0;
            i++;
        }

        switch (i){
            case 0:
                blockPosition = BlockPosition.RIGHT;
                break;
            case 1:
                blockPosition = BlockPosition.CENTER;
                break;
            case 2:
                blockPosition = BlockPosition.LEFT;
                break;
        }

        // Determine distance to move forward before capturing block
        double[] relativePositionToCapture = camera.getStoneData();

        claw.manual(1, 0);
        sleep(3000);
        claw.manual(0,0);

        Drive.EncPID.Reset();
        sleep(20);
        DrivetoPosition(-relativePositionToCapture[0] - 3);
        Drive.EncPID.Reset();
        sleep(20);
        DriveSidewaystoPosition(relativePositionToCapture[1]);

        claw.manual(-1, 0);
        sleep(3000);
        claw.manual(0,0);

        Drive.EncPID.Reset();
        sleep(20);
        DrivetoPosition(-5);

        TurnToHeading( -90);









    }
}
