package UnionFind;

/**
 * Quick Union
 * unionElements(p,q) ���Ӷ�Ϊ O(h)
 * isConnected(p,q) ���Ӷ�Ϊ O(h)
 * find ����Ϊ�ӵ����ϲ��Ҽ���,hΪ���ĸ߶�
 *
 * @author wangzhe
 */
public class UnionFind2 implements UF {

    private int[] parent;

    public UnionFind2(int size) {

        parent = new int[size];
        for (int i = 0; i < size; i++)
            parent[i] = i;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //Ѱ��Ԫ�� p����Ӧ�ĸ��ڵ�,����Ӧ�ļ��ϱ��
    //�������ϲ���,O(h)���Ӷ�,hΪ�ڵ��Ӧ���ĸ߶�
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;
        parent[pRoot] = qRoot;
    }
}
