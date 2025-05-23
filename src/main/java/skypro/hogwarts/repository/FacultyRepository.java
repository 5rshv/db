package skypro.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skypro.hogwarts.model.Faculty;
import skypro.hogwarts.model.Student;

import java.util.Collection;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findFacultyByColorIgnoreCase(String color);
}
