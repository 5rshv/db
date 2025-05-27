package skypro.hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import skypro.hogwarts.model.Student;
import skypro.hogwarts.repository.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.LongStream;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("createStudent");
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        logger.info("getStudent");
        return studentRepository.findById(id).get();
    }

    public Student editeStudent(Student student) {
        logger.info("editeStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("deleteStudent");
        studentRepository.deleteById(id);
    }


    public Collection<Student> getAllStudents() {
        logger.info("getAllStudents");
        return studentRepository.findAll();
    }

    public Collection<Student> getAllStudentsByNameA() {
        logger.info("getAllStudentsByNameA");
        return studentRepository.findAll().stream()
                .filter(s -> s.getName().toLowerCase()
                        .startsWith("a"))
                .sorted(Comparator.comparing(Student::getName))
                .toList();
    }


    public Collection<Student> getStudentsByAge(int age) {
        logger.info("getStudentsByAge");
        return studentRepository.findAll().stream().filter(s -> s.getAge() == age).toList();
    }

    public Collection<Student> getStudentsByAgeBetween(int min, int max) {
        logger.info("getStudentsByAgeBetween");
        return studentRepository.findStudentsByAgeBetween(min, max);
    }

    public Collection<Student> getStundentByName(String name) {
        logger.info("getStundentByName");
        return studentRepository.findStudentByNameIgnoreCase(name);
    }

    public double getStudentsAgeAverage() {
        logger.info("getStudentsAgeAverage");
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    public Collection<Integer> getStudentsId() {
        logger.info("getStudentsId");
        return studentRepository.getStudentsId();
    }

    public Collection<Integer> getStudentsLastFive() {
        logger.info("getStudentsLastFive");
        return studentRepository.getStudentsLastFive();
    }

    public Long getLongIntTest1() {
        long start = System.nanoTime();
        Long result = LongStream.rangeClosed(1, 1_000_000)
                .parallel()
                .sum();
        long finish = System.nanoTime();
        long informationTime = finish - start;
        logger.info("information time test 1: start{} , finish{}, informationTime{}", start, finish, informationTime);
        return result;
    }

    public Long getLongIntTest2() {
        long start = System.nanoTime();
        Long result = ((1L + 1_000_000L) * 1_000_000L / 2);
        long finish = System.nanoTime();
        long informationTime = finish - start;
        logger.info("information time 2: start{} , finish{}, informationTime{}", start, finish, informationTime);
        return result;
    }

    public Long getLongIntTest3() {
        long start = System.nanoTime();
        Long result = LongStream.rangeClosed(1, 1_000_000)
                .sum();
        long finish = System.nanoTime();
        long informationTime = finish - start;
        logger.info("information time test 3: start{} , finish{}, informationTime{}", start, finish, informationTime);
        return result;
    }
}