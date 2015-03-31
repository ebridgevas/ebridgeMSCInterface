
class DaemonThreadTest
{
    public static void main (String [] args)
    {
        if (args.length == 0)
            new MyThread ().start ();
        else
        {
            MyThread mt = new MyThread ();
            mt.setDaemon (true);
            mt.start ();
        }
        try
        {
            Thread.sleep (10000);
        }
        catch (InterruptedException e)
        {
        }
        System.out.println("Program ends");
    }
}
class MyThread extends Thread
{

    public void run ()
    {
        try {
            System.out.println("Daemon is " + isDaemon());
            while (true) {
                System.out.print(".");
                try { Thread.sleep(1000); } catch (Exception e){}
            }
        }finally {
            System.out.println("Worker thread ends");
        }
    }
}