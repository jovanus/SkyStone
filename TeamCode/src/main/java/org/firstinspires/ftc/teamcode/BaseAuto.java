package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Subsystems.*;


public abstract class BaseAuto extends LinearOpMode {

    AutoDrive Drive = new AutoDrive();

    protected void TurnToHeading(double Angle){
        Drive.GyroPID.setSetPoint(Angle);
        sleep(30);
        telemetry.addLine()
                .addData("Run: ", Drive.isHeadingInTolerance())
                .addData("Error :", Drive.getHeadingError())
                .addData("Output :", Drive.GyroPID.getOutput());
        telemetry.update();

        while (!Drive.isHeadingInTolerance() && opModeIsActive()){
            telemetry.addLine()
                    .addData("Run: ", Drive.isHeadingInTolerance())
                    .addData("Error :", Drive.getHeadingError())
                    .addData("Output :", Drive.GyroPID.getOutput());
            telemetry.update();
            Drive.TurnToHeading();
            sleep(10);
        }
        Drive.Stop();
    }

    protected void DrivetoPosition(double Position){
        Drive.EncPID.setSetPoint(Position);
        telemetry.addLine()
                .addData("Run: ", Drive.isPositionInTolerance())
                .addData("Error :", Drive.PositionError())
                .addData("Output :", Drive.EncPID.getOutput());
        telemetry.update();

        while (!Drive.isPositionInTolerance() && opModeIsActive()){
            telemetry.addLine()
                    .addData("Run: ", Drive.isPositionInTolerance())
                    .addData("Error :", Drive.PositionError())
                    .addData("Output :", Drive.EncPID.getOutput());
            telemetry.update();
            Drive.DriveToPosition();
        }
        Drive.Stop();
    }

    public void Initialize(){
        final DcMotor init_drive[] = {
                hardwareMap.dcMotor.get("D_FL"),
                hardwareMap.dcMotor.get("D_FR"),
                hardwareMap.dcMotor.get("D_RL"),
                hardwareMap.dcMotor.get("D_RR")};

        // Initializers
        Drive.initialize(init_drive,
                hardwareMap.get(BNO055IMU.class,"imu"));
    }



    @Override
    public abstract void runOpMode() throws InterruptedException;
}
