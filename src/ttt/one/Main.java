package ttt.one;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengxuan on 2016/7/17.
 */
public class Main {

    public static boolean check(ArrayList<String> rules, Map<String, Integer> map) {
        for (int i = 0; i < rules.size(); i++) {
            char[] ss = rules.get(i).toCharArray();
            int index = 0;

            char ch;
            int number, prevNumber = 0;

            if (Character.isUpperCase(ss[index])) {
                prevNumber = map.get(String.valueOf(ss[index]));
                index ++;
            } else {
                do {
                    prevNumber = prevNumber * 10 + ss[index] - '0';
                    index++;
                } while(Character.isDigit(ss[index]));
            }
            while (index < ss.length) {
                int flag = 0; // 0 for <, 1 for <=
                number = 0;
                index++;
                ch = ss[index]; // '<'

                if (ss[index] == '=') {
                    flag = 1;
                    index ++;
                }
                if (Character.isUpperCase(ss[index])) {
                    number = map.get(String.valueOf(ss[index]));
                    index++;
                } else {
                    do {
                        number = number * 10 + ss[index] - '0';
                        index++;
                    } while (index < ss.length && Character.isDigit(ss[index]));
                }

                if (flag == 0) {
                    if (prevNumber >= number)  return false;
                }

                if (flag == 1) {
                    if (prevNumber > number) return false;
                }

                prevNumber = number;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rules = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<String, Integer>();

        ArrayList<String> rule = new ArrayList<>();

        for (int i = 0; i < rules; i++) {
            String s = br.readLine();
            rule.add(s);
            String[] s1 = s.split("<");
            for (int j = 0; j < s1.length; j++) {
                if (s1[j].charAt(0) == '=') {
                    char c = s1[j].charAt(1);
                    if (Character.isUpperCase(c)) {
                        String ss = s1[j].substring(1);
                        map.put(ss, 0);
                    }
                } else {
                    char c = s1[j].charAt(0);
                    if (Character.isUpperCase(c)) {
                        map.put(s1[j], 0);
                    }
                }
            }
        }

        int count = map.size();

        int tests = Integer.parseInt(br.readLine());

        for (int i = 0; i < tests; i++) {
            for (int j = 0; j < count; j++) {
                String[] s1 = br.readLine().split(" ");
                map.put(s1[0], Integer.parseInt(s1[1]));
            }

            boolean flag = check(rule, map);

            if (flag) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
