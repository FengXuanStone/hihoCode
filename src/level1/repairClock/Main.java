package level1.repairClock;

import javafx.scene.control.RadioMenuItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by fengxuan on 2016/7/19.
 */
public class Main {

    public static int repair(int[] rang, int re) {
        int result = 0;

        for(int i = 0; i < rang.length; i++) {
            int tmpResult = rang[i];
            int left = i - 1;
            int right = i + 1;
            for(int j = 0; j < re; j++) {
                if(left == -1 && right == rang.length) {
                    break;
                } else if(left == -1) {
                    tmpResult = rang[right] + tmpResult + 1;
                    right++;
                } else if(right == rang.length) {
                    tmpResult = rang[left] + tmpResult + 1;
                    left--;
                } else {
                    if(rang[left] > rang[right]) {
                        tmpResult = rang[left] + tmpResult + 1;
                        left--;
                    } else {
                        tmpResult = rang[right] + tmpResult + 1;
                        right++;
                    }
                }
            }
            //update result
            result = result > tmpResult ? result : tmpResult;
        }

        return result;
    }

    public static int[] getPange(String[] numStr) {
        int[] result = new int[numStr.length + 1];

        int[] nums = new int[numStr.length];

        for(int i =0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(numStr[i]);
        }

        for(int i = 0; i < nums.length; i++) {
            if(i == 0) result[i] = nums[i] - 1;
            else result[i] = nums[i] - nums[i - 1] - 1;
        }

        result[nums.length] = 100 - nums[nums.length - 1];

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s1 = Integer.parseInt(br.readLine());

        while (s1 > 0) {
            String[] msg = br.readLine().split(" ");
            String[] repairStr = br.readLine().split(" ");
            int[] repairNum = getPange(repairStr);

            int result = repair(repairNum, Integer.parseInt(msg[1]));

            System.out.println(result);
            s1--;
        }
    }
}
