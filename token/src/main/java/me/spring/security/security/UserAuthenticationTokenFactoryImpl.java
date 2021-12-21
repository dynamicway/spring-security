package me.spring.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserAuthenticationTokenFactoryImpl implements UserAuthenticationTokenFactory {

    private final ObjectMapper objectMapper;

    @Override
    public UserAuthenticationToken of(HttpServletRequest httpServletRequest) {
        final UserAuthenticationToken userAuthenticationToken;
        try {
            userAuthenticationToken = objectMapper.readValue(httpServletRequest.getInputStream(), UserAuthenticationToken.class);
            return userAuthenticationToken;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
