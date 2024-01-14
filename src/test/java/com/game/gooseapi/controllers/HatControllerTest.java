package com.game.gooseapi.controllers;

import com.game.gooseapi.models.Hat;
import com.game.gooseapi.models.Sessions;
import com.game.gooseapi.repositories.HatRepository;
import com.game.gooseapi.repositories.SessionRepository;
import com.game.gooseapi.response.MyResponseObject;
import com.game.gooseapi.response.OperationStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class HatControllerTest {
    @Mock
    private HatRepository hatRepository;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private HatController hatController;


    @Test
    public void getAllHatsWhichNamesContainsReturnsHatSet() {
        ArrayList<Hat> hats = new ArrayList<>();
        hats.add(new Hat(1, "go hat", 1, 1, 1));
        hats.add(new Hat(2, "aaa", 1, 1, 1));
        hats.add(new Hat(3, "some hat", 1, 1, 1));
        when(hatRepository.findAll()).thenReturn(hats);

        ArrayList<Hat> expectedSetHat = new ArrayList<>();
        expectedSetHat.add(new Hat(1, "go hat", 1, 1, 1));
        expectedSetHat.add(new Hat(3, "some hat", 1, 1, 1));

        MyResponseObject<ArrayList<Hat>> expectedResponseObject =
                new MyResponseObject<>(expectedSetHat, OperationStatus.SUCCESSFUL_OPERATION);

        MyResponseObject<ArrayList<Hat>> actualResponseObject = hatController.getAllHatsByString("hat");

        assertIterableEquals(expectedResponseObject.getResponseObject(), actualResponseObject.getResponseObject());
        assertEquals(expectedResponseObject.getStatus(), actualResponseObject.getStatus());

    }


    @Test
    public void getAllHatsWhichNamesContainsReturnsNotFoundObject() {
        ArrayList<Hat> hats = new ArrayList<>();
        hats.add(new Hat(1, "dddd", 1, 1, 1));
        hats.add(new Hat(2, "aaa", 1, 1, 1));
        Iterable<Hat> rialSetHat = hats;
        when(hatRepository.findAll()).thenReturn(rialSetHat);

        MyResponseObject<ArrayList<Hat>> actualResponseObject = hatController.getAllHatsByString("hat");

        assertEquals(OperationStatus.NOT_FOUND_OBJECT, actualResponseObject.getStatus());
    }


}