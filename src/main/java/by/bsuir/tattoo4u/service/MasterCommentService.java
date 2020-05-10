package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.MasterComment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MasterCommentService {

    MasterComment add(MasterComment masterComment);

    List<MasterComment> getByMaster(Master master, Pageable pageable);
}
