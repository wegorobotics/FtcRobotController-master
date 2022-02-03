package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="willow girl boss, slay",group="Drive")
//@Disabled
public class TeleOp_Drive_and_Arm_2 extends OpMode {
    DcMotor bL_Wheel;
    DcMotor bR_Wheel;
    DcMotor fL_Wheel;
    DcMotor fR_Wheel;
    DcMotor spinWheel;
    DigitalChannel btnSens;
    DcMotorEx arm;
    Servo grab;

    @Override
    public void init() {

        bL_Wheel = hardwareMap.get(DcMotor.class, "bL_DcMotor");
        bR_Wheel = hardwareMap.get(DcMotor.class, "bR_DcMotor");
        fL_Wheel = hardwareMap.get(DcMotor.class, "fL_DcMotor");
        fR_Wheel = hardwareMap.get(DcMotor.class, "fR_DcMotor");
        spinWheel = hardwareMap.get(DcMotorEx.class, "spinny_wheel");
        btnSens = hardwareMap.get(DigitalChannel.class, "btnSens");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        grab = hardwareMap.get(Servo.class, "servoClaw");

        arm.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        bL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        bR_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        fR_Wheel.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void start() {
        grab.setPosition(0);
    }

    @Override
    public void loop() {

        boolean aBtn = gamepad1.a;
        boolean bBtn = gamepad1.b;
        boolean yBtn = gamepad1.y;
        boolean btnState = btnSens.getState();

        boolean rightBumper = gamepad1.right_bumper;
        boolean leftBumper = gamepad1.left_bumper;

        double throttle = -gamepad1.left_stick_y * 0.6; // 60% of speed when driving
        double turn = gamepad1.left_stick_x * 0.6;
        double leftSpeed = throttle + turn;
        double rightSpeed = throttle - turn;
        double grabPos = aBtn ? 0 : 0.15;

        grab.setPosition(grabPos); // servo claw position

        // it's drive time //
        bL_Wheel.setPower(leftSpeed);
        bR_Wheel.setPower(rightSpeed);
        fL_Wheel.setPower(leftSpeed);
        fR_Wheel.setPower(rightSpeed);

        if (yBtn) { // spinning wheel reverse
            spinWheel.setPower(-0.28);
        }

        else {
            spinWheel.setPower(0);
        }

        if (bBtn) { // spinning wheel forward
            spinWheel.setPower(0.28);
        }

        else {
            spinWheel.setPower(0);
        }

        if (rightBumper) {
            arm.setPower(0.8);
        }

        if (!btnState) {
            if (leftBumper) {
                arm.setPower(0);
            }
        }

        else {
            if (leftBumper) {
                arm.setPower(-0.8);
            }
        }

        if (!rightBumper && !leftBumper) {
            arm.setPower(0);
        }
    }

    @Override
    public void stop() {
    }
}

