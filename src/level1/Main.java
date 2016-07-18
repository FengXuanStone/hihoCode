package level1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by fengxuan on 2016/7/18.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s1 = Integer.parseInt(br.readLine());

        int max = Integer.MIN_VALUE;

        while (s1 > 0) {
            String s = br.readLine();
            for(int i = 0; i < s.length(); i ++) {
                String ss1 = s.substring(0, i) + "A" + s.substring(i, s.length() - 1);

            }

            System.out.println(max);

            s1--;
        }

    }
}
