package skypro.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import skypro.hogwarts.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findStudentsByAgeBetween(int min, int max);

    Collection<Student> findStudentByNameIgnoreCase(String name);

    @Query(value = "select count(id) from student", nativeQuery = true)
    Collection<Integer>getStudentsId();

    @Query(value = "select round(avg(age)) from student;", nativeQuery = true)
    Collection<Integer> getStudentsAgeAverage();

    @Query(value = "select * from student order by id desc limit 5;", nativeQuery = true)
    Collection<Integer>getStudentsLastFive();
}

