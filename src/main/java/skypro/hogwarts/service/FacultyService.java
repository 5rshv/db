package skypro.hogwarts.service;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import skypro.hogwarts.model.Faculty;
import skypro.hogwarts.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;

@Service
public class FacultyService {
    private  FacultyRepository facultyRepository;
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("createFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        logger.info("getFaculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty editeFaculty(Faculty faculty) {
        logger.info("editeFaculty");
        Faculty existing = facultyRepository.findById(faculty.getId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));

        existing.setName(existing.getName());
        existing.setColor(existing.getColor());

        return facultyRepository.save(existing);
    }

    public void deleteFaculty(Long id) {
        logger.info("deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("getAllFaculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        logger.info("getFacultyByColor");
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }

    public String getLongFaculties() {
        logger.info("getLongFaculties");
        return facultyRepository.findAll().stream()
                .map(Faculty :: getName)
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
}
}