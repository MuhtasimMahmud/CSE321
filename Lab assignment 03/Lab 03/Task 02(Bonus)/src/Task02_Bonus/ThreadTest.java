package Task02_Bonus;

import java.util.*;

class myThread extends Thread
{
	int start,end;
	ThreadTest test;
	
	public myThread(int s, int e, ThreadTest t) 
	{
		start = s;
		end = e;
		test = t;
	}
	
	@Override
	public void run() 
	{		
		int key = start;
		int max_divisors = 0;
		
		for(int i=start; i<=end; i++) 
		{
			int divisor_count = 0;
			for(int j=1; j<=i; j++) 
			{
				if(i%j == 0)
					divisor_count++;
			}
			
			if(divisor_count > max_divisors) 
			{
				key = i;
				max_divisors = divisor_count;
			}
		}
		
		test.hash_map.put(key, max_divisors);
	}
}

public class ThreadTest 
{	
	HashMap<Integer, Integer> hash_map = new HashMap<Integer, Integer>();
	
	public static void main(String [] args) 
	{
		ThreadTest t = new ThreadTest();
		
		myThread t0 = new myThread(1,   10000, t);
		myThread t1 = new myThread(10001, 20000, t);
		myThread t2 = new myThread(20001, 30000, t);
		myThread t3 = new myThread(30001, 40000, t);
		myThread t4 = new myThread(40001, 50000, t);
		myThread t5 = new myThread(50001, 60000, t);
		myThread t6 = new myThread(60001, 70000, t);
		myThread t7 = new myThread(70001, 80000, t);
		myThread t8 = new myThread(80001, 90000, t);
		myThread t9 = new myThread(90001, 100000, t);
		
		
		System.out.println("Single Thread running : ");
		long start_time = System.currentTimeMillis();
		
		t0.run();
		t1.run();
		t2.run();
		t3.run();
		t4.run();
		t5.run();
		t6.run();
		t7.run();
		t8.run();
		t9.run();
		
		try{
			t0.join();
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
		}catch(Exception e){
			System.out.println("Exception faces");
		}
	
		/**
		 * The HashMap was giving the key values in descending order.
		 * So, i have made the HashMap reverse order to see the numbers as ascending order.
		*/
		
		List<Integer> keys = new ArrayList<Integer>(t.hash_map.keySet());
		Collections.reverse(keys);
		
		int result_key = 0;
		int divisors = 0;
		
		for(int i : keys) 
		{
//			System.out.println(" The Integer number  " + i + " has " + t.hash_map.get(i) + " divisors.");

			if(t.hash_map.get(i) > divisors) 
			{
				result_key = i;
				divisors = t.hash_map.get(i);
			}
		}
		
		System.out.println("\nThe integer which has highest number of divisors is : " + result_key + ", which has " + divisors + " divisors.");
		
		long end_time = System.currentTimeMillis();
		
		long execution_time = (end_time - start_time )/1000;

		System.out.println("Execution time for single thread = " + execution_time + " seconds.");
		
		System.out.println("\n\n");
		
		
		System.out.println("Multiple Thread running : ");
		start_time = System.currentTimeMillis();
		
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		
		try{
			t0.join();
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
		}catch(Exception e){
			System.out.println("Exception faces");
		}
		
		
		result_key = 0;
		divisors = 0;
		
		for(int i : keys) 
		{
//			System.out.println(" The Integer number  " + i + " has " + t.hash_map.get(i) + " divisors.");

			if(t.hash_map.get(i) > divisors) 
			{
				result_key = i;
				divisors = t.hash_map.get(i);
			}
		}
		
		System.out.println("\nThe integer which has highest number of divisors is : " + result_key + ", which has " + divisors + " divisors.");
		
		end_time = System.currentTimeMillis();
		
		execution_time = (end_time - start_time )/1000;

		System.out.println("Execution time for multiple thread = " + execution_time + " seconds.");
		
		
		System.out.println("\n\nIn my pc single thread is taking nearly double time of multiple thread.");
		
	}
}

