package com.game.gooseapi.controllers;

import com.game.gooseapi.models.Goose;
import com.game.gooseapi.models.Hat;
import com.game.gooseapi.models.Sessions;
import com.game.gooseapi.repositories.GooseRepository;
import com.game.gooseapi.repositories.HatRepository;
import com.game.gooseapi.repositories.SessionRepository;
import com.game.gooseapi.response.MyResponseObject;
import com.game.gooseapi.response.OperationStatus;
import com.game.gooseapi.services.GooseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GooseControllerTest {
    @Mock
    private GooseRepository gooseRepository;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private HatRepository hatRepository;
    @Mock
    private GooseService gooseService;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private GooseController gooseController;


    @Test
    public void wearHatReturnsFailed() {
        String sessionName = "session";
        String gooseName = "Goose";
        int hatId = 2;
        when(sessionRepository.findBySessionName(sessionName)).thenReturn(null);

        OperationStatus actualOperationStatus = gooseController.wearHat(sessionName, gooseName, hatId);

        assertEquals(OperationStatus.FAILED, actualOperationStatus);
    }


    @Test
    public void wearHatReturnsNotFoundObject() {
        String sessionName = "session";
        Sessions sessions = new Sessions(sessionName);
        String gooseName = "Goose";
        int hatId = 2;
        when(sessionRepository.findBySessionName(sessionName)).thenReturn(sessions);
        when(gooseRepository.findByName(gooseName)).thenReturn(new Goose(1l, gooseName,
                new Hat(1, "no hat", 0, 0, 0),
                new Sessions("some other session")));

        OperationStatus actualOperationStatus = gooseController.wearHat(sessionName, gooseName, hatId);

        assertEquals(OperationStatus.NOT_FOUND_OBJECT, actualOperationStatus);
    }


    @Test
    public void wearHatReturnsSuccessfulOperation() {
        String sessionName = "session";
        Sessions sessions = new Sessions(sessionName);
        String gooseName = "Goose";
        int hatId = 2;
        Hat hat = new Hat(2, "newHat", 1, 1, 1);

        when(sessionRepository.findBySessionName(sessionName)).thenReturn(sessions);
        when(gooseRepository.findByName(gooseName)).thenReturn(new Goose(1l, gooseName,
                new Hat(1, "no hat", 0, 0, 0), sessions));
        when(hatRepository.findHatById(hatId)).thenReturn(hat);

        OperationStatus actualOperationStatus = gooseController.wearHat(sessionName, gooseName, hatId);

        assertEquals(OperationStatus.SUCCESSFUL_OPERATION, actualOperationStatus);
    }


}