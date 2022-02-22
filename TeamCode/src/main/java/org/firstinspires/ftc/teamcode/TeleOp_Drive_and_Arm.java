package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="it's drive time, electric boogaloo 2",group="shy shy shy")
@Disabled
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

        //grab.setPosition(0);
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
        //double grabPos = aBtn ? 0.1 : 0;

        //encoder positions//
        int topLevel = 900;
        int midLevel = 1100;
        int botLevel = 1369;
        int pickupLevel = 0;

        int speed = 400;

        //grab.setPosition(grabPos); // servo claw position

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
            arm.setTargetPosition(pickupLevel);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            int currentPosition = arm.getCurrentPosition();

            if (currentPosition > pickupLevel) {

                arm.setDirection(DcMotorEx.Direction.REVERSE);
                arm.setVelocity(speed);
            }
            else {
                arm.setDirection(DcMotorEx.Direction.FORWARD);
                arm.setVelocity(speed);
            }
        }

        if (dpadUp) { // top level position
            arm.setTargetPosition(topLevel);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            int currentPosition = arm.getCurrentPosition();

            if (currentPosition > topLevel) {

                arm.setDirection(DcMotorEx.Direction.REVERSE);
            }
            else {
                arm.setDirection(DcMotorEx.Direction.FORWARD);
                arm.setVelocity(speed);
            }
        }

        if (dpadRight) { // middle level position
            arm.setTargetPosition(midLevel);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            int currentPosition = arm.getCurrentPosition();

            if (currentPosition > midLevel) {
                arm.setDirection(DcMotorEx.Direction.REVERSE);
                arm.setVelocity(speed);
            }
            else {
                arm.setDirection(DcMotorEx.Direction.FORWARD);
                arm.setVelocity(speed);
            }
        }

        if (dpadDown) { // bottom level position
            arm.setTargetPosition(botLevel);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            int currentPosition = arm.getCurrentPosition();

            if (currentPosition > botLevel) {

                arm.setDirection(DcMotorEx.Direction.REVERSE);
                arm.setVelocity(speed);
            }
            else {
                arm.setDirection(DcMotorEx.Direction.FORWARD);
                arm.setVelocity(speed);
            }
        }

    }

    @Override
    public void stop() {
    }
}