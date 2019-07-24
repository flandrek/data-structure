package Trie;

import java.util.TreeMap;

/**
 * ʵ��һ�� MapSum �������������,insert �� sum
 * 1.���ڷ��� insert,�㽫�õ�һ�ԣ��ַ������������ļ�ֵ��
 * �ַ�����ʾ��,������ʾֵ��������Ѿ�����,��ôԭ���ļ�ֵ�Խ���������µļ�ֵ��
 * 2.���ڷ��� sum,�㽫�õ�һ����ʾǰ׺���ַ���,����Ҫ���������Ը�ǰ׺��ͷ�ļ���ֵ���ܺ�
 *
 * @author wangzhe
 */
public class MapSum {
    /**
     * Initialize your data structure here.
     */
    private class Node {

        public int value;
        public TreeMap<Character, Node> next; //����һ���ڵ��ӳ��

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

    //���� node���е�����,������ value�Ӻ�
    private int sum(Node node) {

        if (node.next.size() == 0) //�ݹ��������,�ж��Ƿ�ΪҶ�ӽڵ�
            return node.value;    //�ö��߼�Ҳ���Բ�д

        int res = node.value;
        for (char c : node.next.keySet())
            res += sum(node.next.get(c));
        return res;
    }
}
