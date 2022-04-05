package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name= "mecanum_to_lastwaltz", group="stan loona")
//@Disabled
public class MecanumLW extends OpMode {
    // motors
    DcMotor fl_Wheel;
    DcMotor bl_Wheel;
    DcMotor fr_Wheel;
    DcMotor br_Wheel;

    @Override
    public void init() {

        fl_Wheel = hardwareMap.get(DcMotor.class, "fl_wheel");
        bl_Wheel = hardwareMap.get(DcMotor.class, "bl_wheel");
        fr_Wheel = hardwareMap.get(DcMotor.class, "fr_wheel");
        br_Wheel = hardwareMap.get(DcMotor.class, "br_wheel");

        fr_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fl_Wheel.setDirection(DcMotor.Direction.FORWARD);
        br_Wheel.setDirection(DcMotor.Direction.REVERSE);
        bl_Wheel.setDirection(DcMotor.Direction.FORWARD);
    }

    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x; // rotation

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        fl_Wheel.setPower(frontLeftPower);
        bl_Wheel.setPower(backLeftPower);
        fr_Wheel.setPower(frontRightPower);
        br_Wheel.setPower(backRightPower);
    }
}
