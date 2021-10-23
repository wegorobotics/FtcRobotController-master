package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="Simple Drive 2 Electric Boogaloo",group="I guess this is no longer test code")
//@Disabled
public class NotSoSimpleDrive extends OpMode {
    DcMotor leftWheel;
    DcMotor rightWheel;
    DcMotor hexMotor;
    Servo grab;
    //DigitalChannel touchSens;

    @Override
    public void init(){
        leftWheel = hardwareMap.get(DcMotor.class,"Left_DcMotor");
        rightWheel = hardwareMap.get(DcMotor.class,"Right_DcMotor");
        hexMotor = hardwareMap.get(DcMotor.class,"Hex_Motor");
        grab = hardwareMap.get(Servo.class,"servoClaw");
        //touchSens = hardwareMap.get(DigitalChannel.class,"touch_Sensor");

        //touchSens.setMode(DigitalChannel.Mode.INPUT);
        leftWheel.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void start() {
        grab.setPosition(0.5);
    }

    @Override
    public void loop() {
        double throttle = -gamepad1.left_stick_y; // don't change this part, this is the actual drive code
        double turn = gamepad1.left_stick_x;
        double leftSpeed = throttle - turn;
        double rightSpeed = throttle + turn;

        leftWheel.setPower(leftSpeed);
        rightWheel.setPower(rightSpeed); // anything under here is just experimental

        //boolean buttonState = touchSens.getState();

        if (gamepad1.a) {
            hexMotor.setPower(0.7);
        }
        else {
            hexMotor.setPower(0);
        }
    }
    @Override
    public void stop () {
    }
}