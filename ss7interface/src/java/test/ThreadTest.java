/**
 * @author david@tekeshe.com
 */
public class ThreadTest
{
    public static void main (String [] args)
    {
        WorkerThread worker = new WorkerThread ();
        worker.start ();
        for (int i = 0; i < 50; i++)
            System.out.println ("i = " + i + ", i * i = " + i * i);
    }
}

class WorkerThread extends Thread
{

    public void run()
    {
        for (int count = 1, row = 1; row < 20; row++, count++)
        {
            for (int i = 0; i < count; i++)
                System.out.print ('*');
            System.out.print ('\n');
        }
    }
}
