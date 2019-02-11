/*
Property of Alexander Joseph Swanson Villares
alexanderjswanson@icloud.com | https://github.com/ajswanny
*/


/* Imports */
import java.io.*;
import java.util.*;


public class IntervalPartitioning {

    /* Children */
    /**
     * A representation of a Classroom to contain scheduled Lectures.
     */
    private class Classroom implements Comparable<Classroom> {

        /* Fields */
        ArrayList<Lecture> lectures = new ArrayList<>();
        ArrayList<Integer> finishTimes = new ArrayList<>();
        int roomNumber;


        /* Constructor */
        /**
         * Default constructor.
         * @param lecture The Lecture to be scheduled first in this Classroom.
         * @param roomNumber The number of the Classroom.
         */
        private Classroom(Lecture lecture, int roomNumber) {
            addLecture(lecture); this.roomNumber = roomNumber;
        }


        /* Methods */
        /**
         * Adds a new Lecture to this Classroom.
         * @param lecture The Lecture to append.
         */
        void addLecture(Lecture lecture) {

            // Append the Lecture and keep track of its finish-time.
            lectures.add(lecture);
            finishTimes.add(lecture.finishTime);

        }

        /**
         * @return The latest (maximum) finish-time.
         */
        private int findMaxFinishTime() {
            return Collections.max(finishTimes);
        }

        /**
         * A custom implementation of Comparable for Classroom. Orders Classrooms based on which one has the earliest
         * finish-time.
         * @param o The Classroom to which this object will be compared.
         * @return A comparison of the maximum-finish-times of each of the Classrooms.
         */
        @Override
        public int compareTo(Classroom o) {
            return Integer.compare(findMaxFinishTime(), o.findMaxFinishTime());
        }

    }

    /**
     * A representation of a Lecture with an ID, start-time, and end-time.
     */
    public class Lecture implements Comparable<Lecture> {

        /* Fields */
        private String lectureId;
        private int startTime;
        private int finishTime;


        /* Constructor */
        /**
         * The default constructor.
         * @param lectureId The given name.
         * @param startTime The given start-time.
         * @param finishTime The given end-time.
         */
        Lecture(String lectureId, int startTime, int finishTime) {
            this.lectureId = lectureId;
            this.startTime = startTime;
            this.finishTime = finishTime;
        }


        /* Methods */
        /**
         * A custom implementation of Comparable for Lecture. Lectures are compared based on which one has a later
         * start-time.
         * @param o The Lecture to which this object will be compared.
         * @return A comparison for later start-time.
         */
        @Override
        public int compareTo(Lecture o) {

            return Integer.compare(this.startTime, o.startTime);

        }

    }


    /* Methods */
    /**
     * This method schedules a List of lectures using a greedy Interval-Scheduling algorithm. The scheduling is printed
     * out to the console.
     * @param lectures The Lectures to schedule within Classrooms.
     */
    private void scheduleLectures(List<Lecture> lectures) {

        try {

            // Sort the Lectures in the given list.
            Collections.sort(lectures);

            // Initialize the first Classroom to hold lectures.
            Classroom classroom = new Classroom(lectures.get(0), 1);

            // Initialize a Priority Queue for Classrooms and add a first value.
            PriorityQueue<Classroom> classroomQueue = new PriorityQueue<>();
            classroomQueue.add(classroom);

            // Initialize a counter for the amount of allocated classrooms.
            int allocatedClassrooms = 1;

            System.out.println("During the program run:");
            outputDataFor(classroom, lectures.get(0));

            // Apply an implementation of Interval Scheduling to the Lectures.
            for (int i = 1; i < lectures.size(); i++) {

                // Fetch the Classroom with the earliest finish-time.
                classroom = classroomQueue.peek();

                // Fetch the next Lecture.
                Lecture lecture = lectures.get(i);

                // If the finish-time of the current Lecture in iteration is less than the latest finish-time for the
                // current Classroom in iteration, schedule the Lecture within that Classroom.
                assert classroom != null;
                if (lectures.get(i).startTime >= classroom.findMaxFinishTime()) {

                    classroom.addLecture(lecture);
                    outputDataFor(classroom, lecture);

                } else {

                    allocatedClassrooms++;
                    classroom = new Classroom(lecture, allocatedClassrooms);
                    classroomQueue.add(classroom);

                    outputDataFor(classroom, lecture);

                }

            }

            // Output Lecture scheduling.
            System.out.println("After the end of the program run:");
            for (int i = 1; i <= allocatedClassrooms; i++) {

                Classroom c = classroomQueue.poll();
                System.out.printf("Room %d: ", c.roomNumber);
                for (Lecture l : c.lectures) {
                    System.out.printf("(%s, %d, %d) ", l.lectureId, l.startTime, l.finishTime);
                }
                System.out.println();

            }

        } catch (IndexOutOfBoundsException e) {

            System.out.println("There is no Lecture data in the provided file.");

        }


    }

    private void outputDataFor(Classroom classroom, Lecture lecture) {

        System.out.printf(
                "Room %d: (%s, %d, %d)\n",
                classroom.roomNumber, lecture.lectureId, lecture.startTime, lecture.finishTime
        );

    }


    /* Constructor */
    /**
     * The default constructor.
     */
    private IntervalPartitioning() {

        while (true) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the name of a text file (q to quit): ");
            String input = scanner.next();
            System.out.println();

            if (input.equals("q")) { System.out.println("Goodbye"); break; }

            try {

                // Create a container for Lectures to be scheduled.
                ArrayList<Lecture> lectures = new ArrayList<>();

                // Define methodology for reading the specified TXT file containing Lecture data.
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(input);
                assert inputStream != null;
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                // Read, clean, and record the data.
                while ((line = reader.readLine()) != null) {
                    line = line.replace("(", "");
                    line = line.replace(")", "");
                    line = line.replace(" ", "");
                    String[] values = line.split(",");
                    lectures.add(new Lecture(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2])));
                }

                // Run the Interval-Scheduling algorithm to schedule the input Lectures.
                scheduleLectures(lectures);

            } catch (FileNotFoundException e) {

                System.out.println("Could not locate the file: " + input);

            } catch (IOException e) {

                System.out.println("IOException when reading specified file. Please check that the TXT file is valid");

            } catch (NullPointerException e) {

                System.out.println("That file contains no text or does not exist. Please enter the name of a valid " +
                        "file");

            }

        }

    }

    public static void main(String[] args) {

        new IntervalPartitioning();

    }

}