package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Disabled
@Autonomous(name="Carousel_Park_BlueR",group="Blue")
public class Carousel_Park_BlueR extends LinearOpMode {

    HardwareRealbot rb = new HardwareRealbot();

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
            leftTarget = rb.bL_Wheel.getCurrentPosition() + (int)(leftInches * countsPerInch);
            rightTarget = rb.bR_Wheel.getCurrentPosition() + (int)(rightInches * countsPerInch);

            rb.bL_Wheel.setTargetPosition(leftTarget);
            rb.bR_Wheel.setTargetPosition(rightTarget);
            rb.fL_Wheel.setTargetPosition(leftTarget);
            rb.fR_Wheel.setTargetPosition(rightTarget);

            rb.bL_Wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rb.bR_Wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rb.fR_Wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rb.fL_Wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            rb.bL_Wheel.setPower(leftPower);
            rb.bR_Wheel.setPower(rightPower);
            rb.fL_Wheel.setPower(leftPower);
            rb.fR_Wheel.setPower(rightPower);

            while (opModeIsActive() && (rb.bL_Wheel.isBusy() || rb.bR_Wheel.isBusy() || rb.fL_Wheel.isBusy() || rb.fR_Wheel.isBusy())) {
                // wait for code to finish
            }

            rb.bL_Wheel.setPower(0);
            rb.bR_Wheel.setPower(0);
            rb.fL_Wheel.setPower(0);
            rb.fR_Wheel.setPower(0);
        }
    }

    @Override
    public void runOpMode() {
        rb.init(hardwareMap);

        waitForStart();

        if (opModeIsActive()) {
            rb.arm.setPower(0.5);
            sleep(1200);
            rb.arm.setPower(0);
            rb.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            drive(0.5,0.5,16,16);
            sleep(250);

            rb.bR_Wheel.setDirection(DcMotor.Direction.FORWARD);
            rb.fR_Wheel.setDirection(DcMotor.Direction.FORWARD);

            drive(0.5,0.5,15,15);
            sleep(250);

            rb.bR_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.fR_Wheel.setDirection(DcMotor.Direction.REVERSE);

            drive(0.5,0.5,12.5,12.5);
            sleep(250);
            drive(0.25,0.25,3,3);
            sleep(250);
            drive(0.25,0.25,1.2,1.2);
            sleep(250);
            drive(0.25,0.25,1.1,1.1);
            sleep(500);

            rb.spinWheel.setPower(0.5);
            sleep(3000);
            rb.spinWheel.setPower(0);

            rb.bL_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.bR_Wheel.setDirection(DcMotor.Direction.FORWARD);
            rb.fL_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.fR_Wheel.setDirection(DcMotor.Direction.FORWARD);

            drive(0.5,0.5,42,42);

            rb.bR_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.fR_Wheel.setDirection(DcMotor.Direction.REVERSE);

            drive(0.5,0.5,10.5,10.5);

            rb.bL_Wheel.setDirection(DcMotor.Direction.FORWARD);
            rb.bR_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.fL_Wheel.setDirection(DcMotor.Direction.FORWARD);
            rb.fR_Wheel.setDirection(DcMotor.Direction.REVERSE);

            drive(0.5,0.5,30,30);
            sleep(500);
            drive(0.25,0.25,4,4);
            sleep(500);
            drive(0.25,0.25,4,4);
            sleep(500);
            drive(0.25,0.25,1,1);
        }
    }
}