import java.util.Scanner;

public class GradeStudent {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // 1. Enter number of students
        System.out.println("Enter number of students:");
        int numberStudents = Integer.parseInt(input.nextLine());

        // 2. Repeat for each student
        for (int s = 1; s <= numberStudents; s++) {

            System.out.println("\n========== STUDENT " + s + " ==========");

            System.out.println("Enter student name:");
            String name = input.nextLine();

            System.out.println("Enter number of semesters:");
            int numberSemesters = Integer.parseInt(input.nextLine());

            // Initialize grand totals for the student across all semesters
            double totalQualityPoint = 0;
            int totalCredit = 0;

            // 3. Repeat for each semester
            for (int sem = 1; sem <= numberSemesters; sem++) {

                System.out.println("\n--- SEMESTER " + sem + " ---");

                System.out.println("Enter number of courses for Semester " + sem + ":");
                int numbercourse = Integer.parseInt(input.nextLine());

                // 4. Repeat for each course in the current semester
                for (int i = 1; i <= numbercourse; i++) {

                    System.out.println("\nCourse " + i);

                    System.out.println("Enter course name:");
                    String courseName = input.nextLine();

                    System.out.println("Enter credit hour:");
                    int credit = Integer.parseInt(input.nextLine());

                    System.out.println("Enter your mark:");
                    int mark = Integer.parseInt(input.nextLine());

                    String grade;
                    double point;

                    // 5. Grade calculation
                    if (mark >= 90 && mark <= 100) {
                        grade = "A+";
                        point = 4.0;
                    } else if (mark >= 85) {
                        grade = "A";
                        point = 4.0;
                    } else if (mark >= 80) {
                        grade = "A-";
                        point = 3.75;
                    } else if (mark >= 75) {
                        grade = "B+";
                        point = 3.25;
                    } else if (mark >= 70) {
                        grade = "B";
                        point = 3.0;
                    } else if (mark >= 65) {
                        grade = "B-";
                        point = 2.75;
                    } else if (mark >= 60) {
                        grade = "C+";
                        point = 2.25;
                    } else if (mark >= 50) {
                        grade = "C";
                        point = 2.0;
                    } else if (mark >= 45) {
                        grade = "D";
                        point = 1.0;
                    } else if (mark >= 35) {
                        grade = "F";
                        point = 0;
                    } else {
                        grade = "Non grade";
                        point = 0;
                    }

                    // 6. Calculate quality point
                    double qualityPoint = point * credit;

                    // Accumulate totals across all semesters
                    totalQualityPoint += qualityPoint;
                    totalCredit += credit;

                    // 7. Display course result
                    System.out.println("\nCourse Name: " + courseName);
                    System.out.println("Mark: " + mark);
                    System.out.println("Grade: " + grade);
                    System.out.println("Grade Point: " + point);
                    System.out.println("Quality Point: " + qualityPoint);
                }
            }

            // 8. Calculate and display Cumulative GPA (CGPA) for the student
            System.out.println("\n========== CUMULATIVE STUDENT RESULT (CGPA) ==========");
            System.out.println("Student Name: " + name);
            System.out.println("Total Quality Points (All Semesters): " + totalQualityPoint);
            System.out.println("Total Credit Hours (All Semesters): " + totalCredit);

            if (totalCredit > 0) {
                double cgpa = totalQualityPoint / totalCredit;
                System.out.printf("Cumulative GPA (CGPA): %.2f%n", cgpa);
            } else {
                System.out.println("CGPA cannot be calculated.");
            }
        }

        input.close();
    }
}
