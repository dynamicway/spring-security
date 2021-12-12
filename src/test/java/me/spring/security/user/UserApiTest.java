package me.spring.security.user;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserApiTest {

    private final UserApi userApi = new UserApi();
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userApi)
            .build();

    @Test
    void registerUser_status_isCreated() throws Exception {
        mockMvc.perform(post("/users"))
                .andExpect(status().isCreated());
    }
}
