package by.bsuir.tattoo4u.security.jwt;

import by.bsuir.tattoo4u.entity.Role;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                true,
                mapToGrantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        for (Role role : userRoles) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorityList;
    }
}
