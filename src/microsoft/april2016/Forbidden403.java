package microsoft.april2016;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by fengxuan on 2016/7/16.
 */
public class Forbidden403 {
    public static class TrimNode {
        boolean deny = false;
        int ruleNumber = -1;
        TrimNode left = null;
        TrimNode right = null;
    }

    public static class minNode{
        boolean deny = false;
        int ruleNumber = Integer.MAX_VALUE;
    }

    public static void insertTrim(TrimNode root, int ruleNum, Long num, int limit, int index, boolean deny) {
        if(limit == 0 && num == 0L) {
            root.deny = deny;
            root.ruleNumber = ruleNum;
        }

        if(index < limit) {
            Long tmp = num >>> (32 - index - 1);

            if(tmp % 2 == 0) {
                if(root.left != null) {
                    if(index == limit - 1 && root.left.ruleNumber == -1) {
                        TrimNode l1 = root.left;
                        l1.deny = deny;
                        l1.ruleNumber = ruleNum;
                        return;
                    } else {
                        insertTrim(root.left, ruleNum, num, limit, index + 1, deny);
                    }
                } else {
                    TrimNode l1 = new TrimNode();
                    root.left = l1;
                    if(index == limit - 1) {
                        l1.deny = deny;
                        l1.ruleNumber = ruleNum;
                        return;
                    } else {
                        insertTrim(root.left, ruleNum, num, limit, index + 1, deny);
                    }
                }
            } else {
                if(root.right != null) {
                    if(index == limit - 1 && root.right.ruleNumber == -1) {
                        TrimNode r1 = root.right;
                        r1.deny = deny;
                        r1.ruleNumber = ruleNum;
                        return;
                    }else {
                        insertTrim(root.right, ruleNum, num, limit, index + 1, deny);
                    }
                } else {
                    TrimNode r1 = new TrimNode();
                    root.right = r1;
                    if(index == limit - 1) {
                        r1.deny = deny;
                        r1.ruleNumber = ruleNum;
                        return;
                    } else {
                        insertTrim(root.right, ruleNum, num, limit, index + 1, deny);
                    }
                }
            }
        }
    }

    public static void search(TrimNode root, Long s, int index, minNode node) {
        if(root == null) return;

        if(index > 32) {
            return;
        }

        if(root.ruleNumber != -1) {
            if(root.ruleNumber <= node.ruleNumber) {
                node.ruleNumber = root.ruleNumber;
                node.deny = root.deny;
            }
        }

        Long tmp = s >>> (32 - index - 1);

        if(tmp %2 == 0) {
            search(root.left, s, index + 1, node);
        } else {
            search(root.right, s, index + 1, node);
        }
    }

    public static Long toLong(String s) {
        String[] t = s.split("\\.");
        Long result = 0L;
        result += (Long.parseLong(t[0]) << 24);
        result += (Long.parseLong(t[1]) << 16);
        result += (Long.parseLong(t[2]) << 8);
        result += Long.parseLong(t[3]);

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int rules = Integer.parseInt(firstLine[0]);
        int tests = Integer.parseInt(firstLine[1]);

        TrimNode root = new TrimNode();

        for(int i = 0; i < rules; i++) {
            String[] s = br.readLine().split(" ");
            String[] t = s[1].split("/");
            boolean deny = s[0].equals("deny");
            Long num = toLong(t[0]);

            if(t.length == 2) {
                insertTrim(root, i, num, Integer.parseInt(t[1]), 0, deny);
            } else {
                insertTrim(root, i, num, 32, 0, deny);
            }
        }

        for(int i = 0; i < tests; i++) {
            Long s = toLong(br.readLine());
            minNode node = new minNode();
            search(root, s, 0, node);

            if(node.deny) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }

    }
}
