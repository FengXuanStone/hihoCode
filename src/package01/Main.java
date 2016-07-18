package package01;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by fengxuan on 2016/7/16.
 */
public class Main {
    public static class package01 {
        int[] need;
        int[] value;

        package01(int num) {
            need = new int[num];
            value = new int[num];
        }

        public int getResult(int num, int maxP) {
            int[][] f = new int[num + 1][maxP + 1];

            for(int i = 0; i <= maxP; i++) {
                f[0][i] = 0;
            }

            for(int i = 1; i <= num; i++) {
                for(int j = 0; j <= maxP; j++) {
                    if(j < need[i - 1]) {
                        f[i][j] = f[i - 1][j];
                    } else {
                        int f1 = f[i - 1][j];
                        int f2 = f[i - 1][j - need[i - 1]] + value[i - 1];
                        f[i][j] = Math.max(f1, f2);
                    }
                }
            }

            return f[num][maxP];
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = br.readLine().split(" ");
        int num = Integer.parseInt(s1[0]);
        int maxP = Integer.parseInt(s1[1]);

        package01 p01 = new package01(num);

        for(int i = 0; i < num; i++) {
            String[] s2 = br.readLine().split(" ");
            p01.need[i] = Integer.parseInt(s2[0]);
            p01.value[i] = Integer.parseInt(s2[1]);
        }

        int result = p01.getResult(num, maxP);

        System.out.println(result);

    }
}
