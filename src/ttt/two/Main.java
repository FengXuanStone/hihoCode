package ttt.two;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by fengxuan on 2016/7/17.
 */
public class Main {

    public static class hasTree {
        int row = 0;
        int col = 0;
        int high = 0;
        ArrayList<Integer> highs = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();

        hasTree(int x1, int y1, int x2, int y2, int high) {
            row = x2 - x1;
            col = y2 - y1;
            this.high = high;


        }




    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int high = Integer.parseInt(s[0]);
        int num = Integer.parseInt(s[1]);

        for(int i = 0; i < num; i++) {

        }

        System.out.println();
    }
}
