package UnionFind;

/**
 * Quick Union
 * unionElements(p,q) ���Ӷ�Ϊ O(h)
 * isConnected(p,q) ���Ӷ�Ϊ O(h)
 * find ����Ϊ�ӵ����ϲ��Ҽ���,hΪ���ĸ߶�
 * ��ԭ�����Ͻ����Ż�,���� sz��ʾ�� iΪ���ļ����е�Ԫ�ظ���,�Լ�С�ϲ����������
 *
 * @author wangzhe
 */
public class UnionFind3 implements UF {

    private int[] parent;
    private int[] sz; //sz[i]��ʾ�� iΪ���ļ�����Ԫ�صĸ���

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
        //��������Ԫ����������Ԫ�ظ�����ͬ�жϺϲ�����
        //��Ԫ�ظ����ٵļ��Ϻϲ���Ԫ�ظ�����ļ�����
        if (sz[pRoot] <= sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else { //sz[pRoot] > sz[qRoot]
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }
}
