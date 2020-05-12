package com.fomkina.test.web;

import com.fomkina.test.domain.Box;
import com.fomkina.test.domain.Item;
import com.fomkina.test.dto.ElementsDto;
import com.fomkina.test.service.BoxService;
import com.fomkina.test.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 15:17
 */
@ExtendWith(MockitoExtension.class)
class ControllerTest {
    @InjectMocks
    private Controller controller;
    @Mock
    private BoxService boxService;
    @Mock
    private ItemService itemService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(print())
                .build();
    }

    @Test
    void expectGetBoxes() throws Exception {
        Box box = new Box();
        box.setId(1L);
        box.setContainedIn(null);

        when(boxService.getAll()).thenReturn(Collections.singletonList(box));

        mockMvc.perform(get("/boxes"))
                .andExpect(status().isOk());
    }

    @Test
    void expectGetItems() throws Exception {
        Item item = new Item();
        item.setId(1L);
        item.setContainedIn(1L);
        item.setColor("red");

        when(itemService.getAll()).thenReturn(Collections.singletonList(item));

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk());
    }

    @Test
    void expectGetElements() throws Exception {
        ElementsDto elementsDto = new ElementsDto();
        elementsDto.setBoxId(1L);
        elementsDto.setColor("red");

        Box box = new Box();
        box.setId(1L);
        box.setContainedIn(null);

        when(boxService.getBoxes(elementsDto)).thenReturn(Collections.singletonList(box));
        when(itemService.getItemIds(elementsDto, Collections.singletonList(box))).thenReturn(Collections.singletonList(1L));

        mockMvc.perform(post("/elements").content("{\"box\":\"1\",\"color\":\"red\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}