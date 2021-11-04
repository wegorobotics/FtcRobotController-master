package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Disabled
@Autonomous(name="electric boogaloo, it's drive time 2",group="no longer testing lol")
public class AutonomousLinear extends LinearOpMode {
    HardwareRealbot robot = new HardwareRealbot();

    private ElapsedTime runtime = new ElapsedTime();

    static final double HD_CountsPerRev = 28;
    static final double driveGearReduction = 20.15293;
    static final double wheelCircumference = 90 * Math.PI;
    static final double countsPerMM = (HD_CountsPerRev * driveGearReduction) / wheelCircumference;
    static final double countsPerInch = countsPerMM * 25.4;

    public void drive(double leftPower, double rightPower, double leftInches, double rightInches) {
        int leftTarget;
        int rightTarget;

        if (opModeIsActive()) {
            leftTarget = robot.Left_DcMotor.getCurrentPosition() + (int)(leftInches * countsPerInch);
            rightTarget = robot.Right_DcMotor.getCurrentPosition() + (int)(rightInches * countsPerInch);

            robot.Left_DcMotor.setTargetPosition(leftTarget);
            robot.Right_DcMotor.setTargetPosition(rightTarget);

            robot.Left_DcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.Right_DcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.Left_DcMotor.setPower(leftPower);
            robot.Right_DcMotor.setPower(rightPower);

            while (opModeIsActive() && (robot.Left_DcMotor.isBusy() || robot.Right_DcMotor.isBusy())) {
                // wait for code to finish
            }

            robot.Left_DcMotor.setPower(0);
            robot.Right_DcMotor.setPower(0);
        }
    }

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.grab.setPosition(1); // close claw on init, keeping pre-load in
        waitForStart();

        if (opModeIsActive()) {

            drive(0.7,0.7,30,15); // adjust speeds/distance as needed

            runtime.reset(); //reset time after driving

            while (opModeIsActive() && runtime.seconds() <= 5 ) {
                robot.arm.setPosition(0.5); //raise arm
                drive(0.4, 0.4, 4, 4); // drive forward 4 inch with slow speed
            }

                        // touch sensor code, add back in if you want //
            //boolean btnState = robot.touchSens.getState();

            //if (!btnState) {
            //  robot.Left_DcMotor.setPower(0); //turn off motors and spin core hex for 3 sec if touch sensor pressed
            //  robot.Right_DcMotor.setPower(0);
            //  robot.Hex_Motor.setPower(.5);
            //  sleep(3000);
        }
    }
}
