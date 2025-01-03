import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolledStudents;
    String schedule;

    Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public boolean hasAvailableSlots() {
        return enrolledStudents < capacity;
    }

    public void enrollStudent() {
        if (hasAvailableSlots()) {
            enrolledStudents++;
        } else {
            throw new IllegalStateException("No available slots.");
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }

    @Override
    public String toString() {
        return courseCode + " - " + title + " (" + enrolledStudents + "/" + capacity + " enrolled)";
    }
}

class Student {
    String studentId;
    String name;
    List<String> registeredCourses;

    Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    private static Map<String, Course> courses = new HashMap<>();
    private static Map<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeSampleData();

        while (true) {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> listCourses();
                case 2 -> registerForCourse(scanner);
                case 3 -> dropCourse(scanner);
                case 4 -> viewRegisteredCourses(scanner);
                case 5 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeSampleData() {
        courses.put("CS101", new Course("CS101", "Intro to Programming", "Learn the basics of programming.", 30, "Mon 9-11AM"));
        courses.put("CS102", new Course("CS102", "Data Structures", "Learn about arrays, lists, and trees.", 25, "Wed 10-12AM"));
        courses.put("CS103", new Course("CS103", "Database Systems", "Introduction to relational databases.", 20, "Fri 1-3PM"));

        students.put("S001", new Student("S001", "Alice"));
        students.put("S002", new Student("S002", "Bob"));
    }

    private static void listCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        if (!course.hasAvailableSlots()) {
            System.out.println("No available slots in the course!");
            return;
        }

        if (student.registeredCourses.contains(courseCode)) {
            System.out.println("You are already registered for this course.");
            return;
        }

        course.enrollStudent();
        student.registeredCourses.add(courseCode);
        System.out.println("Successfully registered for the course!");
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);

        if (course == null || !student.registeredCourses.contains(courseCode)) {
            System.out.println("You are not registered for this course.");
            return;
        }

        course.dropStudent();
        student.registeredCourses.remove(courseCode);
        System.out.println("Successfully dropped the course!");
    }

    private static void viewRegisteredCourses(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("\n--- Registered Courses for " + student.name + " ---");
        for (String courseCode : student.registeredCourses) {
            Course course = courses.get(courseCode);
            System.out.println(course);
        }
    }
}
