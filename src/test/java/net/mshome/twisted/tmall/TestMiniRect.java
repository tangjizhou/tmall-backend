package net.mshome.twisted.tmall;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.highgui.HighGui.waitKey;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/12/12
 */
public class TestMiniRect {

    public static void main(String[] args) {
        OpenCV.loadLocally();
        // MatOfPoint2f point1 = new MatOfPoint2f(new Point(1, 1), (new Point(200, 200)));
        // RotatedRect rotatedRect = Imgproc.minAreaRect(point1);
        // System.out.println(rotatedRect.center);
        // System.out.println(rotatedRect.size.width);
        // System.out.println(rotatedRect.size.height);
        // Point[] vertices = new Point[4];
        // rotatedRect.points(vertices);
        // for (int i = 0; i < 4; i++) {
        //     Imgproc.line(image, vertices[i], vertices[(i + 1) % 4], new Scalar(0, 255, 0));
        // }
        //
        // Rect rect = rotatedRect.boundingRect();
        // Imgproc.rectangle(image, rect, new Scalar(9));

        Mat image = Imgcodecs.imread("/Users/tangjizhou/Pictures/WechatIMG26.jpeg", 0);

        Mat mat = new Mat();
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(image, contours, mat, Imgproc.RETR_EXTERNAL, Imgproc.CV_POLY_APPROX_DP);
        Imgproc.drawContours(image, contours, -1, new Scalar(0), 3);
        HighGui.imshow("sss", image);
        waitKey(0);
    }

}
