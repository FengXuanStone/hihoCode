package kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by fengxuan on 2016/7/16.
 */
public class Main {

    public static int[] getNext(char[] p) {
        int[] next = new int[p.length];
        next[0] = -1;
        int k = -1;
        int j = 0;

        while (j < p.length - 1) {
            //p[k]表示前缀，p[j]表示后缀
            if(k == -1 || p[j] == p[k]) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }

        return next;
    }

    public static int kmpSearch(char[] p, int[] next, char[] s) {
        int result = 0;
        int i = 0;
        int j = 0;

        while (i < s.length && j < p.length) {
            //如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
            if(j == -1 || s[i] == p[j]) {
                if(j == p.length - 1) {
                    result++;
                    j = next[j];
                } else {
                    i++;
                    j++;
                }
            } else {
                j = next[j];
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader  in = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(in.readLine());
        for(int i = 0; i < number; i++){
            char[] p = in.readLine().toCharArray();
            char[] s = in.readLine().toCharArray();
            int[] next = getNext(p);
            int result = kmpSearch(p, next, s);
            System.out.println(result);
        }

//        char[] p = "ADA".toCharArray();
//        char[] s = "ADADADA".toCharArray();
//        int[] next = getNext(p);
//        int result = kmpSearch(p, next, s);
//        System.out.println(result);

    }
}
