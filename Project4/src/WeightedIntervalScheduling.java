import java.io.*;
import java.util.*;

@SuppressWarnings("Duplicates")
public class WeightedIntervalScheduling {

    /* Fields */
    private int[] P;
    private int[] M;
    private HashMap<Integer, Job> jobs;
    private ArrayList<Job> solution;
    private static WeightedIntervalScheduling instance;

    private WeightedIntervalScheduling() {
        instance = this;
    }

    /* Default Constructor */
    /** Jobs representation */
    private class Job {
        String id;
        int startTime;
        int endTime;
        int value;

        Job(String id, int startTime, int endTime, int value) {
            this.id = id;
            this.startTime = startTime;
            this.endTime = endTime;
            this.value = value;
        }

    }

    /* Methods */
    private int p(int j) {
        int jStartTime = jobs.get(j).startTime;
        int p = 0;
        for (int i = 1; i < j; i++) {
            int x = jobs.get(i).endTime;
            if (x < jStartTime) {
                p = i;
            }
        }
        return p;
    }

    /** Runs the recurse M-Compute-Opt and calculates its necessary resources. */
    private void runMComputeOpt(String jobsFilename) {

        output("- M-Compute-Opt");

        // Input Jobs file.
        jobs = new HashMap<>(3);
        ArrayList<Job> tempJobs = new ArrayList<>(3);
        try {
            InputStream jobsInput = getClass().getClassLoader().getResourceAsStream(jobsFilename);
            assert jobsInput != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(jobsInput));
            String line;

            // Read input as Jobs objects with ID, startTime, endTime, and value fields.
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    line = line.replace("(", "");
                    line = line.replace(")", "");
                    line = line.replace(" ", "");
                    String[] vals = line.split(",");
                    tempJobs.add(new Job(
                            vals[0], Integer.valueOf(vals[1]), Integer.valueOf(vals[2]), Integer.valueOf(vals[3]))
                    );
                }
            }
        } catch (IOException e) {
            System.out.println("Could not locate that file or it is empty.");
        }

        // Define global array M; -1 indicates that the value is null.
        M = new int[tempJobs.size()+1];
        for (int i = 1; i <= tempJobs.size(); i++) {
            M[i] = -1;
        }

        // Sort jobs by nondecreasing finish time.
        tempJobs.sort(Comparator.comparingInt(o -> o.endTime));
        for (int i = 1; i < tempJobs.size() + 1; i++) {
            jobs.put(i, tempJobs.get(i-1));
        }
        output("Sorted jobs");
        output("Jobs are sorted as:");
        for (Job job: jobs.values()) {
            output("\t" + "ID: " + job.id);
        }
        output("");

        // Compute p(1), p(2), ..., p(n).
        P = new int[jobs.size()+1];
        P[0] = 0;
        for (int j = 1; j < jobs.size() + 1; j++) {
            P[j] = p(j);
        }
        output("Computed p(i)-p(n)");
        output("Values are:");
        for (int i = 1; i <= jobs.size(); i++) {
            output("\tp(" + i + "): " + P[i]);
        }
        output("");

        // Initialize M[0] to 0.
        M[0] = 0;
        output("Computing M[i]-M[n]");
        output("Values are:");
        output("\tM[0] = 0");

        // Run M-Compute-Opt(n).
        MComputeOpt(jobs.size());
        output("");

        // Run Find-Solution(n).
        solution = new ArrayList<>(jobs.size());
        FindSolution(jobs.size());
        output("Computed optimal set of jobs");
        output("Optimal set is:");
        solution.sort(Comparator.comparingInt(o -> o.endTime));
        for (Job job : solution) {
            output("\tID: " + job.id);
        }
        output("");

    }

    /** Recursive implementation of OPT calculation. */
    private int MComputeOpt(int j) {

        // If M[j] has not been computed yet, calculate it.
        if (M[j] == -1) {
            M[j] = Math.max(jobs.get(j).value + MComputeOpt(P[j]), MComputeOpt(j-1));
            output("\tM[" + j + "] = " + M[j]);
        }

        return M[j];
    }

    /** Runs the non-recursive Iterative-Compute-Opt and calculates its necessary resources. */
    private void runIterativeComputeOpt(String jobsFilename) {
        output("- Iterative-Compute-Opt");

        // Input Jobs file.
        jobs = new HashMap<>(3);
        ArrayList<Job> tempJobs = new ArrayList<>(3);
        try {
            InputStream jobsInput = getClass().getClassLoader().getResourceAsStream(jobsFilename);
            assert jobsInput != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(jobsInput));
            String line;

            // Read input as Jobs objects with ID, startTime, endTime, and value fields.
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    line = line.replace("(", "");
                    line = line.replace(")", "");
                    line = line.replace(" ", "");
                    String[] vals = line.split(",");
                    tempJobs.add(new Job(
                            vals[0], Integer.valueOf(vals[1]), Integer.valueOf(vals[2]), Integer.valueOf(vals[3]))
                    );
                }
            }
        } catch (IOException e) {
            System.out.println("Could not locate that file or it is empty.");
        }

        // Define global array M; -1 indicates that the value is null.
        M = new int[tempJobs.size()+1];

        // Sort jobs by nondecreasing finish time.
        tempJobs.sort(Comparator.comparingInt(o -> o.endTime));
        for (int i = 1; i < tempJobs.size() + 1; i++) {
            jobs.put(i, tempJobs.get(i-1));
        }
        output("Sorted jobs");
        output("Jobs are sorted as:");
        for (Job job: jobs.values()) {
            output("\t" + "ID: " + job.id);
        }
        output("");

        // Compute p(1), p(2), ..., p(n).
        P = new int[jobs.size()+1];
        P[0] = 0;
        for (int j = 1; j < jobs.size() + 1; j++) {
            P[j] = p(j);
        }
        output("Computed p(i)-p(n)");
        output("Values are:");
        for (int i = 1; i <= jobs.size(); i++) {
            output("\tp(" + i + "): " + P[i]);
        }
        output("");

        // Run Iterative-Compute-Opt.
        output("Computing M[i]-M[n]");
        output("Values are:");
        output("\tM[0] = 0");
        IterativeComputeOpt();
        output("");

        // Run Find-Solution(n).
        solution = new ArrayList<>(jobs.size());
        FindSolution(jobs.size());
        output("Computed optimal set of jobs");
        output("Optimal set is:");
        solution.sort(Comparator.comparingInt(o -> o.endTime));
        for (Job job : solution) {
            output("\tID: " + job.id);
        }

    }

    /** Iterative OPT calculator. */
    private void IterativeComputeOpt() {

        // Initialize base case.
        M[0] = 0;

        // Calculate all memoizations.
        for (int j = 1; j <= jobs.size(); j++) {
            M[j] = Math.max(jobs.get(j).value + M[P[j]], M[j-1]);
            output("\tM[" + j + "] = " + M[j]);
        }

    }

    /** Finds the solution of a memoization-table by back-tracking. */
    private void FindSolution(int j) {
        if (j != 0) {
            if (jobs.get(j).value + M[P[j]] > M[j-1]) {
                solution.add(jobs.get(j));
                FindSolution(P[j]);
            } else {
                FindSolution(j-1);
            }
        }
    }

    /** Simple System.out shortcut */
    private void output(String output) {
        System.out.println(output);
    }

    public static void main(String[] args) {

        new WeightedIntervalScheduling();

        while (true) {

            // User-input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name of a text file (q to quit): ");
            String input = scanner.next();
            if (input.equals("q")) {
                System.out.println("Goodbye");
                break;
            }
            if (instance.getClass().getClassLoader().getResourceAsStream(input) == null) {
                System.out.println("Cannot locate that file.");
                continue;
            }
            System.out.println();

            // Run algorithms with given input-file.
            instance.runMComputeOpt(input);
            instance.runIterativeComputeOpt(input);
            System.out.println();

        }

    }

}
