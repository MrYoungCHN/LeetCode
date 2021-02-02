package com.yang.problem1733;

import java.util.HashMap;
import java.util.Map;

/**
 * 1733.需要教语言的最少人数
 * 在一个由 m 个用户组成的社交网络里，我们获取到一些用户之间的好友关系。两个用户之间可以相互沟通的条件是他们都掌握同一门语言。
 * 给你一个整数 n ，数组 languages 和数组 friendships ，它们的含义如下：
 * 总共有 n 种语言，编号从 1 到 n 。
 * languages[i] 是第 i 位用户掌握的语言集合。
 * friendships[i] = [ui, vi] 表示 ui 和 vi 为好友关系。
 * 你可以选择 一门 语言并教会一些用户，使得所有好友之间都可以相互沟通。请返回你 最少 需要教会多少名用户。
 * 请注意，好友关系没有传递性，也就是说如果 x 和 y 是好友，且 y 和 z 是好友， x 和 z 不一定是好友。
 *
 * 示例 1：
 * 输入：n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
 * 输出：1
 * 解释：你可以选择教用户 1 第二门语言，也可以选择教用户 2 第一门语言。
 *
 * 示例 2：
 * 输入：n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
 * 输出：2
 * 解释：教用户 1 和用户 3 第三门语言，需要教 2 名用户。
 *
 * @author Daze
 * @date 2021-02-01
 */
public class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Map<Integer, Integer> mostLanguage = new HashMap<>();
        Map<Integer, Integer> notConnected = new HashMap<>();

        // 记录没有共同语言的好友 有哪些人
        for (int[] friendship : friendships) {
            if (!hasCommon(languages, friendship[0], friendship[1])) {
                notConnected.put(friendship[0], (notConnected.getOrDefault(friendship[0], 0) + 1));
                notConnected.put(friendship[1], (notConnected.getOrDefault(friendship[1], 0) + 1));
            }
        }
        // 统计他们会的语言
        for (Map.Entry<Integer, Integer> entry : notConnected.entrySet()) {
            for (int language : languages[entry.getKey() - 1]) {
                mostLanguage.put(language, (mostLanguage.getOrDefault(language, 0) + 1));
            }
        }

        int most = 0;
        // 找到他们里最通用的语言
        for (Map.Entry<Integer, Integer> entry : mostLanguage.entrySet()) {
            most = Math.max(most, entry.getKey());
        }
        int nodes = notConnected.size();

        // 需要再学习该语言的人数 即他们的总人数-会该语言的人数
        return nodes - most;
    }

    /**
     * 判断两个人是否有共同语言
     *
     * @param languages 语言集合
     * @param i 第i个用户
     * @param j 第j个用户
     * @return 是否有共同语言
     */
    private boolean hasCommon(int[][] languages, int i, int j) {
        int[] lv1 = languages[i - 1];
        int[] lv2 = languages[j - 1];

        for (int l1 : lv1) {
            for (int l2 : lv2) {
                if (l1 == l2) {
                    return true;
                }
            }
        }
        return false;
    }
}
