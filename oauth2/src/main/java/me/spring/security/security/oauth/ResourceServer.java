package me.spring.security.security.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResourceServer {

    KAKAO("id", "profile_image"),
    NAVER("id", null),
    GOOGLE("sub", null);

    private final String nameAttribute;
    private final String thumbnail;

    public static ResourceServer getResourceServer(String resourceServerName) {
        switch (resourceServerName) {
            case "kakao":
                return KAKAO;
            default:
                throw new RuntimeException();
        }
    }

}
