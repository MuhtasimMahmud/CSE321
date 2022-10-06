import java.util.*;

public class Priority_Scheduling {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of process: ");
        int n = sc.nextInt();
        System.out.println();
        int st = 0;
        int tot = 0;
        int priority[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int wt[] = new int[n];
        int tat[] = new int[n];
        int k[] = new int[n];
        int f[] = new int[n];


        for (int i = 0; i < n; i++) {
            System.out.print("Enter process " + (i + 1) + "'s arrival time (enter 0 if all arrive at the same time): ");
            at[i] = sc.nextInt(); // this will be zero for the given example
            System.out.println();
            System.out.print("Enter process " + (i + 1) + "'s burst time: ");
            bt[i] = sc.nextInt();
            System.out.println();
            System.out.print("Enter process " + (i + 1) + "'s priority: ");
            priority[i] = sc.nextInt();
            System.out.println();
            k[i] = bt[i];
            f[i] = 0;
        }

        while (true) {
            int min = Integer.MAX_VALUE;
            int c = n;
            if (tot == n)
                break;

            for (int i = 0; i < n; i++) {
                if ((priority[i] <= min) && (f[i] == 0) && (at[i] <= st)) {
                    min = priority[i];
                    c = i;
                }
            }

            if (c == n)
                st++;
            else {
                bt[c]--;
                st++;
                if (bt[c] == 0) {
                    ct[c] = st;
                    f[c] = 1;
                    tot++;
                }
            }
        }
        float avg_wt = 0, avg_tat = 0;

        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - k[i];
            avg_wt += wt[i];
            avg_tat += tat[i];
        }


        for (int i = 0; i < n; i++) {
            System.out.println("Process number: " + (i + 1) + " Completion Time: " + ct[i] + " Turnaround Time: " + tat[i] + " Waiting Time: " + wt[i]);
        }

        System.out.println();

        System.out.println("Average Turnaround Time: " + (float) avg_tat / n);
        System.out.println("Average Waiting Time: " + (float) avg_wt / n);
    }
}
