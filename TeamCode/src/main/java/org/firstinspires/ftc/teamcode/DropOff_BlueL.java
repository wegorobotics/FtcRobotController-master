package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Disabled
@Autonomous(name="DropOff_BlueL",group="Blue")
public class DropOff_BlueL extends LinearOpMode {

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
        rb.grab.setPosition(0.15);

        waitForStart();

        if (opModeIsActive()) {
            rb.bL_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.bR_Wheel.setDirection(DcMotor.Direction.FORWARD);
            rb.fL_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.fR_Wheel.setDirection(DcMotor.Direction.FORWARD);

            rb.grab.setPosition(0);
            sleep(1000);
            drive(0.6,0.6,29, 29);
            sleep(500);
            drive(0.3,0.3,3, 3);
            sleep(500);
            drive(0.25,0.25,2, 2);
            sleep(500);
            drive(0.25,0.25,1.5, 1.5);
            sleep(1000);

            rb.arm.setPower(0.35);
            sleep(3500);
            rb.arm.setPower(0);
            rb.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rb.grab.setPosition(0.15);
            sleep(2000);

            rb.bL_Wheel.setDirection(DcMotor.Direction.FORWARD);
            rb.bR_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.fL_Wheel.setDirection(DcMotor.Direction.FORWARD);
            rb.fR_Wheel.setDirection(DcMotor.Direction.REVERSE);

            drive(0.25,0.25,10, 10);
            sleep(1000);

            rb.bL_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.fL_Wheel.setDirection(DcMotor.Direction.REVERSE);

            drive(0.25,0.25,30, 30);

            rb.bL_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.bR_Wheel.setDirection(DcMotor.Direction.FORWARD);
            rb.fL_Wheel.setDirection(DcMotor.Direction.REVERSE);
            rb.fR_Wheel.setDirection(DcMotor.Direction.FORWARD);

            drive(0.6,0.6,60, 60);
        }
    }
}
