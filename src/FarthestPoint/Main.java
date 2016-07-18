package FarthestPoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by fengxuan on 2016/7/17.
 */
public class Main {
    public static class maxPoint{
        int x = Integer.MIN_VALUE;
        int y = Integer.MIN_VALUE;
        double max = Double.MIN_VALUE;
    }

    public static void getResult(double x, double y, double r, maxPoint point) {
        int right = (int) Math.floor(x + r);
        int left = (int) Math.ceil(x - r);

        for(int i = right; i >= left; i--) {
            double j = y + Math.sqrt(r * r - (i - x) * (i - x));
            int y1 = (int) Math.floor(j);
            double sum = (i - x) * (i - x) + (y1 - y) * (y1 - y);
            if(sum > point.max) {
                point.x = i;
                point.y = y1;
                point.max = sum;
            }

            double j1 = y - Math.sqrt(r * r - (i - x) * (i - x));
            int y2 = (int) Math.ceil(j1);
            double sum1 = (i - x) * (i - x) + (y2 - y) * (y2 - y);
            if(sum1 > point.max) {
                point.x = i;
                point.y = y2;
                point.max = sum1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");

        maxPoint x = new maxPoint();

        getResult(Double.parseDouble(firstLine[0]),Double.parseDouble(firstLine[1]),
                Double.parseDouble(firstLine[2]), x);

        System.out.println(x.x + " " + x.y);
    }
}
