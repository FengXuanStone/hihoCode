package trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by fengxuan on 2016/7/15.
 */
public class Main {
    public static class TrimNode {
        public char node;
        public int number = 0;
        public ArrayList<TrimNode> subTrimNode = new ArrayList<TrimNode>();
    }

    public static void insertTrim(TrimNode root, char[] str, int index) {
        if(index >= str.length) return;

        int i = 0;
        for(; i < root.subTrimNode.size(); i++) {
            TrimNode tmp = root.subTrimNode.get(i);
            boolean ss = tmp.node == str[index];
            if(tmp.node ==  str[index]) {
                tmp.number++;
                insertTrim(tmp, str, index + 1);
                return;
            }
        }

        if(i >= root.subTrimNode.size()) {
            TrimNode tmp = new TrimNode();
            tmp.node = str[index];
            tmp.number = 1;
            root.subTrimNode.add(tmp);
            insertTrim(tmp, str, index + 1);
        }
    }

    public static int searchNumber(TrimNode root, char[] str, int index) {
        if(index >= str.length) return 0;

        if(index == str.length - 1) {
            for(int i = 0; i < root.subTrimNode.size(); i++) {
                boolean ss = str[index] == root.subTrimNode.get(i).node;
                if(ss) {
                    return root.subTrimNode.get(i).number;
                }
            }
            return 0;
        } else {
            for(int i = 0; i < root.subTrimNode.size(); i++) {
                boolean ss = str[index] == root.subTrimNode.get(i).node;
                if(ss) {
                    return searchNumber(root.subTrimNode.get(i), str, index + 1);
                }
            }
        }
        return  0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int wordNumber = Integer.parseInt(br.readLine());
        String[] words = new String[wordNumber];
        for(int i = 0; i < wordNumber; i++) {
            words[i] = br.readLine();
        }

        //String[] words = new String[]{"babaab", "babbbaaaa","abba", "aaaaabaa","babaababb"};

        TrimNode root = new TrimNode();
        for(int i = 0; i < wordNumber; i++) {
            insertTrim(root, words[i].toCharArray(), 0);
        }

        int testNumber = Integer.parseInt(br.readLine());
        String[] tests = new String[testNumber];
        for(int i = 0; i < testNumber; i++) {
            tests[i] = br.readLine();
        }

        //String[] tests = new String[]{"babb", "baabaaa", "bab", "bb", "bbabbaab"};

        for(int i = 0; i < testNumber; i++) {
            int result = searchNumber(root, tests[i].toCharArray(), 0);
            System.out.println(result);
        }
    }
}
