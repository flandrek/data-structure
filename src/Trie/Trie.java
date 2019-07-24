package Trie;
/**
 * 字典树
 *
 * @author wangzhe
 */

import java.util.TreeMap;

public class Trie {

    private class Node {

        public boolean isWord;    // 到达某个节点时是否已经描绘一个单词
        public TreeMap<Character, Node> next;  // 到下一个节点的映射

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    //返回Trie中存储的单词数量
    public int getSize() {
        return size;
    }

    //向 Trie中添加一个新的单词 word
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        if (!cur.isWord) { //添加之前该单词不存在
            cur.isWord = true;
            size++;
        }
    }

    //查询单词 word是否在 Trie中
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    //查询在 Trie中是否有单词以 prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }

}
