package UnionFind;

/**
 * Quick Union
 * unionElements(p,q) 复杂度为 O(h)
 * isConnected(p,q) 复杂度为 O(h)
 * find 方法为从低至上查找集合,h为树的高度
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

    //寻找元素 p所对应的根节点,即对应的集合编号
    //由下至上查找,O(h)复杂度,h为节点对应树的高度
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
