package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
public class HardwareRealbot {

    DcMotor bL_Wheel;
    DcMotor bR_Wheel;
    DcMotor fL_Wheel;
    DcMotor fR_Wheel;
    public DcMotorEx spinWheel;
    public DcMotorEx arm;
    public Servo grab;
    public DigitalChannel touchSens;
    //public DistanceSensor distanceSens;

    HardwareMap hwMap;

    public HardwareRealbot() {
    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        bL_Wheel = hwMap.get(DcMotor.class, "bL_DcMotor");
        bR_Wheel = hwMap.get(DcMotor.class, "bR_DcMotor");
        fL_Wheel = hwMap.get(DcMotor.class, "fL_DcMotor");
        fR_Wheel = hwMap.get(DcMotor.class, "fR_DcMotor");
        arm = hwMap.get(DcMotorEx.class, "arm");

        spinWheel = hwMap.get(DcMotorEx.class,"spinny_wheel");
        grab = hwMap.get(Servo.class,"servoClaw");
        //touchSens = hwMap.get(DigitalChannel.class,"touch_Sensor");
        //distanceSens = hwMap.get(DistanceSensor.class,"distanceSensor");

        //touchSens.setMode(DigitalChannel.Mode.INPUT);

        bL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        bR_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        fR_Wheel.setDirection(DcMotor.Direction.REVERSE);

        bL_Wheel.setPower(0);
        bR_Wheel.setPower(0);
        fL_Wheel.setPower(0);
        fR_Wheel.setPower(0);

        //Left_DcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Right_DcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Hex_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        bL_Wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bR_Wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fL_Wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fR_Wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        bL_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bR_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fL_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fR_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        spinWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
}
