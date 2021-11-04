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

    public DcMotorEx Left_DcMotor;
    public DcMotorEx Right_DcMotor;
    public DcMotorEx Hex_Motor;
    public Servo grab;
    public Servo arm;
    public DigitalChannel touchSens;
    public DistanceSensor distanceSens;

    HardwareMap hwMap;

    public HardwareRealbot() {
    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        Left_DcMotor = hwMap.get(DcMotorEx.class, "Left_DcMotor");
        Right_DcMotor = hwMap.get(DcMotorEx.class,"Right_DcMotor");
        Hex_Motor = hwMap.get(DcMotorEx.class,"Hex_Motor");
        grab = hwMap.get(Servo.class,"servoClaw");
        arm = hwMap.get(Servo.class,"servoArm");
        touchSens = hwMap.get(DigitalChannel.class,"touch_Sensor");
        distanceSens = hwMap.get(DistanceSensor.class,"distanceSensor");

        touchSens.setMode(DigitalChannel.Mode.INPUT);

        Right_DcMotor.setDirection(DcMotor.Direction.REVERSE);

        Left_DcMotor.setPower(0);
        Right_DcMotor.setPower(0);

        //Left_DcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Right_DcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Hex_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Left_DcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Right_DcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Hex_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Left_DcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Right_DcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hex_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
