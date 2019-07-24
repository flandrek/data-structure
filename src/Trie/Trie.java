package Trie;
/**
 * �ֵ���
 *
 * @author wangzhe
 */

import java.util.TreeMap;

public class Trie {

    private class Node {

        public boolean isWord;    // ����ĳ���ڵ�ʱ�Ƿ��Ѿ����һ������
        public TreeMap<Character, Node> next;  // ����һ���ڵ��ӳ��

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

    //����Trie�д洢�ĵ�������
    public int getSize() {
        return size;
    }

    //�� Trie�����һ���µĵ��� word
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        if (!cur.isWord) { //���֮ǰ�õ��ʲ�����
            cur.isWord = true;
            size++;
        }
    }

    //��ѯ���� word�Ƿ��� Trie��
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

    //��ѯ�� Trie���Ƿ��е����� prefixΪǰ׺
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
