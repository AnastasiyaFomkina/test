package com.fomkina.test.dao;

import com.fomkina.test.domain.Box;
import com.fomkina.test.dto.ElementsDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 12:14
 */
@Repository
public class BoxDao {

    private EntityManager entityManager;

    public BoxDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Box box) {
        entityManager.persist(box);
    }

    @NotNull
    public Collection<Box> getAll() {
        return entityManager.createQuery("select m from Box m order by m.id asc")
                .getResultList();
    }

    public Collection<Box> getBoxesByElementsDto(ElementsDto elementsDto) {
        return entityManager.createQuery("select m from Box m where m.containedIn = :containedIn")
                .setParameter("containedIn", elementsDto.getBoxId())
                .getResultList();
    }

    public Collection<Box> getBoxesByContainedIn(Long containedIn) {
        return entityManager.createQuery("select m from Box m where m.containedIn = :containedIn")
                .setParameter("containedIn", containedIn)
                .getResultList();
    }
}
