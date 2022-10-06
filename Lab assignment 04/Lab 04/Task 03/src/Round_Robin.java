import java.util.Scanner;

public class Round_Robin {
    public static void calc_WT(int wt[], int n, int bt[], int quantum, int ct[], int at[]) {

        int remaining[] = new int[n];

        for (int i = 0; i < wt.length; i++) {
            remaining[i] = bt[i];
        }
        int t = 0;
        int arrival = 0;

        while (true) {

            boolean done = true;
            for (int i = 0; i < n; i++) {
                if (remaining[i] > 0) {
                    done = false;
                    if (remaining[i] > quantum && at[i] <= arrival) {
                        t += quantum;
                        remaining[i] -= quantum;
                        arrival++;
                    } else {
                        if (at[i] <= arrival) {
                            arrival++;
                            t += remaining[i];
                            remaining[i] = 0;
                            ct[i] = t;
                        }
                    }
                }
            }

            if (done == true) {
                break;
            }
        }
    }

    public static void calc_TAT(int wt[], int n, int bt[], int tat[], int ct[], int at[]) {
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

    }

    public static void calculation(int n, int bt[], int quantum, int at[]) {
        int wt[] = new int[n];
        int tat[] = new int[n];
        int ct[] = new int[n];
        calc_WT(wt, n, bt, quantum, ct, at);
        calc_TAT(wt, n, bt, tat, ct, at);
        int total_wt = 0, total_tat = 0;

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println("Process number: " + (i+1) + " Completion Time: " + ct[i] + " Turnaround Time: " + tat[i] + " Waiting Time: " + wt[i]);
        }

        System.out.println();

        System.out.println("Average Turnaround Time: " + (float) total_tat / n);
        System.out.println("Average Waiting Time: " + (float) total_wt / n);
    }

    public static void main(String[] agrs) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of process: ");
        int n = sc.nextInt();
        System.out.println();
        int at[] = new int[n];
        int bt[] = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter process " + (i + 1) + "'s arrival time: ");
            at[i] = sc.nextInt();
            System.out.println();
            System.out.print("Enter process " + (i + 1) + "'s burst time: ");
            bt[i] = sc.nextInt();
            System.out.println();
        }
        int quantum = 4;
        calculation(n, bt, quantum, at);


    }
}
