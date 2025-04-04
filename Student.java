import java.util.HashMap;
import java.util.Map;

/**
 * The Student class represents a student with an ID, name, and enrolled courses
 * with grades.
 */
public class Student {
    private int id;
    private String name;
    private Map<String, Double> courses; // Stores course names with their corresponding grades

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCourse(String course, double grade) {
        courses.put(course, grade);
    }

    public String getCourseReport() {
        if (courses.isEmpty()) {
            return "";
        }
        StringBuilder report = new StringBuilder();
        for (Map.Entry<String, Double> entry : courses.entrySet()) {
            report.append(entry.getKey()).append(" - Grade: ").append(entry.getValue()).append("\n");
        }
        return report.toString().trim(); // Remove the last newline
    }

    public double calculateAverageGrade() {
        if (courses.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (double grade : courses.values()) {
            total += grade;
        }
        return total / courses.size();
    }
}