package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class TapeMeasure {
    CRServo AimTapeMeasure;
    Servo DropTapeMeasure;
    DcMotor TapeMeasure;

    public void init(CRServo AimTapeMeasure, Servo DropTapeMeasure, DcMotor TapeMeasure){
        this.AimTapeMeasure = AimTapeMeasure;
        this.DropTapeMeasure = DropTapeMeasure;
        this.TapeMeasure = TapeMeasure;
    }

    public void manual(double extend, double pan, boolean drop){
        Extend(extend);
        Pan(pan);
        Drop(drop);
    }

    public void Extend(double var){
        TapeMeasure.setPower(var);
    }

    public void Pan(double var){
        AimTapeMeasure.setPower(var);
    }

    public void Drop(boolean drop){
        this.DropTapeMeasure.setPosition(drop ? 1 : 0);
    }
}
