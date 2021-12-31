package me.spring.security.security.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final DefaultOAuth2UserService defaultOAuth2UserService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // db를 조회하여 정보에 맞는 defaultOAuth2User 를 반환
        // 유저가 회원가입이 돼있지 않다면 회원 가입 (권한은 일반 유저)
        // attribute에 resource_server_id, resource_server_name, profile_image 를 넣어줌
        // 위를 기반으로 DefaultOAuth2User 생성
//        Map<String, Object> attributes = new DefaultOAuth2UserService().loadUser(userRequest).getAttributes();
        return null;
    }


}
