package com.game.gooseapi.controllers;

import com.game.gooseapi.models.Sessions;
import com.game.gooseapi.repositories.SessionRepository;
import com.game.gooseapi.response.OperationStatus;
import jakarta.inject.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;


//@SpringBootTest
//@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class SessionControllerTest {
    @Mock
    private SessionRepository sessionRepository;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private SessionController sessionController;

    @Test
    void contextLoads() throws Exception {
        assertThat(sessionController).isNotNull();
    }

    @Test
    public void testYourControllerMethod() throws Exception {
        mockMvc.perform(get("/sessions"))
                .andExpect(status().isOk());
    }


//    @Test
//    public void testCreateNewSession() throws Exception {
//        String sessionName = "Fluffy1";
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/session/{sessionName}", sessionName)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("SAVED")).andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//
//    @Test
//    public void testCreateAlreadyExistSession() throws Exception {
//        String sessionName = "s";
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/session/{sessionName}", sessionName)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("ALREADY_EXIST")).andExpect(MockMvcResultMatchers.status().isOk());
//    }


    @Test
    public void testCreateNewSession() {
        String name = "NewSession";
        when(sessionRepository.findBySessionName(name)).thenReturn(null);

        OperationStatus operationStatus = sessionController.createNewSession(name);

        assertEquals(OperationStatus.SAVED, operationStatus);
        verify(sessionRepository, times(1)).save(any(Sessions.class));
    }


    @Test
    public void testCreateSessionWithExistingName() {
        String name = "NewSession";
        when(sessionRepository.findBySessionName(name)).thenReturn(new Sessions());

        OperationStatus operationStatus = sessionController.createNewSession(name);

        assertEquals(OperationStatus.ALREADY_EXIST, operationStatus);
        verify(sessionRepository, never()).save(any(Sessions.class));
    }
}