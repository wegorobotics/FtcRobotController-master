package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//@Disabled
@TeleOp (name="motortest",group="hula hoop")

public class motortest extends OpMode {
    DcMotor bL_Wheel;

    @Override
    public void init() {

        bL_Wheel = hardwareMap.get(DcMotor.class, "spinny_wheel");
    }

    @Override
    public void loop() {

        boolean aBtn = gamepad1.a;
        boolean bBtn = gamepad1.b;

        if (aBtn) {
            bL_Wheel.setPower(0.25);
        }
       else {
           bL_Wheel.setPower(0);
        }

        if (bBtn) {
            bL_Wheel.setPower(-0.25);
        }
        else {
            bL_Wheel.setPower(0);
        }

    }
}
