package com.fomkina.test.web;

import com.fomkina.test.domain.Box;
import com.fomkina.test.domain.Item;
import com.fomkina.test.dto.ElementsDto;
import com.fomkina.test.service.BoxService;
import com.fomkina.test.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 14:17
 */
@RestController
@RequestMapping
public class Controller {
    @Autowired
    private BoxService boxService;
    @Autowired
    private ItemService itemService;

    @GetMapping("/boxes")
    public Collection<Box> getBoxes() {
        return boxService.getAll();
    }

    @GetMapping("/items")
    public Collection<Item> getItems() {
        return itemService.getAll();
    }

    @PostMapping("/elements")
    public Collection<Long> getElements(@RequestBody ElementsDto elementsDto) {
        Collection<Box> boxes = boxService.getBoxes(elementsDto);
        return itemService.getItemIds(elementsDto, boxes);
    }
}