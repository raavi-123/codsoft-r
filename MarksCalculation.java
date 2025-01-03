import java.util.Scanner;

public class MarksCalculation {

    public static double[] calculateTotalAndAverage(int[] marks) {
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        double averagePercentage = (double) totalMarks / marks.length;
        return new double[]{totalMarks, averagePercentage};
    }

    public static String assignGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Marks Calculation Program!");
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        int[] marks = new int[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            while (true) {
                System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
                int mark = scanner.nextInt();
                if (mark >= 0 && mark <= 100) {
                    marks[i] = mark;
                    break;
                } else {
                    System.out.println("Please enter a valid mark between 0 and 100.");
                }
            }
        }

        double[] results = calculateTotalAndAverage(marks);
        double totalMarks = results[0];
        double averagePercentage = results[1];

        String grade = assignGrade(averagePercentage);

        System.out.println("\nResults:");
        System.out.printf("Total Marks: %.2f out of %.2f%n", totalMarks, numSubjects * 100.0);
        System.out.printf("Average Percentage: %.2f%%%n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}