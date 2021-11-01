package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="it's drive time",group="idk")
//@Disabled
public class TeleOp_Drive_and_Arm extends OpMode {
    DcMotor leftWheel;
    DcMotor rightWheel;
    Servo armServo;
    DcMotor spinWheel;
    Servo grab;
    //DigitalChannel touchSens;

    static final double increment = 0.005;

    @Override
    public void init() {
        leftWheel = hardwareMap.get(DcMotor.class, "Left_DcMotor");
        rightWheel = hardwareMap.get(DcMotor.class, "Right_DcMotor");
        armServo = hardwareMap.get(Servo.class, "servoArm");
        grab = hardwareMap.get(Servo.class, "servoClaw");
        spinWheel = hardwareMap.get(DcMotor.class,"Hex_Motor2");
        //touchSens = hardwareMap.get(DigitalChannel.class,"touch_Sensor");

        //touchSens.setMode(DigitalChannel.Mode.INPUT);
        leftWheel.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void start() {
        grab.setPosition(0.25);
        armServo.setPosition(0.5);
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
        boolean aBtn = gamepad1.a;
        boolean xBtn = gamepad1.x;

        double grabPos = aBtn ? 1:0.25;
        double wheelState = xBtn ? 0.7:0;

        grab.setPosition(grabPos);
        spinWheel.setPower(-wheelState);

        if (gamepad1.dpad_up) {
            double newPos = armServo.getPosition();
            newPos += increment;

            if (newPos<=1 && newPos>=0) {
                armServo.setPosition(newPos);
            }
        }

        if (gamepad1.dpad_down) {
            double newPos = armServo.getPosition();
            newPos -= increment;

            if (newPos<=1 && newPos>=0) {
                armServo.setPosition(newPos);
            }
        }
    }

    @Override
    public void stop() {
    }
}