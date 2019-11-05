package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;

public class Claw {

    CRServo GrabClaw, RotateClaw;

    int toNum(boolean in) {
        return (in?1:0);
    }

    public void init(CRServo grabClaw, CRServo rotateClaw){
        GrabClaw = grabClaw;
        RotateClaw = rotateClaw;
    }

    void grabClaw(double power){

        GrabClaw.setPower(power);
    }

    void rotateClaw(double power){
        RotateClaw.setPower(power);
    }


}
