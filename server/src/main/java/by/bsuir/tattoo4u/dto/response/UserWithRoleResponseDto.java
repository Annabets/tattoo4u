package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.User;
import lombok.Data;

@Data
public class UserWithRoleResponseDto {
    private Long id;
    private String username;
    private String email;
    private String role;
    private String photo;
    private boolean isBanned;

    public UserWithRoleResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRoles().get(0).getName();
        this.photo = user.getPhoto()!=null?user.getPhoto().getUrl():null;
        this.isBanned =user.isBanned();
    }
}
