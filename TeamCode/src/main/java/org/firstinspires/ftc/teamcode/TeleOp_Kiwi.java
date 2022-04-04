package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name="kiwi drive",group="stan twice")
//@Disabled
public class TeleOp_Kiwi extends OpMode {

    DcMotor lWheel;
    DcMotor rWheel;
    DcMotor mWheel;

    @Override
    public void init() {
        lWheel = hardwareMap.get(DcMotor.class, "lWheel");
        rWheel = hardwareMap.get(DcMotor.class, "rWheel");
        mWheel = hardwareMap.get(DcMotor.class, "mWheel");

        lWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        double lPower;
        double rPower;
        double mPower;

        double leftX = gamepad1.left_stick_x;
        double leftY = gamepad1.left_stick_y;
        double rightX = gamepad1.right_stick_x;

        lPower = -leftX;
        rPower = leftX / 2;
        mPower = leftX / 2;

        leftY = leftY * (Math.sqrt(3) / 2);
        rPower += -(leftY);
        mPower += leftY;

        lPower += rightX;
        rPower += rightX;
        mPower += rightX;

        if (Math.abs(lPower) > 1 || Math.abs(rPower) > 1 || Math.abs(mPower) > 1) {
            double maxPower = findAbsoluteMax(lPower, rPower, mPower);
            lPower /= maxPower;
            rPower /= maxPower;
            mPower /= maxPower;
        }

        lWheel.setPower(lPower);
        rWheel.setPower(rPower);
        mWheel.setPower(mPower);
        }

    double findAbsoluteMax(double p1, double p2, double p3) {
        double max;
        max = Math.max(Math.abs(p1), Math.abs(p2));
        max = Math.max(max, Math.abs(p3));
        return max;
    }
}
