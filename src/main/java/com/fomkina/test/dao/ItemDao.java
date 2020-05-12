package com.fomkina.test.dao;

import com.fomkina.test.domain.Item;
import com.fomkina.test.dto.ElementsDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 12:21
 */
@Repository
public class ItemDao {

    private EntityManager entityManager;

    public ItemDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Item item) {
        entityManager.persist(item);
    }

    public Collection<Item> getAll() {
        return entityManager.createQuery("select m from Item m order by m.id asc")
                .getResultList();
    }

    public Collection<Long> getItemsByElementsDto(ElementsDto elementsDto) {
        return entityManager.createQuery("select m.id from Item m where m.containedIn = :containedIn and m.color =:color")
                .setParameter("containedIn", elementsDto.getBoxId())
                .setParameter("color", elementsDto.getColor())
                .getResultList();
    }

    public Collection<Long> getItemsIdByContainedInAndColor(Long containedIn, String color) {
        return entityManager.createQuery("select m.id from Item m where m.containedIn = :containedIn and m.color =:color")
                .setParameter("containedIn", containedIn)
                .setParameter("color", color)
                .getResultList();
    }
}
