import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 * The StudentManagementSystem class manages a list of students and provides
 * methods
 * to add, update, delete students and generate reports.
 */
public class StudentManagementSystem {
    private List<Student> students;
    private static final String[] NAMES = { "Alice", "Bob", "Charlie", "David", "Eve", "Faythe", "Grace", "Heidi",
            "Ivan", "Judy" };
    private static final Random RANDOM = new Random();

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    // Add a new student to the system
    public void addStudent(Student student) {
        students.add(student);
    }

    // Generate a random student and add them to the system
    public void addRandomStudent() {
        int id = RANDOM.nextInt(1000) + 1; // Generate random ID between 1 and 1000
        String name = NAMES[RANDOM.nextInt(NAMES.length)];
        students.add(new Student(id, name));
        System.out.println("Random Student Added: " + name + " (ID: " + id + ")");
    }

    // Remove a student by ID
    public boolean removeStudent(int studentId) {
        return students.removeIf(s -> s.getId() == studentId);
    }

    // Find a student by ID
    public Student findStudent(int studentId) {
        for (Student s : students) {
            if (s.getId() == studentId) {
                return s;
            }
        }
        return null; // not found
    }

    // Update a student's name by ID
    public boolean updateStudentName(int studentId, String newName) {
        Student s = findStudent(studentId);
        if (s != null) {
            s.setName(newName);
            return true;
        }
        return false;
    }

    // Generate a report for a specific student
    public void generateReport(int studentId) {
        Student s = findStudent(studentId);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("=== Student Report ===");
        System.out.println("ID: " + s.getId() + ", Name: " + s.getName());
        String report = s.getCourseReport();
        if (report.isEmpty()) {
            System.out.println(" No courses enrolled.");
        } else {
            System.out.println(" Courses & Grades: \n" + report);
            System.out.printf(" Average Grade: %.2f%n", s.calculateAverageGrade());
        }
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Random Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Update Student Name");
            System.out.println("5. Enroll Student in Course");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Student
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    sms.addStudent(new Student(id, name));
                    System.out.println("Student added successfully.");
                    break;

                case 2: // Add Random Student
                    sms.addRandomStudent();
                    break;

                case 3: // Remove Student
                    System.out.print("Enter student ID to remove: ");
                    int removeId = scanner.nextInt();
                    if (sms.removeStudent(removeId)) {
                        System.out.println("Student removed successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4: // Update Student Name
                    System.out.print("Enter student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter new student name: ");
                    String newName = scanner.nextLine();

                    if (sms.updateStudentName(updateId, newName)) {
                        System.out.println("Student name updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5: // Enroll Student in Course
                    System.out.print("Enter student ID to enroll: ");
                    int enrollId = scanner.nextInt();
                    scanner.nextLine();

                    Student student = sms.findStudent(enrollId);
                    if (student != null) {
                        System.out.print("Enter course name: ");
                        String course = scanner.nextLine();

                        System.out.print("Enter grade: ");
                        double grade = scanner.nextDouble();
                        scanner.nextLine();

                        student.addCourse(course, grade);
                        System.out.println("Course added successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6: // Generate Report
                    System.out.print("Enter student ID to generate report for: ");
                    int reportId = scanner.nextInt();
                    sms.generateReport(reportId);
                    break;

                case 7: // Exit
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}