package UnionFind;

/**
 * Quick Union
 * unionElements(p,q) ���Ӷ�Ϊ O(h)
 * isConnected(p,q) ���Ӷ�Ϊ O(h)
 * find ����Ϊ�ӵ����ϲ��Ҽ���,hΪ���ĸ߶�
 * ��ԭ�����Ͻ����Ż�,���� rank��ʾ�� iΪ�����������,�Լ�С�ϲ����������
 * �� 4�Ļ����ϼ���·��ѹ������,parent[p] = parent[parent[p]]
 *
 * @author wangzhe
 */
public class UnionFind5 implements UF {

    private int[] parent;
    private int[] rank; //rank[i]��ʾ�� iΪ���ļ�������ʾ�����Ĳ���

    public UnionFind5(int size) {

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

    //Ѱ��Ԫ�� p����Ӧ�ĸ��ڵ�,����Ӧ�ļ��ϱ��
    //�������ϲ���,O(h)���Ӷ�,hΪ�ڵ��Ӧ���ĸ߶�
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");
        while (p != parent[p]) {
            //·��ѹ��,�ø��ڵ�ĸ��ڵ�Ϊ�丸�ڵ�
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
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
        //��������Ԫ���������� rank��ͬ�жϺϲ�����
        //�� rank�͵ļ��Ϻϲ��� rank�ߵļ�����
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