package com.szabodev.demo.dao;

import com.szabodev.demo.model.Developer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DeveloperCustomRepositoryImpl implements DeveloperCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Developer> findByDeveloperCriteria(DeveloperFilter developerFilter) {

        Session session = em.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Developer> criteria = builder.createQuery(Developer.class);
        Root<Developer> developer = criteria.from(Developer.class);
        List<Predicate> predicates = new ArrayList<>();

        if (developerFilter.getFirstName() != null && !"".equals((developerFilter.getFirstName()))) {
            predicates.add(builder.like(builder.lower(developer.get("firstName")), "%" + developerFilter.getFirstName().toLowerCase() + "%"));
        }

        if (developerFilter.getLastName() != null && !"".equals(developerFilter.getLastName())) {
            predicates.add(builder.like(builder.lower(developer.get("lastName")), "%" + developerFilter.getLastName().toLowerCase() + "%"));
        }

        criteria.where(predicates.toArray(new Predicate[predicates.size()]));
        Query<Developer> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
