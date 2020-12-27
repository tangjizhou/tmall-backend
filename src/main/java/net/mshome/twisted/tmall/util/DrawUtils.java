package net.mshome.twisted.tmall.util;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 画图工具
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/12/13
 */
public final class DrawUtils {

    @Getter
    @Setter
    static class Point implements Cloneable {

        private long width;
        private long height;
        private final long x;
        private final long y;

        private Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public static Point of(long x, long y) {
            return new Point(x, y);
        }

        @Override
        public Point clone() {
            return Point.of(this.x, this.y);
        }

        public Point leftTop() {
            return Point.of(0, 0);
        }

        public Point rightTop() {
            return Point.of(0, 0);
        }

        public Point leftBottom() {
            return Point.of(0, 0);
        }

        public Point rightBottom() {
            return Point.of(0, 0);
        }

    }

    @Getter
    @Setter
    static class Edge {

        private Point from;
        private Point to;

        private Edge(Point from, Point to) {
            this.from = from;
            this.to = to;
        }

        public static Edge of(Point from, Point to) {
            return new Edge(from, to);
        }

    }

    @Getter
    @Setter
    private static class Contour {

        private List<Point> topContour;
        private List<Point> bottomContour;

        public Contour(List<Point> topContour, List<Point> bottomContour) {
            this.topContour = topContour;
            this.bottomContour = bottomContour;
        }

    }

    public static List<Point> draw(List<Point> points) {
        if (CollectionUtils.isEmpty(points)) {
            return Collections.emptyList();
        }

        Map<Long, List<Point>> xPointsMap = points.stream().collect(Collectors.groupingBy(Point::getX));
        Set<Map.Entry<Long, List<Point>>> xPointsEntries = xPointsMap.entrySet();
        Contour contour = calculateContour(xPointsEntries, Point::getY);
        List<Point> contourPoints = new ArrayList<>(contour.getBottomContour());
        contour.getTopContour().sort(Collections.reverseOrder());
        contourPoints.addAll(contour.getTopContour());
        return contourPoints;
    }

    private static Contour calculateContour(Set<Map.Entry<Long, List<Point>>> entries,
                                            Function<Point, Long> compareSupplier) {

        int index = 0;
        List<Point> topContour = new ArrayList<>();
        List<Point> bottomContour = new ArrayList<>();
        Contour contour = new Contour(topContour, bottomContour);
        for (Map.Entry<Long, List<Point>> entry : entries) {
            List<Point> points = entry.getValue();
            points.sort(Comparator.comparing(compareSupplier));
            Point from = points.get(0).clone();
            Point to = points.get(points.size() - 1).clone();
            Edge lastEdge = null;
            Edge edge = Edge.of(from, to);
            if (index == 0) {
                lastEdge = edge;
                topContour.add(from.leftTop());
                bottomContour.add(to.leftBottom());
            }

            calculateContourPoints(lastEdge, edge, contour);

            if (index == entries.size() - 1) {
                topContour.add(from.rightTop());
                bottomContour.add(to.rightBottom());
            }
            ++index;
        }

        return contour;
    }

    private static void calculateContourPoints(Edge lastEdge, Edge edge, Contour contour) {
        if (lastEdge == edge) {
            return;
        }


    }


    public static boolean hasOverlapping() {
        return false;

    }

    // 先求出所有不同的x，然后每个地方，找出最上方的和最下方的，然后 上方取最下，下方取最上。
    public List<Point> merge(Contour left2RightScan, Contour right2LeftScan) {
        List<Point> l1 = null;
        List<Point> l2 = null;

        // 所有的x
        List<Long> xs = null;


        xs.forEach(v -> {

        });

        l1.stream().map(Point::getX).sorted().collect(Collectors.toMap(Function.identity(), Function.identity(), (o1, o2) -> o1 > o2 ? o2 : o1));

        return Collections.emptyList();
    }

}
