package UnionFind;

/**
 * Quick Find
 * unionElements(p,q) 复杂度为 O(n)
 * isConnected(p,q) 复杂度为 O(1)
 *
 * @author wangzhe
 */
public class UnionFind1 implements UF {

    private int[] id;

    public UnionFind1(int size) {

        id = new int[size];
        for (int i = 0; i < id.length; i++)
            id[i] = i;
    }

    @Override
    public int getSize() {
        return id.length;
    }

    //查找元素 p所对应的集合的编号
    private int find(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("p is out of bound.");
        return id[p];
    }

    //查看元素 p和 q是否属于同一个集合
    @Override //时间复杂度为 O(1)
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并元素 p和元素 q所属的集合
    @Override //时间复杂度为 O(n)
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID)
            return;
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID)
                id[i] = qID;
    }
}
