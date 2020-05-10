package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.User;
import lombok.Data;

@Data
public class AuthenticationResponseDto {

    private Long id;
    private String username;
    private String token;
    private String email;
    private String role;
    private String photo;
    private boolean isBanned;

    public static AuthenticationResponseDto fromUserAndToken(User user, String token) {
        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
        authenticationResponseDto.setUsername(user.getUsername());
        authenticationResponseDto.setToken(token);
        authenticationResponseDto.setId(user.getId());
        authenticationResponseDto.setEmail(user.getEmail());
        authenticationResponseDto.setRole(user.getRoles().get(0).getName());
        authenticationResponseDto.setPhoto((user.getPhoto()==null)?null:user.getPhoto().getUrl());
        authenticationResponseDto.setBanned(user.isBanned());

        return authenticationResponseDto;
    }
}
