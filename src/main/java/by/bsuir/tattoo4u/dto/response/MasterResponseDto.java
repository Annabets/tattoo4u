package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.MasterComment;
import by.bsuir.tattoo4u.entity.Post;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
public class MasterResponseDto {

    private String id;
    private String username;
    private String email;
    private String rating;
    private String photo;
    private List<PostResponseDto> posts;
    private List<MasterCommentResponseDto> lastComments;

    public MasterResponseDto(Master master) {
        this.id = master.getUser().getId().toString();
        this.username = master.getUser().getUsername();
        this.email = master.getUser().getEmail();
        this.rating = master.getRating().toString();
        this.photo = master.getUser().getPhoto()!=null?master.getUser().getPhoto().getUrl():null;
        Set<Post> postSet=master.getUser().getPosts();
        if(postSet!=null){
            List<Post> postList= new ArrayList<>(postSet);

            Collections.sort(postList);

            if(postList.size()>5){
                postList.removeAll(postList.subList(0, postList.size()-5));
            }

            List<PostResponseDto> postResponseDtos=new ArrayList<>();
            for (Post post:postList){
                postResponseDtos.add(new PostResponseDto(post));
            }
            this.posts=postResponseDtos;
        }

        Set<MasterComment> masterCommentSet=master.getCommentsToMaster();
        if(masterCommentSet!=null){
            List<MasterComment> masterCommentsList= new ArrayList<>(masterCommentSet);

            Collections.sort(masterCommentsList);

            if(masterCommentsList.size()>3){
                masterCommentsList.removeAll(masterCommentsList.subList(0, masterCommentsList.size()-3));
            }

            List<MasterCommentResponseDto> masterCommentResponseDtos=new ArrayList<>();
            for (MasterComment masterComment:masterCommentsList){
                masterCommentResponseDtos.add(new MasterCommentResponseDto(masterComment));
            }
            this.lastComments=masterCommentResponseDtos;
        }
    }
}
