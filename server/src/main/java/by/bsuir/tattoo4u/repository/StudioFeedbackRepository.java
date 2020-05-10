package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.StudioFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudioFeedbackRepository extends JpaRepository<StudioFeedback, Long> {
    List<StudioFeedback> findAllByStudio(Studio studio);

    @Query(nativeQuery = true, value = "SELECT AVG(rating) FROM studio_feedback WHERE studio_id IN (:studio)")
    Double avg(@Param("studio") Long studio);
}
