package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;

public class Claw {

    CRServo GrabClaw, RotateClaw;

    int toNum(boolean in) {
        return (in?1:0);
    }

    public void init(CRServo grabClaw, CRServo rotateClaw){
        GrabClaw = grabClaw;
        RotateClaw = rotateClaw;
    }

    public void grabClaw(double power){

        GrabClaw.setPower(Range.clip(power, -.9, 0.8));
    }

    public void rotateClaw(double power){
        RotateClaw.setPower(power);
    }

    public void manual(double grabClaw, double rotateClaw){
        this.grabClaw(grabClaw);
        this.rotateClaw(rotateClaw);
    }


}
