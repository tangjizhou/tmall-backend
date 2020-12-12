package net.mshome.twisted.tmall;

import nu.pattern.OpenCV;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class FindContours {

    private static final int MAX_THRESHOLD = 255;
    private Mat srcGray = new Mat();
    private JFrame frame;
    private JLabel imgSrcLabel;
    private JLabel imgContoursLabel;
    private int threshold = 100;
    private Random rng = new Random(12345);
    private static HashMap<String, Integer> retrModelMap = new HashMap();
    private int retrModel = Imgproc.RETR_EXTERNAL;

    static {
        retrModelMap.put("RETR_EXTERNAL", Imgproc.RETR_EXTERNAL);
        retrModelMap.put("RETR_TREE", Imgproc.RETR_TREE);
        retrModelMap.put("RETR_LIST", Imgproc.RETR_LIST);
        retrModelMap.put("RETR_CCOMP", Imgproc.RETR_CCOMP);
        retrModelMap.put("RETR_FLOODFILL", Imgproc.RETR_FLOODFILL);
    }

    private static HashMap<String, Integer> chainApproxMap = new HashMap();

    static {
        chainApproxMap.put("CHAIN_APPROX_NONE", Imgproc.CHAIN_APPROX_NONE);
        chainApproxMap.put("CHAIN_APPROX_SIMPLE", Imgproc.CHAIN_APPROX_SIMPLE);
        chainApproxMap.put("CHAIN_APPROX_TC89_L1", Imgproc.CHAIN_APPROX_TC89_L1);
        chainApproxMap.put("CHAIN_APPROX_TC89_KCOS", Imgproc.CHAIN_APPROX_TC89_KCOS);
        chainApproxMap.put("CV_POLY_APPROX_DP", Imgproc.CV_POLY_APPROX_DP);
    }

    private int chainApproxModel = Imgproc.CV_POLY_APPROX_DP;

    public FindContours(String[] args) throws FileNotFoundException {
        String filename = "/Users/tangjizhou/Pictures/test.png";
        Mat src = Imgcodecs.imread(filename);
        // Mat src = new Mat();
        // src.create(400, 500, CvType.CV_8UC1);
        Imgproc.resize(src, src, new Size(src.width(), src.height()));

        // for (int i = 0; i < 5; i++) {
        //     for (int j = 8 - i; j > i; j--) {
        //         int x1 = j * 20;
        //         int x2 = x1 - 10;
        //         int y1 = i * 30;
        //         int y2 = y1 + 20;
        //         Imgproc.rectangle(src, new Point(x1, y1), new Point(x2, y2), new Scalar(9), 3, Imgproc.LINE_8);
        //     }
        // }
        // ArrayList<MatOfPoint> contours = new ArrayList<>();
        // Mat hierarchy = new Mat();
        // Imgproc.findContours(src, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Imgproc.drawContours(src, points, 0, new Scalar(0), 2, Imgproc.LINE_8);

        if (src.empty()) {
            System.err.println("Cannot read image: " + filename);
            System.exit(0);
        }
        //灰度变换
        Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_BGR2BGRA);
        //滤波处理
        Imgproc.blur(srcGray, srcGray, new Size(3, 3));
        // Create and set up the window.
        frame = new JFrame("Finding contours in your image demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // // Set up the content pane.
        Image img = HighGui.toBufferedImage(src);
        addComponentsToPane(frame.getContentPane(), img);
        // // Use the content pane's default BorderLayout. No need for
        // setLayout(new BorderLayout());
        // Display the window.
        frame.pack();
        frame.setVisible(true);
        update();
    }

    public static void main(String[] args) {
        // Load the native OpenCV library
        OpenCV.loadLocally();
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                new FindContours(args);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void addComponentsToPane(Container pane, Image img) {
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.PAGE_AXIS));
        sliderPanel.add(new JLabel("Canny threshold: "));
        //构建滑动工具条
        JSlider slider = buildSlider();
        //构建检索模式下拉框
        JComboBox<String> retrModelBox = buildRetrModelBox();
        //构建链近似值模式下拉框
        JComboBox<String> chainApproxModelBox = buildChainApproxModelBox();
        sliderPanel.add(slider);
        sliderPanel.add(new JLabel("轮廓检索模式"));
        sliderPanel.add(retrModelBox);
        sliderPanel.add(new JLabel("链近似值模式"));
        sliderPanel.add(chainApproxModelBox);
        pane.add(sliderPanel, BorderLayout.PAGE_START);

        JPanel imgPanel = new JPanel();
        imgSrcLabel = new JLabel(new ImageIcon(img));
        imgPanel.add(imgSrcLabel);
        Mat blackImg = Mat.zeros(srcGray.size(), CvType.CV_8U);
        imgContoursLabel = new JLabel(new ImageIcon(HighGui.toBufferedImage(blackImg)));
        imgPanel.add(imgContoursLabel);
        pane.add(imgPanel, BorderLayout.CENTER);
    }

    private JComboBox<String> buildRetrModelBox() {
        JComboBox<String> retrModelBox = new JComboBox(retrModelMap.keySet().toArray());
        retrModelBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                retrModel = retrModelMap.get(cb.getSelectedItem());
                update();
            }
        });
        return retrModelBox;
    }

    private JComboBox<String> buildChainApproxModelBox() {
        JComboBox<String> retrModelBox = new JComboBox(chainApproxMap.keySet().toArray());
        retrModelBox.addActionListener(e -> {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            chainApproxModel = chainApproxMap.get(cb.getSelectedItem());
            update();
        });
        return retrModelBox;
    }

    private JSlider buildSlider() {
        JSlider slider = new JSlider(0, MAX_THRESHOLD, threshold);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            threshold = source.getValue();
            update();
        });
        return slider;
    }

    private void update() {
        Mat cannyOutput = new Mat();
        Imgproc.Canny(srcGray, cannyOutput, threshold, threshold * 2);
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(cannyOutput, contours, hierarchy, retrModel, chainApproxModel);

        Mat drawing = Mat.zeros(cannyOutput.size(), CvType.CV_8UC3);
        for (int i = 0; i < contours.size(); i++) {
            Scalar color = new Scalar(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
            Imgproc.drawContours(drawing, contours, i, color, 1, Imgproc.LINE_8, hierarchy, 0, new Point());
        }
        imgContoursLabel.setIcon(new ImageIcon(HighGui.toBufferedImage(drawing)));
        frame.repaint();
    }

}