package com.yang.common;

import java.util.List;

/**
 * N叉树
 *
 * @author Daze
 * @date 2021-01-31
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
