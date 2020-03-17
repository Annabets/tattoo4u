package by.bsuir.tattoo4u.dto.request;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
