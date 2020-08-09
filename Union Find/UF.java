import java.util.*;

public class UF
{
    private int[] id;
    UF(int N)
    {
        id = new int[N];
        for(int i=0;i<N;i++)
        {
            id[i]=i;
        }
    }
    boolean connected(int p, int q)
    {
        return (id[p] == id[p]);
    }
    void union(int p, int q)
    {
        for(int i=0;i<id.length;i++)
        {
            int initial_id = id[p];
            if(id[i] == initial_id)
            {
                id[i] = id[q];
            }
        }
    }
    public static void main()
    {

    }
}
