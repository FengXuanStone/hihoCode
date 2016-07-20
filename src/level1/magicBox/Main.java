package level1.magicBox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by fengxuan on 2016/7/20.
 */
public class Main {

    public static int maxNumber(String seq, int[] vanishNum) {
        int result = 0;
        int[] num = {0, 0, 0};

        for(int i = 0; i < seq.length(); i++) {
            char s = seq.charAt(i);
            if(s == 'R') {
                num[0] = num[0] + 1;
            } else if(s == 'B') {
                num[1] = num[1] + 1;
            } else if(s == 'Y'){
                num[2] = num[2] + 1;
            }

            boolean isVanish = isVanish(num, vanishNum);

            if(isVanish) {
                int tmp = num[0] + num[1] + num[2];
                result = result > tmp ? result : tmp;

                num[0] = 0;
                num[1] = 0;
                num[2] = 0;
            }
        }
        int tmp = num[0] + num[1] + num[2];
        result = result > tmp ? result : tmp;

        return result;
    }

    public static boolean isVanish(int[] nums, int[] vanishNum) {
        int[] values = new int[3];
        for(int i = 0; i < 3; i++) {
            values[i] = Math.abs((nums[i] - nums[(i + 1) % 3]));
        }

        Arrays.sort(values);

        for(int i = 0; i < 3; i++) {
            if(values[i] != vanishNum[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = br.readLine().split(" ");
        int[] vanishNum = new int[3];
        for(int i = 0; i < 3; i++) {
            vanishNum[i] = Integer.parseInt(s1[i]);
        }
        Arrays.sort(vanishNum);

        String seq = br.readLine();

        int result = maxNumber(seq, vanishNum);

        System.out.println(result);

    }
}
