package com.fomkina.test.service;

import com.fomkina.test.dao.BoxDao;
import com.fomkina.test.domain.Box;
import com.fomkina.test.dto.ElementsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 13:03
 */
@Service
public class BoxService {

    @Autowired
    private BoxDao boxDao;

    @Transactional
    public void create(Long id, Long containedIn) {
        Box box = new Box();
        box.setId(id);
        box.setContainedIn(containedIn);
        boxDao.create(box);
    }

    @Transactional
    public Collection<Box> getAll() {
        return boxDao.getAll();
    }

    public Collection<Box> getBoxesByElementsDto(ElementsDto elementsDto) {
        return boxDao.getBoxesByElementsDto(elementsDto);
    }

    public Collection<Box> getBoxesByContainedIn(Long containedIn) {
        return boxDao.getBoxesByContainedIn(containedIn);
    }

    public Collection<Box> getBoxes(ElementsDto elementsDto) {
        Collection<Box> allBoxes = new ArrayList<>(getBoxesByElementsDto(elementsDto));
        getBoxesByElementsDto(elementsDto).stream().map(box -> getBoxesByContainedIn(box.getId())).forEach(allBoxes::addAll);
        return allBoxes;
    }
}
