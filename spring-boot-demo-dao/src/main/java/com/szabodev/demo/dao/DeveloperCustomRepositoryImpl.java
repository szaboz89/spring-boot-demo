package com.szabodev.demo.dao;

import com.szabodev.demo.model.Developer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DeveloperCustomRepositoryImpl implements DeveloperCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Developer> findByDeveloperCriteria(DeveloperFilter developerFilter) {

        Criteria cr = em.unwrap(Session.class).createCriteria(Developer.class);

        if (developerFilter.getFirstName() != null && !"".equals((developerFilter.getFirstName()))) {
            cr.add(Restrictions.like("firstName", "%" + (developerFilter.getFirstName() + "%")));
        }

        if (developerFilter.getLastName() != null && !"".equals(developerFilter.getLastName())) {
            cr.add(Restrictions.eq("lastName", developerFilter.getLastName()));
        }
        return cr.list();
    }
}
