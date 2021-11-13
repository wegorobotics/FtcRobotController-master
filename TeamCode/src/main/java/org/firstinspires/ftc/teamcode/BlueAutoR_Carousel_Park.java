package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Disabled
@Autonomous(name="BlueAutoR_Carousel_Park",group="Blue")
public class BlueAutoR_Carousel_Park extends LinearOpMode {
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
        robot.arm.setPosition(0.5); // set arm upwards

        waitForStart();

        if (opModeIsActive()) {

            robot.Left_DcMotor.setDirection(DcMotor.Direction.FORWARD);
            robot.Right_DcMotor.setDirection(DcMotor.Direction.REVERSE);

            drive(0.7,0.7,3, 3); // adjust speeds/distance as needed

            drive(0.5,0.5,1,32);

            robot.Left_DcMotor.setDirection(DcMotor.Direction.REVERSE);
            robot.Right_DcMotor.setDirection(DcMotor.Direction.FORWARD);

            drive(0.7,0.7,41.5,41.5);
            drive(0.5,0.5,1,16.4);

            robot.Hex_Motor.setPower(0.7);
            sleep(3000);

            robot.Left_DcMotor.setDirection(DcMotor.Direction.FORWARD);
            robot.Right_DcMotor.setDirection(DcMotor.Direction.REVERSE);

            drive(0.7,0.7,10,10);
            drive(0.5,0.5,21,1);
            drive(0.7,0.7,12.75,12.75);

        }
    }
}
