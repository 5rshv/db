package skypro.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skypro.hogwarts.model.Avatar;

import java.util.Optional;

public interface AvaterRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
}
