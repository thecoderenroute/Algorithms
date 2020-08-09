import java.util.Scanner;

public class QF
{
    int[] id;
    int [] sz;
    QF(int N)
    {
        id = new int[N];
        sz = new int[N];
        for(int i=0;i<N;i++)
        {
            id[i] = i;
            sz[i]=1;

        }
    }
    private int root(int i)
    {
        while(i != id[i])
            i = id[i];
        return i;
    }
    public boolean connected(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        return root(p) == root(q);
    }
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if(sz[i]<=sz[j])
        {
            id[i] = j;
            sz[j] += sz[i];
        }
        else
        {
            id[j]=i;
            sz[i]+=sz[j];
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        QF qf = new QF(sc.nextInt());
        System.out.println("Enter p and q");
        while(sc.hasNext())
        {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (!qf.connected(p, q))
            {
                qf.union(p, q);
                System.out.println(p+" and "+q+" are not connected");
            }
            else
                System.out.println(p+" and "+q+" are connected");
        }
    }
}
