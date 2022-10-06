package Task03;

class mergeSort extends Thread 
{
	int arr[];
	int l,h;
	
	public mergeSort(int a[], int low, int high)
	{
		arr = new int[a.length];
		arr = a;
		l = low;
		h = high;
	}
	
	@Override
	public void run() 
	{
		if(l < h) 
		{
			int mid = (l+h)/2;
			
			mergeSort Left_mSort = new mergeSort(arr, l, mid);
			Left_mSort.start();
			mergeSort Right_mSort = new mergeSort(arr, mid+1, h);
			Right_mSort.start();
			
			try {
				Left_mSort.join();
				Right_mSort.join();
			}
			catch(Exception e) {
				System.out.println("Exception faced");
			}
			
			merge m = new merge(arr, l , mid, h);
			m.start();
		}
	}
}

class merge extends Thread
{
	int arr[];
	int l, mid, h;
	
	public merge(int a[], int low, int m, int high) 
	{
		arr = a;
		l = low;
		mid = m;
		h = high;
	}
	
	@Override
	public void run() 
	{
		int n1 = (mid-l)+1;
		int n2 = (h-mid);
		
		
		int L[] = new int[n1];
		int R[] = new int[n2];
		
		
		for(int i=0; i<n1; i++) 
		{
			L[i] = arr[l+i];
		}
		for(int j=0; j<n2; j++) 
		{
			R[j] = arr[mid+1 + j];
		}
		
		int i=0, j=0, k=l;
		
		while(i<n1 && j<n2)
		{
			if(L[i] <= R[j]) 
			{
				arr[k++] = L[i++];
			}
			else 
			{
				arr[k++] = R[j++];
			}
		}
		
		while(i < n1) 
			arr[k++] = L[i++];
		
		while(j < n2) 
			arr[k++] = R[j++];
	}
}

public class ThreadTest 
{	
	public static void print(int arr[]) 
	{
		for(int i=0; i< arr.length; i++)
			System.out.print(arr[i] + "  ");
		
		System.out.println("\n");
	}
	
	public static void main(String [] args) 
	{
		int arr[] = {5, 3, 9, 1, 2, 7, 4, 17, 12, 28, 23, 13, 87};
		System.out.println("Before sorting, the initial array is : \n");
		print(arr);
		
		
		mergeSort mSort = new mergeSort(arr, 0, arr.length-1);
		mSort.start();
		
		try {
			mSort.join();
		}
		catch(Exception e){
			System.out.println("Exception faced");
		}
		
		System.out.println("After sorting the array is : \n");
		print(arr);
		
	}
}

