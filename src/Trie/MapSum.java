package Trie;

import java.util.TreeMap;

/**
 * 实现一个 MapSum 类里的两个方法,insert 和 sum
 * 1.对于方法 insert,你将得到一对（字符串，整数）的键值对
 * 字符串表示键,整数表示值。如果键已经存在,那么原来的键值对将被替代成新的键值对
 * 2.对于方法 sum,你将得到一个表示前缀的字符串,你需要返回所有以该前缀开头的键的值的总和
 *
 * @author wangzhe
 */
public class MapSum {
    /**
     * Initialize your data structure here.
     */
    private class Node {

        public int value;
        public TreeMap<Character, Node> next; //到下一个节点的映射

        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String word, int val) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return 0;
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    //遍历 node所有的子树,并将其 value加和
    private int sum(Node node) {

        if (node.next.size() == 0) //递归结束条件,判断是否为叶子节点
            return node.value;    //该段逻辑也可以不写

        int res = node.value;
        for (char c : node.next.keySet())
            res += sum(node.next.get(c));
        return res;
    }
}
