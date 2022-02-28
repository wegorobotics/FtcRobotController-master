package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import java.util.List;

@TeleOp (name="willow can see now lol",group="stan loona")
//@Disabled
public class TeleOp_VisionDriveArm extends OpMode {

    static final String modelAsset = "FreightFrenzy_BCDM.tflite";
    static final String[] labels = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };

    static final String VuforiaKey =  "AaGKGez/////AAABmbXgPjF/GEitgbUpZvj0aGpLtgpRx8ddqlDkZtTLfY" +
            "gb5qO4voHPolv6Z9GcjAaW+hzMXenjWPpumo3+d2Gpve/j3emwOoEbwBMdnc4IyJfc+tBT3yut8y" +
            "+WIcU7W/UZ7zmejqJCXzAYtDdyEU4lpwkDeLTIinJgpedkfghZj77d2uOhrkjXDH1VsKac5hMs3MNG" +
            "u4QaYOW6tlx+RsZ5NSh98UQ2fXKhSCc3Dadb3i4dNXtXpbsW/3Vgc58LsM9FVwnQEuGJ9" +
            "qexk+c2C2go6wm8lGwuVdx7fPEmCyuu8+NBzBLMiFFeUBWEYSrsZ10rA3enytUgfbHAnMT" +
            "mKVonKLL1Cn00vMrgauluhW5K+BYT";

    RevBlinkinLedDriver rgbLights;
    VuforiaLocalizer vuforia;
    TFObjectDetector tfod;
    DcMotor bL_Wheel;
    DcMotor bR_Wheel;
    DcMotor fL_Wheel;
    DcMotor fR_Wheel;
    DcMotor spinWheel;
    DcMotorEx arm;
    Servo grab;

    @Override
    public void init() {

        initVuforia();
        initTFOD();

        rgbLights = hardwareMap.get(RevBlinkinLedDriver.class, "lights");
        bL_Wheel = hardwareMap.get(DcMotor.class, "bL_DcMotor");
        bR_Wheel = hardwareMap.get(DcMotor.class, "bR_DcMotor");
        fL_Wheel = hardwareMap.get(DcMotor.class, "fL_DcMotor");
        fR_Wheel = hardwareMap.get(DcMotor.class, "fR_DcMotor");
        spinWheel = hardwareMap.get(DcMotorEx.class, "spinny_wheel");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        grab = hardwareMap.get(Servo.class, "servoClaw");

        arm.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        bL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        bR_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fL_Wheel.setDirection(DcMotor.Direction.FORWARD);
        fR_Wheel.setDirection(DcMotor.Direction.REVERSE);

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(2.5, 16.0/9.0);
        }
    }

    @Override
    public void start() {
        grab.setPosition(0);
    }

    @Override
    public void loop() {

        boolean aBtn = gamepad1.a;
        boolean bBtn = gamepad1.b;
        boolean yBtn = gamepad1.y;

        boolean rightBumper = gamepad1.right_bumper;
        boolean leftBumper = gamepad1.left_bumper;

        double throttle = -gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;
        double leftSpeed = throttle + turn;
        double rightSpeed = throttle - turn;
        double grabPos = aBtn ? 0 : 0.15;

        grab.setPosition(grabPos); // servo claw position

        // it's drive time //
        bL_Wheel.setPower(leftSpeed);
        bR_Wheel.setPower(rightSpeed);
        fL_Wheel.setPower(leftSpeed);
        fR_Wheel.setPower(rightSpeed);

        if (yBtn) { // spinning wheel reverse
            spinWheel.setPower(-0.5);
        } else {
            spinWheel.setPower(0);
        }

        if (bBtn) { // spinning wheel forward
            spinWheel.setPower(0.5);
        } else {
            spinWheel.setPower(0);
        }

        if (rightBumper) {
            arm.setPower(0.65);
            rgbLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_WITH_GLITTER);
        }


        if (leftBumper) {
            arm.setPower(-0.65);
            rgbLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE);
        }

        if (!rightBumper && !leftBumper) {
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm.setPower(0);
        }

        if (tfod != null) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected ",updatedRecognitions.size());
                int i = 0;

                for (Recognition recognition : updatedRecognitions) {
                    telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                    telemetry.addData(String.format(" left, top (%d)",i), "%.03f , %.03f", recognition.getLeft(), recognition.getTop());
                    telemetry.addData(String.format(" right, bottom (%d)",i),"%.03f , %.03f",recognition.getRight(),recognition.getBottom());
                    i++;
                }
                telemetry.update();
            }
        }
    }

    public void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VuforiaKey;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    public void initTFOD() {

        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;

        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(modelAsset, labels);
    }
}
