package UnionFind;

public interface UF {

    int getSize();

    boolean isConnected(int p, int q); //�ж�����Ԫ��֮���Ƿ�����

    void unionElements(int p, int q); //������Ԫ�غϲ���һ��,���һ�������е�Ԫ��
}
