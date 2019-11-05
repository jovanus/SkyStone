package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.teamcode.Subsystems.*;

@TeleOp(name = "TeleOp")

public class Teleop extends LinearOpMode {

    final static MechenumDrive Drive = new MechenumDrive();
    final static Lift lift = new Lift();
    final static TapeMeasure tapeMeasure = new TapeMeasure();
    final static Camera camera = new Camera();

    CRServo GrabClaw, RotateClaw;

    VectorF translation;

    int toNum(boolean in) {
        return (in?1:0);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        this.Initialize();
        waitForStart();

        camera.activateTracking();
        while (opModeIsActive()){
            Drive.Drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            GrabClaw.setPower(toNum(gamepad2.a) - toNum(gamepad2.y));
            RotateClaw.setPower(toNum(gamepad2.b) - toNum(gamepad2.x));

            tapeMeasure.manual(gamepad2.right_stick_y, gamepad2.right_stick_x, gamepad2.right_bumper);
            lift.Manual(gamepad2.left_stick_y);


            double[] data = camera.getStoneData();

            telemetry.addData("X Position: ", translation.get(0));
            telemetry.addData("Y Position: ", translation.get(1));
            telemetry.addData("Z Position: ", translation.get(2));




            telemetry.update();
            idle();
        }
        camera.disableTracking();


    }


    public void Initialize(){
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
        Drive.Initialize(init_drive);
        camera.init(hardwareMap.get(WebcamName.class, "Webcam 1"),hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));
    }
}
