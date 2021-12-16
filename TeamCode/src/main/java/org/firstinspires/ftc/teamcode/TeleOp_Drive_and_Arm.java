package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="it's drive time, electric boogaloo 2",group="idk")
//@Disabled
public class TeleOp_Drive_and_Arm extends OpMode {
    //DcMotor bL_Wheel;
    //DcMotor bR_Wheel;
    //DcMotor fL_Wheel;
    //DcMotor fR_Wheel;
    //DcMotor spinWheel;
    DcMotor arm;
    Servo grab;

    @Override
    public void init() {

        arm = hardwareMap.get(DcMotor.class, "bL_DcMotor");
        //bL_Wheel = hardwareMap.get(DcMotor.class, "bL_DcMotor");
        //bR_Wheel = hardwareMap.get(DcMotor.class, "bR_DcMotor");
        //fL_Wheel = hardwareMap.get(DcMotor.class, "fL_DcMotor");
        //fR_Wheel = hardwareMap.get(DcMotor.class, "fR_DcMotor");
        //arm = hardwareMap.get(DcMotor.class, "arm");
        grab = hardwareMap.get(Servo.class, "servoClaw");

        //bL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        //bR_Wheel.setDirection(DcMotor.Direction.REVERSE);
        //fL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        //fR_Wheel.setDirection(DcMotor.Direction.REVERSE);

        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void start() {
        grab.setPosition(0.40);
    }

    @Override
    public void loop() {

        boolean aBtn = gamepad1.a;
        boolean bBtn = gamepad1.b;
        boolean yBtn = gamepad1.y;

        double throttle = -gamepad1.left_stick_y * 0.6; //60% of speed when driving
        double turn = gamepad1.left_stick_x * 0.6;
        double leftSpeed = throttle + turn;
        double rightSpeed = throttle - turn;
        double grabPos = aBtn ? 1 : 0.40;

                //it's drive time//
        //bL_Wheel.setPower(leftSpeed);
        //bR_Wheel.setPower(rightSpeed);
        //fL_Wheel.setPower(leftSpeed);
        //fR_Wheel.setPower(rightSpeed);
        //

        //if (yBtn) {
           // spinWheel.setPower(-1);
       // } else {
        //    spinWheel.setPower(0);
        //}

        //if (bBtn) {
        //    spinWheel.setPower(1);
        //} else {
        //    spinWheel.setPower(0);
        //}

        grab.setPosition(grabPos);
        
        if (gamepad1.dpad_up) {
            arm.setDirection(DcMotor.Direction.REVERSE);
            arm.setPower(0.3);
        }

        if (gamepad1.dpad_down) {
            arm.setDirection(DcMotor.Direction.FORWARD);
            arm.setPower(0.3);
        }

        else {
            arm.setPower(0);
        }

        if (!gamepad1.dpad_up && !gamepad1.dpad_down) {
            double armPower = arm.getPower();

            if (armPower == 0) {
                arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
        }
    }

    @Override
    public void stop(){
    }
}