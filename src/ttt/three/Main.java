package ttt.three;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by fengxuan on 2016/7/17.
 */
public class Main {
    public static class Node {
        int id;
        int[] parent;

        Node(int id, int[] parent) {
            this.id = id;
            this.parent = parent;
        }
    }

    public static void isStable(Node e) {
        int count = 0;

        for(int i = 0; i < e.parent.length; i++) {

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        System.out.println();
    }
}
