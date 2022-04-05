package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name="kiwi drive",group="stan twice")
//@Disabled
public class TeleOp_Kiwi extends OpMode {

    DcMotor aWheel;
    DcMotor bWheel;
    DcMotor cWheel;

    @Override
    public void init() {
        aWheel = hardwareMap.get(DcMotor.class, "aWheel");
        bWheel = hardwareMap.get(DcMotor.class, "bWheel");
        cWheel = hardwareMap.get(DcMotor.class, "cWheel");

        aWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        cWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        aWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        double aPower;
        double bPower;
        double cPower;

        double leftX = gamepad1.left_stick_x;
        double leftY = gamepad1.left_stick_y;
        double rightX = gamepad1.right_stick_x;

        aPower = -leftX;
        bPower = leftX / 2;
        cPower = leftX / 2;

        leftY = leftY * (Math.sqrt(3) / 2);
        bPower += -(leftY);
        cPower += leftY;

        aPower += rightX;
        bPower += rightX;
        cPower += rightX;

        if (Math.abs(aPower) > 1 || Math.abs(bPower) > 1 || Math.abs(cPower) > 1) {
            double maxPower = findAbsoluteMax(aPower, bPower, cPower);
            aPower /= maxPower;
            bPower /= maxPower;
            cPower /= maxPower;
        }

        aWheel.setPower(aPower);
        bWheel.setPower(bPower);
        cWheel.setPower(cPower);
        }

    double findAbsoluteMax(double p1, double p2, double p3) {
        double max;
        max = Math.max(Math.abs(p1), Math.abs(p2));
        max = Math.max(max, Math.abs(p3));
        return max;
    }
}
