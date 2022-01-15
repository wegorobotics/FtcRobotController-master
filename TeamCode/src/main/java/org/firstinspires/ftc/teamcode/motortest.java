package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//@Disabled
@TeleOp (name="motortest",group="idk")

public class motortest extends OpMode {
    DcMotor bL_Wheel;

    @Override
    public void init() {

        bL_Wheel = hardwareMap.get(DcMotor.class, "bL_DcMotor");
    }

    @Override
    public void loop() {

        boolean aBtn = gamepad1.a;
        boolean bBtn = gamepad1.b;
        boolean xBtn = gamepad1.x;
        boolean yBtn = gamepad1.y;

        if (aBtn) {
            bL_Wheel.setPower(0.7);
        }
        if (bBtn) {
            bL_Wheel.setPower(-0.7);
        }

        if (xBtn) {
            bL_Wheel.setPower(1);
        }
        if (yBtn){
            bL_Wheel.setPower(-1);
        }

        if (!xBtn && !yBtn && !aBtn && bBtn) {
            bL_Wheel.setPower(0);
            bL_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
}
