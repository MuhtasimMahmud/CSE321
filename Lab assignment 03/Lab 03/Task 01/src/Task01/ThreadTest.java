package Task01;

class myThread_one extends Thread
{
	@Override
	public void run() 
	{
		for(int i=1; i<=10; i++) 
		{
			System.out.println(i);
		}
		
		System.out.println();
		
		myThread_two t2 = new myThread_two();
		t2.start();
		
		try 
		{
			t2.join();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println();
		
		for(int i=21; i<=30; i++) 
		{
			System.out.println(i);
		}
	}
}

class myThread_two extends Thread
{
	@Override
	public void run() 
	{
		for(int i=11; i<=20; i++) 
		{
			System.out.println(i);
		}
	}
}

public class ThreadTest 
{
	public static void main(String [] args) 
	{		
		myThread_one t1 = new myThread_one();
		t1.start();
	}
}
