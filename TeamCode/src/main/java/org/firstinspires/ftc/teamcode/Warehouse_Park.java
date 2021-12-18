package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Disabled
@Autonomous(name="Warehouse_Park",group="idk")
public class Warehouse_Park extends LinearOpMode {

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
            robot.arm.setTargetPosition(400);
            robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.arm.setVelocity(400);
            sleep(400);

            drive(0.6,0.6,45, 45);
        }
    }
}
