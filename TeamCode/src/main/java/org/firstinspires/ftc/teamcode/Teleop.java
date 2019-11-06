package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Subsystems.*;

@TeleOp(name = "TeleOp")

public class Teleop extends LinearOpMode {

    final static MechenumDrive drive = new MechenumDrive();
    final static Lift lift = new Lift();
    final static TapeMeasure tapeMeasure = new TapeMeasure();
    final static Claw claw = new Claw();

    Servo foundationGrab;

    int toNum(boolean in) {
        return (in?1:0);
    }

    boolean toggle = true;
    boolean bRelease = true;

    @Override
    public void runOpMode() throws InterruptedException {

        this.Initialize();
        waitForStart();

        while (opModeIsActive()){
            drive.Drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            claw.manual(toNum(gamepad2.a) - toNum(gamepad2.y),toNum(gamepad2.b) - toNum(gamepad2.x));
            tapeMeasure.manual(gamepad2.right_stick_y, gamepad2.right_stick_x, gamepad2.right_bumper);
            lift.Manual(gamepad2.left_stick_y);

            if (bRelease && gamepad1.right_bumper){
                toggle = !toggle;
                bRelease = !bRelease;
            }
            else{
                bRelease = !gamepad1.right_bumper;
            }
            foundationGrab.setPosition(toggle ? 0 : 1);

            telemetry.addLine("Drive Encoders: ")
            .addData("FL", drive.getEncoders());

            telemetry.update();
            idle();
        }
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
        final Servo init_FoundationGrab = hardwareMap.servo.get("FoundationGrab");

        // Servos
        foundationGrab = init_FoundationGrab;

        // Subsystems
        claw.init(init_GrabClaw, init_RotateClaw);
        tapeMeasure.init(init_AimTapeMeasure, init_DropTapeMeasure, init_TapeMeasure);
        lift.init(init_Lift);
        drive.Initialize(init_drive);
    }
}
