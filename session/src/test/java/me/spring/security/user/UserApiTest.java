package me.spring.security.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "    \"name\": \"userName\"," +
                                "    \"password\": \"userPassword\"," +
                                "    \"email\": \"userEmail\"" +
                                "}")
                )
                .andExpect(status().isCreated());
    }

    @Test
    void registerUser_callsRegisterUser_inUserService() throws Exception {
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "    \"name\": \"userName\"," +
                        "    \"password\": \"userPassword\"," +
                        "    \"email\": \"userEmail\"" +
                        "}")
        );

        RegisterUserRequest registerUser_arguments = new RegisterUserRequest(
                "userName",
                "userPassword",
                "userEmail"
        );
        verify(spyUserService).registerUser(registerUser_arguments);
    }

    @Test
    void getUsers_status_isForbidden_when_notAdmin() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getUsers_status_isOk_when_admin() throws Exception {
        given(spyUserService.getUsers()).willReturn(List.of(
                new GetUser(1, "name1", "email1"),
                new GetUser(2, "name2", "email2"),
                new GetUser(3, "name3", "email3")
        ));
        mockMvc.perform(get("/users"))
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("name2"))
                .andExpect(jsonPath("$[1].email").value("email2"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getUsers_callsGetUsers_inUserService_when_admin() throws Exception {
        mockMvc.perform(get("/users"));
        verify(spyUserService).getUsers();
    }

}
