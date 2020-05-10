package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.MasterComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterCommentRepository extends JpaRepository<MasterComment,Long> {

    List<MasterComment> getAllByMasterAndRatingNotNull(Master master);
    Page<MasterComment> getAllByMaster(Pageable pageable, Master master);
}
