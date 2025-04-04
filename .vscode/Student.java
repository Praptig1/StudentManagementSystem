import java.util.HashMap;
import java.util.Map;

/**
 * The Student class represents a student with an id, name, and a record of courses and grades.
 */
public class Student {
    private int id;
    private String name;
    // Map of course names to grades for the student
    private Map<String, Double> courses;

    // Constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new HashMap<>();
    }

    // Getter and Setter for name (id is assumed unique and unchangeable after creation)
    public String getName() {
        return name;
    }
    public void setName(String newName) {
        this.name = newName;
    }

    public int getId() {
        return id;
    }

    // Enroll the student in a course with a given grade.
    public void addCourse(String courseName, double grade) {
        courses.put(courseName, grade);
    }

    // Remove a course from the student's record.
    public void removeCourse(String courseName) {
        courses.remove(courseName);
    }

    // Calculate the student's average grade across all courses.
    public double calculateAverageGrade() {
        if (courses.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (double grade : courses.values()) {
            total += grade;
        }
        return total / courses.size();
    }

    // Generate a formatted string listing the student's courses and grades.
    public String getCourseReport() {
        if (courses.isEmpty()) {
            return "No courses enrolled.";
        }
        StringBuilder report = new StringBuilder();
        for (Map.Entry<String, Double> entry : courses.entrySet()) {
            report.append(entry.getKey())
                  .append(": ")
                  .append(entry.getValue());
            report.append("; ");
        }
        // Remove trailing separator
        if (report.length() > 2) {
            report.setLength(report.length() - 2);
        }
        return report.toString();
    }

    @Override
    public String toString() {
        // Return a summary of the student
        return "Student[id=" + id + ", name=" + name + "]";
    }
}
