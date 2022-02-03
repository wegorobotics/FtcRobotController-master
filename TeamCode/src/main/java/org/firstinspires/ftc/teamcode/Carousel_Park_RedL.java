package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Disabled
@Autonomous(name="Carousel_Park_RedL",group="Red")
public class Carousel_Park_RedL extends LinearOpMode {

    HardwareRealbot robot = new HardwareRealbot();

    private ElapsedTime runtime = new ElapsedTime();

    static final double HD_CountsPerRev = 28;
    static final double driveGearReduction = 20.15293;
    static final double wheelCircumference = 120 * Math.PI;
    static final double countsPerMM = (HD_CountsPerRev * driveGearReduction) / wheelCircumference;
    static final double countsPerInch = countsPerMM * 25.4;

    public void drive(double leftPower, double rightPower, double leftInches, double rightInches) {
        int leftTarget;
        int rightTarget;

        if (opModeIsActive()) {
            leftTarget = robot.bL_Wheel.getCurrentPosition() + (int)(leftInches * countsPerInch);
            rightTarget = robot.bR_Wheel.getCurrentPosition() + (int)(rightInches * countsPerInch);

            robot.bL_Wheel.setTargetPosition(leftTarget);
            robot.bR_Wheel.setTargetPosition(rightTarget);
            robot.fL_Wheel.setTargetPosition(leftTarget);
            robot.fR_Wheel.setTargetPosition(rightTarget);

            robot.bL_Wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.bR_Wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.fR_Wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.fL_Wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.bL_Wheel.setPower(leftPower);
            robot.bR_Wheel.setPower(rightPower);
            robot.fL_Wheel.setPower(leftPower);
            robot.fR_Wheel.setPower(rightPower);

            while (opModeIsActive() && (robot.bL_Wheel.isBusy() || robot.bR_Wheel.isBusy() || robot.fL_Wheel.isBusy() || robot.fR_Wheel.isBusy())) {
                // wait for code to finish
            }

            robot.bL_Wheel.setPower(0);
            robot.bR_Wheel.setPower(0);
            robot.fL_Wheel.setPower(0);
            robot.fR_Wheel.setPower(0);
        }
    }

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();

        if (opModeIsActive()) {
            robot.arm.setPower(0.5);
            sleep(1200);
            robot.arm.setPower(0);
            robot.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            drive(0.5,0.5,16.5, 16.5);
            drive(0.25,0.25,2.25, 2.25);
            sleep(500);
            drive(0.25,0.25,2, 2);
            sleep(200);
            drive(0.25,0.25,1.5,1.5);

            robot.spinWheel.setPower(0.15);
            sleep(3000);
            robot.spinWheel.setPower(0);

            robot.bL_Wheel.setDirection(DcMotor.Direction.REVERSE);
            robot.fL_Wheel.setDirection(DcMotor.Direction.REVERSE);

            drive(0.5,0.5,24, 24);

            robot.bR_Wheel.setDirection(DcMotor.Direction.FORWARD);
            robot.fR_Wheel.setDirection(DcMotor.Direction.FORWARD);
            drive(0.5,0.5,27, 27);
        }
    }
}
