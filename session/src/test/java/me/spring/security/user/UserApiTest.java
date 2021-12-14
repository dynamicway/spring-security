package me.spring.security.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {UserSecurityConfig.class, UserApi.class})
@WebAppConfiguration
class UserApiTest {

    private final MockMvc mockMvc;

    @MockBean
    private UserServiceImpl spyUserService;


    public UserApiTest(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void registerUser_status_isCreated() throws Exception {
        mockMvc.perform(post("/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"userName\",\n" +
                                "    \"password\": \"userPassword\",\n" +
                                "    \"email\": \"userEmail\"\n" +
                                "}")
                )
                .andExpect(status().isCreated());
    }

    @Test
    void registerUser_callsRegisterUser_inUserService() throws Exception {
        mockMvc.perform(post("/users")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"userName\",\n" +
                        "    \"password\": \"userPassword\",\n" +
                        "    \"email\": \"userEmail\"\n" +
                        "}")
        );

        RegisterUserRequest registerUser_arguments = new RegisterUserRequest(
                "userName",
                "userPassword",
                "userEmail"
        );
        verify(spyUserService).registerUser(registerUser_arguments);
    }

}
