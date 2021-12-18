package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="it's drive time, electric boogaloo 2",group="idk")
//@Disabled
public class TeleOp_Drive_and_Arm extends OpMode {
    DcMotor bL_Wheel;
    DcMotor bR_Wheel;
    DcMotor fL_Wheel;
    DcMotor fR_Wheel;
    DcMotor spinWheel;
    DcMotorEx arm;
    Servo grab;

    @Override
    public void init() {

        bL_Wheel = hardwareMap.get(DcMotor.class, "bL_DcMotor");
        bR_Wheel = hardwareMap.get(DcMotor.class, "bR_DcMotor");
        fL_Wheel = hardwareMap.get(DcMotor.class, "fL_DcMotor");
        fR_Wheel = hardwareMap.get(DcMotor.class, "fR_DcMotor");
        spinWheel = hardwareMap.get(DcMotorEx.class, "spinny_wheel");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        grab = hardwareMap.get(Servo.class, "servoClaw");

        arm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        bL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        bR_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        fR_Wheel.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void start() {

        grab.setPosition(0.6);
    }

    @Override
    public void loop() {

        boolean aBtn = gamepad1.a;
        boolean bBtn = gamepad1.b;
        boolean yBtn = gamepad1.y;

        boolean dpadUp = gamepad1.dpad_up;
        boolean dpadLeft = gamepad1.dpad_left;
        boolean dpadRight = gamepad1.dpad_right;
        boolean dpadDown = gamepad1.dpad_down;

        double throttle = -gamepad1.left_stick_y * 0.6; //60% of speed when driving
        double turn = gamepad1.left_stick_x * 0.6;
        double leftSpeed = throttle + turn;
        double rightSpeed = throttle - turn;
        double grabPos = aBtn ? 0.3 : 0.6;

        //encoder positions//
        int topLevel = 400;
        int midLevel = 500;
        int botLevel = 600;
        int pickupLevel = 0;

        grab.setPosition(grabPos); // servo claw position

        //it's drive time//
        bL_Wheel.setPower(leftSpeed);
        bR_Wheel.setPower(rightSpeed);
        fL_Wheel.setPower(leftSpeed);
        fR_Wheel.setPower(rightSpeed);

        if (yBtn) { // spinning wheel reverse
            spinWheel.setPower(-1);
        }
        else {
            spinWheel.setPower(0);
        }

        if (bBtn) { // spinning wheel forward
            spinWheel.setPower(1);
        }
        else {
            spinWheel.setPower(0);
        }

        if (dpadLeft) { // pick up position
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setTargetPosition(pickupLevel);

            int currentPosition = arm.getCurrentPosition();

            if (currentPosition > pickupLevel) {

                arm.setDirection(DcMotorEx.Direction.REVERSE);
            }

            arm.setVelocity(200);
        }

        if (dpadUp) { // top level position
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setTargetPosition(topLevel);

            int currentPosition = arm.getCurrentPosition();

            if (currentPosition > topLevel) {

                arm.setDirection(DcMotorEx.Direction.REVERSE);
            }

            arm.setVelocity(200);
        }

        if (dpadRight) { // middle level position
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setTargetPosition(midLevel);

            int currentPosition = arm.getCurrentPosition();

            if (currentPosition > midLevel) {
                arm.setDirection(DcMotorEx.Direction.REVERSE);
            }

            arm.setVelocity(200);
        }

        if (dpadDown) { // bottom level position
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setTargetPosition(botLevel);

            int currentPosition = arm.getCurrentPosition();

            if (currentPosition > botLevel) {

                arm.setDirection(DcMotorEx.Direction.REVERSE);
            }

            arm.setVelocity(200);
        }

        else {

            arm.setPower(0);
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    @Override
    public void stop() {
    }
}