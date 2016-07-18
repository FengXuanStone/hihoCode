package microsoft.april2016;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by fengxuan on 2016/7/16.
 */
public class FontSize {
    public static int size(int n, int p, int w, int h, int[] ai) {
        int left = 1;
        int right = Math.min(w, h);
        int result = 0;

        while(left <= right) {
            int mid = (left + right) / 2;
            int col = w / mid;
            int row = h / mid;
            int rowCount = 0;
            for(int i = 0; i < n; i++) {
                rowCount += ai[i]%col == 0 ? ai[i]/col:ai[i]/col + 1;
            }
            int page = rowCount%row == 0 ? rowCount/row:rowCount/row + 1;

            if(page > p) {
                right = mid - 1;
            } else {
                result = mid;
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(in.readLine());
        for(int i = 0; i < number; i++) {
            String[] lin1 = in.readLine().split(" ");
            String[] lin2 = in.readLine().split(" ");
            int[] ai = new int[lin2.length];

            for(int j = 0; j < ai.length; j++) {
                ai[j] = Integer.parseInt(lin2[j]);
            }

            int result = size(Integer.parseInt(lin1[0]),Integer.parseInt(lin1[1]),
                    Integer.parseInt(lin1[2]),Integer.parseInt(lin1[3]), ai);

            System.out.println(result);
        }

//        String[] lin1 = {"1","10","4","3"};
//        String[] lin2 = {"10"};
//        int[] ai = new int[lin2.length];
//
//        for(int j = 0; j < ai.length; j++) {
//            ai[j] = Integer.parseInt(lin2[j]);
//        }
//
//        int result = size(Integer.parseInt(lin1[0]),Integer.parseInt(lin1[1]),
//                Integer.parseInt(lin1[2]),Integer.parseInt(lin1[3]), ai);
//
//        System.out.println(result);
    }

}
