package com.riya.studentcourse;

import com.riya.studentcourse.entity.Course;
import com.riya.studentcourse.entity.Student;
import com.riya.studentcourse.entity.StudentCourse;
import com.riya.studentcourse.repository.CourseRepository;
import com.riya.studentcourse.repository.StudentCourseRepository;
import com.riya.studentcourse.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class StudentCourseApplication {

    private static final Logger logger = LoggerFactory.getLogger(StudentCourseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentCourseApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(StudentRepository studentRepository,
                                      CourseRepository courseRepository,
                                      StudentCourseRepository studentCourseRepository) {
        return args -> {
            logger.info("Starting data pre-population...");

            List<Course> courses = Arrays.asList(
                new Course("Introduction to Programming", "Fundamentals of Java and Python", 3),
                new Course("Web Development Basics", "HTML, CSS, JavaScript essentials", 4),
                new Course("Database Management", "SQL and relational database concepts", 3),
                new Course("Data Structures & Algorithms", "Core concepts for efficient programming", 4),
                new Course("Operating Systems", "Principles of modern operating systems", 3)
            );
            courses.forEach(course -> {
                if (courseRepository.findByTitle(course.getTitle()).isEmpty()) {
                    courseRepository.save(course);
                    logger.info("Saved course: {}", course.getTitle());
                } else {
                    logger.info("Course already exists: {}", course.getTitle());
                }
            });

            List<Course> existingCourses = courseRepository.findAll();
            Course course1 = existingCourses.stream().filter(c -> c.getTitle().equals("Introduction to Programming")).findFirst().orElse(null);
            Course course2 = existingCourses.stream().filter(c -> c.getTitle().equals("Web Development Basics")).findFirst().orElse(null);
            Course course3 = existingCourses.stream().filter(c -> c.getTitle().equals("Database Management")).findFirst().orElse(null);
            Course course4 = existingCourses.stream().filter(c -> c.getTitle().equals("Data Structures & Algorithms")).findFirst().orElse(null);
            Course course5 = existingCourses.stream().filter(c -> c.getTitle().equals("Operating Systems")).findFirst().orElse(null);


            List<Student> students = Arrays.asList(
                new Student("Riya Mishra", "riya.mishra@gmail.com"),
                new Student("Bob Hill", "bob.hill@gmail.com"),
                new Student("Charlie Brown", "charlie.brown@gmail.com"),
                new Student("Jack Hill", "jack.hill@gmail.com"),
                new Student("Peter Pan", "peter.pan@gmail.com"),
                new Student("Frank White", "frank.white@gmail.com"),
                new Student("Grace Lee", "grace.lee@gmail.com"),
                new Student("Gill Hill", "gill.hill@gmail.com"),
                new Student("Ivy Chen", "ivy.chen@gmail.com"),
                new Student("Jack Ryan", "jack.ryan@gmail.com"),
                new Student("Harry Potter", "karen.green@gmail.com"),
                new Student("Liam Davis", "liam.davis@gmail.com"),
                new Student("Jia Mathur", "jia.mathur@gmail.com"),
                new Student("Noah Taylor", "noah.taylor@gmail.com"),
                new Student("Olivia Moore", "olivia.moore@gmail.com")
            );
            students.forEach(student -> {
                if (studentRepository.findByEmail(student.getEmail()).isEmpty()) {
                    studentRepository.save(student);
                    logger.info("Saved student: {}", student.getName());
                } else {
                    logger.info("Student already exists: {}", student.getName());
                }
            });

            // Refresh students to ensure IDs are populated for relationships
            List<Student> existingStudents = studentRepository.findAll();
            Student student1 = existingStudents.stream().filter(s -> s.getEmail().equals("riya.mishra@gmail.com")).findFirst().orElse(null);
            Student student2 = existingStudents.stream().filter(s -> s.getEmail().equals("bob.hill@gmail.com")).findFirst().orElse(null);
            Student student3 = existingStudents.stream().filter(s -> s.getEmail().equals("charlie.brown@gmail.com")).findFirst().orElse(null);
            Student student4 = existingStudents.stream().filter(s -> s.getEmail().equals("jack.hill@gmail.com")).findFirst().orElse(null);
            Student student5 = existingStudents.stream().filter(s -> s.getEmail().equals("peter.pan@gmail.com")).findFirst().orElse(null);
            Student student6 = existingStudents.stream().filter(s -> s.getEmail().equals("frank.white@gmail.com")).findFirst().orElse(null);
            Student student7 = existingStudents.stream().filter(s -> s.getEmail().equals("grace.lee@gmail.com")).findFirst().orElse(null);
            Student student8 = existingStudents.stream().filter(s -> s.getEmail().equals("gill.hill@gmail.com")).findFirst().orElse(null);
            Student student9 = existingStudents.stream().filter(s -> s.getEmail().equals("ivy.chen@gmail.com")).findFirst().orElse(null);
            Student student10 = existingStudents.stream().filter(s -> s.getEmail().equals("jack.ryan@gmail.com")).findFirst().orElse(null);
            Student student11 = existingStudents.stream().filter(s -> s.getEmail().equals("harry.potter@gmail.com")).findFirst().orElse(null);
            Student student12 = existingStudents.stream().filter(s -> s.getEmail().equals("liam.davis@gmail.com")).findFirst().orElse(null);
            Student student13 = existingStudents.stream().filter(s -> s.getEmail().equals("jia.mathur@gmail.com")).findFirst().orElse(null);
            Student student14 = existingStudents.stream().filter(s -> s.getEmail().equals("noah.taylor@gmail.com")).findFirst().orElse(null);
            Student student15 = existingStudents.stream().filter(s -> s.getEmail().equals("olivia.moore@gmail.com")).findFirst().orElse(null);


            // 3. Create and Save StudentCourse enrollments
            // This needs existing student and course objects with IDs
            if (student1 != null && course1 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student1.getId(), course1.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student1, course1, LocalDate.of(2023, 9, 1), 85, 90));
            }
            if (student2 != null && course1 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student2.getId(), course1.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student2, course1, LocalDate.of(2023, 9, 1), 78, 85));
            }
            if (student3 != null && course2 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student3.getId(), course2.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student3, course2, LocalDate.of(2023, 9, 5), 92, 95));
            }
            if (student1 != null && course3 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student1.getId(), course3.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student1, course3, LocalDate.of(2023, 9, 10), 70, 75));
            }
            if (student4 != null && course2 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student4.getId(), course2.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student4, course2, LocalDate.of(2023, 9, 5), 88, 90));
            }
            if (student5 != null && course1 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student5.getId(), course1.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student5, course1, LocalDate.of(2023, 9, 1), 65, 80));
            }
            if (student6 != null && course3 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student6.getId(), course3.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student6, course3, LocalDate.of(2023, 9, 10), 80, 88));
            }
            if (student7 != null && course4 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student7.getId(), course4.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student7, course4, LocalDate.of(2023, 9, 15), 95, 98));
            }
            if (student8 != null && course5 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student8.getId(), course5.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student8, course5, LocalDate.of(2023, 9, 20), 72, 70));
            }
            if (student9 != null && course1 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student9.getId(), course1.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student9, course1, LocalDate.of(2023, 9, 1), 81, 92));
            }
            if (student10 != null && course2 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student10.getId(), course2.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student10, course2, LocalDate.of(2023, 9, 5), 79, 87));
            }
            if (student11 != null && course3 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student11.getId(), course3.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student11, course3, LocalDate.of(2023, 9, 10), 88, 91));
            }
            if (student12 != null && course4 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student12.getId(), course4.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student12, course4, LocalDate.of(2023, 9, 15), 68, 77));
            }
            if (student13 != null && course5 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student13.getId(), course5.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student13, course5, LocalDate.of(2023, 9, 20), 90, 93));
            }
            if (student14 != null && course1 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student14.getId(), course1.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student14, course1, LocalDate.of(2023, 9, 1), 75, 80));
            }
            if (student15 != null && course2 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student15.getId(), course2.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student15, course2, LocalDate.of(2023, 9, 5), 83, 89));
            }
            if (student1 != null && course4 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student1.getId(), course4.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student1, course4, LocalDate.of(2023, 10, 1), 80, 85));
            }
            if (student2 != null && course3 != null &&
                studentCourseRepository.findByStudentIdAndCourseId(student2.getId(), course3.getId()).isEmpty()) {
                studentCourseRepository.save(new StudentCourse(student2, course3, LocalDate.of(2023, 10, 5), 70, 72));
            }

            logger.info("Data pre-population completed.");
        };
    }
}