package by.bsuir.tattoo4u.security.jwt;

import by.bsuir.tattoo4u.service.TokenService;
import by.bsuir.tattoo4u.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private final static Logger log = LogManager.getLogger(JwtTokenFilter.class);

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, TokenService tokenService, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);

        try {
            if (token != null && jwtTokenProvider.isValidToken(token) && tokenService.exists(token) && !userService.isBanned(tokenService.getUsername(token))) {

                Authentication authentication = null;

                authentication = jwtTokenProvider.getAuthentication(token);

                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e) {
            //write logger for uncorrect jwt
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
