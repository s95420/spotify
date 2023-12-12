package com.bialy.spotifydatarestapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bialy.spotifydatarestapi.payload.request.LoginRequest;
import com.bialy.spotifydatarestapi.payload.request.SignupRequest;
import com.bialy.spotifydatarestapi.payload.resposne.JwtResponse;
import com.bialy.spotifydatarestapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class SpotifyDataRestApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder encoder;

    @Test
    void testRegisterUser() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testUser");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("testPassword");
        signupRequest.setRoles(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/backend/src/main/java/com/bialy/sportifydatarestapi/payload/request/signuprequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signupRequest)));

        assertEquals(1, userRepository.count());
    }

    @Test
    void testAuthenticateUser() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testUser");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword(encoder.encode("testPassword"));
        signupRequest.setRoles(null);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPassword");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/backend/src/main/java/com/bialy/sportifydatarestapi/payload/request/LoginRequest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andReturn();

        JwtResponse jwtResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), JwtResponse.class);

        assertEquals("testUser", jwtResponse.getUsername());
    }
}

