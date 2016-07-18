package level1.AAddB;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by fengxuan on 2016/7/18.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = br.readLine().split(" ");

        int result = Integer.parseInt(s1[0]) + Integer.parseInt(s1[1]);

        System.out.println(result);

    }
}
