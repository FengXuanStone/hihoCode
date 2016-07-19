package level1.Characterdeletion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by fengxuan on 2016/7/18.
 */
public class Main {
    public static int del(String c) {
        if(c.length() == 0) return 0;

        boolean flag = false;
        int count = 0;

        for(int i = 0; i < c.length(); i++) {
            int j = i + 1;
            while (j < c.length() && c.charAt(i) == c.charAt(j)) {
                j++;
            }

            if(i != j - 1) {
                count = count + j - i;
                flag = true;
                if(i == 0) {
                    c = c.substring(j, c.length());
                    i--;
                } else {
                    String s1 = c.substring(0, i - 1);
                    String s2 = c.substring(j, c.length());
                    c = c.substring(0, i) + c.substring(j, c.length());
                    i--;
                }

            }
        }

        if(flag) {
            return count + del(c);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s1 = Integer.parseInt(br.readLine());

        while (s1 > 0) {
            String s = br.readLine();
            int max = 0;
            for(int i = 0; i < s.length(); i++) {
                String news1 = s.substring(0, i) + "A" + s.substring(i, s.length());
                int tmp1 = del(news1);
                max = max > tmp1 ? max : tmp1;
                String news2 = s.substring(0, i) + "B" + s.substring(i, s.length());
                int tmp2 = del(news2);
                max = max > tmp2 ? max : tmp2;
                String news3 = s.substring(0, i) + "C" + s.substring(i, s.length());
                int tmp3 = del(news3);
                max = max > tmp3 ? max : tmp3;
            }

            System.out.println(max);

            s1--;
        }
    }
}
