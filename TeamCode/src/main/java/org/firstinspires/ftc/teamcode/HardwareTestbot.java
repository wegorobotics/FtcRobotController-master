package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Disabled
public class HardwareTestbot {

    public DcMotor Left_DcMotor;
    public DcMotor Right_DcMotor;
    public DcMotorEx Hex_Motor;
    public Servo grab;
    public Servo arm;
    public DigitalChannel touchSens;

    HardwareMap hwMap;
    private ElapsedTime period = new ElapsedTime();

    public HardwareTestbot(){
    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        Left_DcMotor = hwMap.get(DcMotor.class, "Left_DcMotor");
        Right_DcMotor = hwMap.get(DcMotor.class,"Right_DcMotor");
        Hex_Motor = hwMap.get(DcMotorEx.class,"Hex_Motor");
        grab = hwMap.get(Servo.class,"servoClaw");
        arm = hwMap.get(Servo.class,"servoArm");
        touchSens = hwMap.get(DigitalChannel.class,"touch_Sensor");

        touchSens.setMode(DigitalChannel.Mode.INPUT);

        Left_DcMotor.setDirection(DcMotor.Direction.REVERSE);

        Left_DcMotor.setPower(0);
        Right_DcMotor.setPower(0);

        Left_DcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Right_DcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hex_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Left_DcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Right_DcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Hex_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
}
