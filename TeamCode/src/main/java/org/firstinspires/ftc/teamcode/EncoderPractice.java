package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//@Disabled
@TeleOp(name="Encoder Practice",group="Practice")

public class EncoderPractice extends OpMode {
    HardwareTestbot robot = new HardwareTestbot();

    @Override
    public void init() {
        robot.init(hardwareMap);

        robot.Hex_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Status", "Ready to run");
    }

    @Override
    public void loop(){

        boolean buttonState = robot.touchSens.getState();
        robot.Hex_Motor.setTargetPosition(1000);
        robot.Hex_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (!buttonState) {
            robot.Hex_Motor.setVelocity(200);

            telemetry.addData("Button state is", false);
            telemetry.addData("Velocity", robot.Hex_Motor.getVelocity());
            telemetry.addData("Position", robot.Hex_Motor.getCurrentPosition());
            telemetry.addData("is at target", !robot.Hex_Motor.isBusy());
            }
        }
    }
