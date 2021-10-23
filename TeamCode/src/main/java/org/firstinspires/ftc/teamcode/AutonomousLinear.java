package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

//@Disabled
@Autonomous(name="AutonomousTest",group="Testbot")
public class AutonomousLinear extends LinearOpMode{
    HardwareTestbot robot = new HardwareTestbot();

    @Override
    public void runOpMode(){

        double turnSpeed = .25;
        double driveSpeed = .25;

        robot.init(hardwareMap);

        telemetry.addData("Status","Ready to run"); // shows up on Driver hub
        telemetry.update();

        waitForStart();

        robot.Left_DcMotor.setPower(turnSpeed); //turning left
        robot.Right_DcMotor.setPower(-turnSpeed);
        sleep(2000);

        robot.Left_DcMotor.setPower(-driveSpeed); //going forward again
        robot.Right_DcMotor.setPower(-driveSpeed);

        telemetry.addData("Path","Complete");
        telemetry.update();
        sleep(1000);

        while (opModeIsActive()){
            boolean btnState = robot.touchSens.getState();

            if (!btnState){
                robot.Left_DcMotor.setPower(0); //turn off motors and spin core hex for 3 sec if
                robot.Right_DcMotor.setPower(0);
                robot.Hex_Motor.setPower(.5);
                sleep(3000);
            }
        }
    }
}