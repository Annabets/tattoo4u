package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.Post;
import lombok.Data;

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

    public MasterResponseDto(Master master) {
        this.id = master.getUser().getId().toString();
        this.username = master.getUser().getUsername();
        this.email = master.getUser().getEmail();
        this.rating = master.getRating().toString();
        this.photo = master.getUser().getPhoto()!=null?master.getUser().getPhoto().getUrl():null;
        Set<Post> postSet=master.getUser().getPosts();
        if(postSet!=null){
            List<Post> postList= new ArrayList<>(postSet);

            if(postList.size()>5){
                postList.removeAll(postList.subList(0, postList.size()-6));
            }

            List<PostResponseDto> postResponseDtos=new ArrayList<>();
            for (Post post:postList){
                postResponseDtos.add(new PostResponseDto(post));
            }
            this.posts=postResponseDtos;
        }
    }
}
