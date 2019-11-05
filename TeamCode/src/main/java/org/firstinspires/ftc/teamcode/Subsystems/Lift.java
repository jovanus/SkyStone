package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Lift {
    class Level{
        int setting;
        String name;
        public Level(int setting, String name){
            this.setting = setting;
            this.name = name;
        }

        public int getSetting() {
            return setting;
        }

        public String getName() {
            return name;
        }
    }

    final List<Level> LEVELS = Arrays.asList(
            new Level(0, "Floor"),
            new Level(110, "First Block")
    );

    DcMotor Lift[];

    private ListIterator<Level> currentIterator = LEVELS.listIterator(0);

    public void init(DcMotor Lift[]){
        this.Lift = Lift;
        for (int i = 0; i < Lift.length; i++){
            Lift[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public void Manual(double var){
        for (int i = 0; i < Lift.length; i++) {
            Lift[i].setPower(var);
        }
    }
}
