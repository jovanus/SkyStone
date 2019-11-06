package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Subsystems.*;


public abstract class BaseAuto extends LinearOpMode {

    AutoDrive Drive = new AutoDrive();
    final static Lift lift = new Lift();
    final static TapeMeasure tapeMeasure = new TapeMeasure();
    final static Camera camera = new Camera();

    CRServo GrabClaw, RotateClaw;

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

    protected void DriveSidewaystoPosition(double Position){
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

    private final static double SCALER = 1.0;
    protected int InToTicks(double in){
        return (int)(SCALER*in);
    }

    protected void Initialize(){
        final DcMotor init_drive[] = {
                hardwareMap.dcMotor.get("D_FL"),
                hardwareMap.dcMotor.get("D_FR"),
                hardwareMap.dcMotor.get("D_RL"),
                hardwareMap.dcMotor.get("D_RR")};

        final DcMotor init_Lift[] = {
                hardwareMap.dcMotor.get("Lift1"),
                hardwareMap.dcMotor.get("Lift2"),
                hardwareMap.dcMotor.get("Lift3")
        };

        final DcMotor init_TapeMeasure = hardwareMap.dcMotor.get("TapeMeasure");

        final CRServo init_GrabClaw = hardwareMap.get(CRServo.class, "GrabClaw");
        final CRServo init_RotateClaw = hardwareMap.get(CRServo.class, "RotateClaw");
        final CRServo init_AimTapeMeasure = hardwareMap.get(CRServo.class, "AimTapeMeasure");
        final Servo init_DropTapeMeasure = hardwareMap.servo.get("DropTapeMeasure");


        // Servos
        RotateClaw = init_RotateClaw;
        GrabClaw = init_GrabClaw;

        // Subsystems
        tapeMeasure.init(init_AimTapeMeasure, init_DropTapeMeasure, init_TapeMeasure);
        lift.init(init_Lift);
        Drive.initialize(init_drive,
                hardwareMap.get(BNO055IMU.class,"imu"));
        camera.init(hardwareMap.get(WebcamName.class, "Webcam 1"),hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));
    }

    @Override
    public abstract void runOpMode() throws InterruptedException;
}
