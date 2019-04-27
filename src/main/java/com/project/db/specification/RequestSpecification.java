package com.project.db.specification;

import com.project.db.entity.Request;
import com.project.db.specification.constant.FieldName;
import com.project.db.specification.util.SearchSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class RequestSpecification {

    public Specification<Request> findBySearchQuery(String searchQuery){
        return (root, query, builder) -> builder.and(getPredicateBySearchQuery(searchQuery, root, query, builder)
                                                        .orElse(builder.conjunction()));
    }

    private Optional<Predicate> getPredicateBySearchQuery(String searchQuery, Root<Request> root,
                                                          CriteriaQuery criteriaQuery, CriteriaBuilder builder) {
        return Optional
                .ofNullable(searchQuery)
                .map(this::findFittingRequestsBySearchQuery)
                .map(requestSpecification -> requestSpecification.toPredicate(root, criteriaQuery, builder));
    }

    private Specification<Request> findFittingRequestsBySearchQuery(String searchQuery) {
        return (root, query, builder) -> builder.and(builder.or(SearchSpecification
                                                        .likeByField(searchQuery,
                                                                builder,
                                                                root.get(FieldName.TO),
                                                                root.get(FieldName.FROM))));
    }

}
