package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.MasterComment;
import by.bsuir.tattoo4u.repository.MasterCommentRepository;
import by.bsuir.tattoo4u.repository.MasterRepository;
import by.bsuir.tattoo4u.service.MasterCommentService;
import by.bsuir.tattoo4u.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class MasterCommentServiceImpl implements MasterCommentService {

    private MasterCommentRepository masterCommentRepository;
    private MasterRepository masterRepository;

    @Autowired
    public MasterCommentServiceImpl(MasterCommentRepository masterCommentRepository, MasterRepository masterRepository) {
        this.masterCommentRepository = masterCommentRepository;
        this.masterRepository = masterRepository;
    }

    @Override
    public MasterComment add(MasterComment masterComment) {
        MasterComment save = masterCommentRepository.save(masterComment);

        Double rating=masterComment.getRating();
        if(rating!=null){
            Master master=masterComment.getMaster();
            List<MasterComment> masterComments=masterCommentRepository.getAllByMasterAndRatingNotNull(master);

            Double amountRating=0.0;
            for(MasterComment element:masterComments){
                amountRating+=element.getRating();
            }

            Double newRating=amountRating/masterComments.size();
            master.setRating(newRating);

            masterRepository.save(master);
        }

        return save;
    }

    @Override
    public List<MasterComment> getByMaster(Master master, Pageable pageable) {
        List<MasterComment> masterComments= new LinkedList<>();
        for (MasterComment masterComment:masterCommentRepository.getAllByMaster(pageable, master)){
            masterComments.add(masterComment);
        }
        return masterComments;
    }
}
