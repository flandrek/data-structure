package UnionFind;

/**
 * Quick Union
 * unionElements(p,q) 复杂度为 O(h)
 * isConnected(p,q) 复杂度为 O(h)
 * find 方法为从低至上查找集合,h为树的高度
 * 在原基础上进行优化,加入 rank表示以 i为根的树的深度,以减小合并后树的深度
 * 将 5的路径压缩操作改为递归方法,所有的节点都指向根节点
 *
 * @author wangzhe
 */
public class UnionFind6 implements UF {

    private int[] parent;
    private int[] rank; //rank[i]表示以 i为根的集合所表示的树的层数

    public UnionFind6(int size) {

        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
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
        if (p != parent[p]) //当 p节点不是根节点
            parent[p] = find(parent[p]); //递归查找 p的根节点
        return parent[p];
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
        //根据两个元素所在树的 rank不同判断合并方向
        //将 rank低的集合合并到 rank高的集合上
        if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[qRoot] < rank[pRoot])
            parent[qRoot] = pRoot;
        else { //rank[pRoot] == rank[qRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
