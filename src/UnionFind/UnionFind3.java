package UnionFind;

/**
 * Quick Union
 * unionElements(p,q) 复杂度为 O(h)
 * isConnected(p,q) 复杂度为 O(h)
 * find 方法为从低至上查找集合,h为树的高度
 * 在原基础上进行优化,加入 sz表示以 i为根的集合中的元素个数,以减小合并后树的深度
 *
 * @author wangzhe
 */
public class UnionFind3 implements UF {

    private int[] parent;
    private int[] sz; //sz[i]表示以 i为根的集合中元素的个数

    public UnionFind3(int size) {

        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
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
        //根据两个元素所在树的元素个数不同判断合并方向
        //将元素个数少的集合合并到元素个数多的集合上
        if (sz[pRoot] <= sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else { //sz[pRoot] > sz[qRoot]
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }
}
