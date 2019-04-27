package com.project.db.specification;

import com.project.db.entity.Journey;
import com.project.db.entity.User;
import com.project.db.specification.constant.FieldName;
import com.project.db.specification.util.SearchSpecification;
import com.project.web.dto.JourneySearchDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class JourneySpecification {



    public Specification<Journey> findByCondition(JourneySearchDto dto) {
        return (root, query, builder) -> builder.and(getPredicateBySearchParams(dto, root, query, builder)
                                                        .orElse(builder.conjunction()));
    }

    private Optional<Predicate> getPredicateBySearchParams(JourneySearchDto dto, Root<Journey> root,
                                                           CriteriaQuery query, CriteriaBuilder builder) {
        return Optional
                .of(dto)
                .map(this::findFittingJourneyBySearchParams)
                .map(journeySpecification -> journeySpecification.toPredicate(root, query, builder));
    }

    private Specification<Journey> findFittingJourneyBySearchParams(JourneySearchDto dto) {
        return (root, query, cb) -> cb.and(cb.and(SearchSpecification
                                                .equalsByJourneyStatus(dto.getStatus(),
                                                                cb,
                                                                root.get(FieldName.STATUS))),
                                          cb.and(cb.or(SearchSpecification
                                                    .likeByField(dto.getSearchQuery(),
                                                                cb,
                                                                root.get(FieldName.TO),
                                                                root.get(FieldName.FROM)))));
    }

    public Specification<Journey> findByConditionAndDriver(JourneySearchDto searchDto, User user) {
        return (root, query, builder) -> builder.and(builder.and(getPredicateByDriver(root, user, query,builder)
                                                            .orElse(builder.conjunction())),
                                                    builder.and(getPredicateBySearchParams(searchDto, root, query, builder)
                                                            .orElse(builder.conjunction())));
    }

    private Optional<Predicate> getPredicateByDriver(Root<Journey> root, User driver,
                                                     CriteriaQuery query, CriteriaBuilder builder) {
        return Optional
                .of(driver)
                .map(this::findFittingJourneyByDriver)
                .map(journeySpecification -> journeySpecification.toPredicate(root, query, builder));
    }

    private Specification<Journey> findFittingJourneyByDriver(User driver) {
        return (root, query, cb) -> cb.and(cb.and(SearchSpecification
                                                .byLongField(driver.getId(),
                                                            cb,
                                                            root.get(FieldName.USER_ID))));
    }
}
