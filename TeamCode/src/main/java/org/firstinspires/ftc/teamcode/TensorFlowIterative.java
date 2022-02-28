package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import java.util.List;

@Disabled
@TeleOp(name="Iterative TensorFlow",group="vision")
public class TensorFlowIterative extends OpMode {

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    private static final String modelAsset = "FreightFrenzy_BCDM.tflite";
    private static final String[] labels = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };

    private static final String VuforiaKey =  "AaGKGez/////AAABmbXgPjF/GEitgbUpZvj0aGpLtgpRx8ddqlDkZtTLfY" +
            "gb5qO4voHPolv6Z9GcjAaW+hzMXenjWPpumo3+d2Gpve/j3emwOoEbwBMdnc4IyJfc+tBT3yut8y" +
            "+WIcU7W/UZ7zmejqJCXzAYtDdyEU4lpwkDeLTIinJgpedkfghZj77d2uOhrkjXDH1VsKac5hMs3MNG" +
            "u4QaYOW6tlx+RsZ5NSh98UQ2fXKhSCc3Dadb3i4dNXtXpbsW/3Vgc58LsM9FVwnQEuGJ9" +
            "qexk+c2C2go6wm8lGwuVdx7fPEmCyuu8+NBzBLMiFFeUBWEYSrsZ10rA3enytUgfbHAnMT" +
            "mKVonKLL1Cn00vMrgauluhW5K+BYT";

    public void init() {
        initVuforia();
        initTFOD();

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(2.5, 16.0/9.0);
        }
    }

    public void loop() {
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

    public void initTFOD(){
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;

        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(modelAsset, labels);
    }
}
