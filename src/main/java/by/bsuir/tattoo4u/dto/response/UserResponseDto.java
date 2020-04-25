package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.User;
import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private String photo;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.photo = user.getPhoto()!=null?user.getPhoto().getUrl():null;
    }

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);

        return user;
    }

    public static UserResponseDto fromUser(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhoto(user.getPhoto().getUrl());

        return userDto;
    }
}

