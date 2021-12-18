package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="it's drive time",group="idk")
@Disabled
public class TeleOp_Drive_ServoArm extends OpMode {
    DcMotor bL_Wheel;
    DcMotor bR_Wheel;
    DcMotor fL_Wheel;
    DcMotor fR_Wheel;
    //Servo armServo;
    //DcMotor spinWheel;
    //Servo grab;

    static final double increment = 0.005;
    static final int maxPos = 1;
    static final int minPos = 0;

    @Override
    public void init() {
        bL_Wheel = hardwareMap.get(DcMotor.class, "bL_DcMotor");
        bR_Wheel = hardwareMap.get(DcMotor.class, "bR_DcMotor");
        fL_Wheel = hardwareMap.get(DcMotor.class, "fL_DcMotor");
        fR_Wheel = hardwareMap.get(DcMotor.class, "fR_DcMotor");

        //grab = hardwareMap.get(Servo.class, "servoClaw");

       // armServo = hardwareMap.get(Servo.class, "servoArm");
        //spinWheel = hardwareMap.get(DcMotor.class,"Hex_Motor");

        bL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        bR_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        fR_Wheel.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void start() {
       // grab.setPosition(0.40);
       // armServo.setPosition(0.5);
    }

    @Override
    public void loop() {
        boolean aBtn = gamepad1.a;
        boolean bBtn = gamepad1.b;
        boolean yBtn = gamepad1.y;

        //double wheelState = -0.7;
        //double wheelState2 = 0.7;

        double throttle = -gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;

        double leftSpeed = throttle + turn;
        double rightSpeed = throttle - turn;

        //double grabPos = aBtn ? 1 : 0.40;

        //grab.setPosition(grabPos);

        //it's drive time
        bL_Wheel.setPower(leftSpeed);
        bR_Wheel.setPower(rightSpeed);
        fL_Wheel.setPower(leftSpeed);
        fR_Wheel.setPower(rightSpeed);
        //

       // if (yBtn) {
       //     spinWheel.setPower(wheelState);
       // }
       // else {
       //     spinWheel.setPower(0);
       // }

       // if (bBtn) {
       //     spinWheel.setPower(wheelState2);
       // }

       // else {
       //     spinWheel.setPower(0);
       // }


       // if (gamepad1.dpad_up) {
       //     double newPos = armServo.getPosition();
       //     newPos += increment;
//
       //     if (newPos<=maxPos && newPos>=minPos) {
        //        armServo.setPosition(newPos);
        //    }
       // }

       // if (gamepad1.dpad_down) {
       //     double newPos = armServo.getPosition();
       //     newPos -= increment;
//
        //    if (newPos<=maxPos && newPos>=minPos) {
        //        armServo.setPosition(newPos);
        //    }
       // }
    }

    @Override
    public void stop() {
    }
}