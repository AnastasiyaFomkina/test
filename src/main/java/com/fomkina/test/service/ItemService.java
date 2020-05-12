package com.fomkina.test.service;

import com.fomkina.test.dao.ItemDao;
import com.fomkina.test.domain.Box;
import com.fomkina.test.domain.Item;
import com.fomkina.test.dto.ElementsDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 13:27
 */
@Service
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    @Transactional
    public void create(Long id, Long containedIn, String color) {

        Item item = new Item();
        item.setId(id);
        item.setContainedIn(containedIn);
        item.setColor(color);
        itemDao.create(item);
    }

    @Transactional
    public void createWithoutColor(Long id, Long containedIn) {
        Item item = new Item();
        item.setId(id);
        item.setContainedIn(containedIn);
        item.setColor(null);
        itemDao.create(item);
    }

    @NotNull
    @Transactional
    public Collection<Item> getAll() {
        return itemDao.getAll();
    }

    private Collection<Long> getItemsIdByElementsDto(ElementsDto elementsDto) {
        return itemDao.getItemsByElementsDto(elementsDto);
    }

    private Collection<Long> getItemsIdByContainedInAndColor(Long containedIn, String color) {
        return itemDao.getItemsIdByContainedInAndColor(containedIn, color);
    }

    public Collection<Long> getItemIds(ElementsDto elementsDto, Collection<Box> boxes) {
        Collection<Long> itemsId = new ArrayList<>(getItemsIdByElementsDto(elementsDto));
        boxes.stream().map(Box::getId).map(id -> getItemsIdByContainedInAndColor(id, elementsDto.getColor())).forEach(itemsId::addAll);
        return itemsId;
    }
}
