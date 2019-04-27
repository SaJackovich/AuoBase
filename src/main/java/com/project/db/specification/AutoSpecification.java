package com.project.db.specification;

import com.project.db.entity.Auto;
import com.project.db.specification.constant.FieldName;
import com.project.db.specification.util.SearchSpecification;
import com.project.web.dto.AutoSearchDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class AutoSpecification {

    public Specification<Auto> findByCondition(AutoSearchDto dto) {
        return (root, query, builder) -> builder.and(getPredicateBySearchDto(dto, root, query, builder)
                                                    .orElse(builder.conjunction()));
    }

    private Optional<Predicate> getPredicateBySearchDto(AutoSearchDto dto, Root<Auto> root,
                                                        CriteriaQuery query, CriteriaBuilder builder) {
        return Optional
                .of(dto)
                .map(this::findGreaterThanAutoSearchDtoParameters)
                .map(autoSpecification -> autoSpecification.toPredicate(root, query, builder));
    }

    private Specification<Auto> findGreaterThanAutoSearchDtoParameters(AutoSearchDto dto) {
        return (root, query, cb) -> cb.and(cb.and(SearchSpecification
                                                .greaterThanDoubleField(dto.getHeight(),
                                                                            cb,
                                                                            root.get(FieldName.HEIGHT))),
                                           cb.and(SearchSpecification
                                                .greaterThanDoubleField(dto.getWidth(),
                                                                            cb,
                                                                            root.get(FieldName.WIDTH))),
                                           cb.and(SearchSpecification
                                                .greaterThanIntegerField(dto.getCarrying(),
                                                                            cb,
                                                                            root.get(FieldName.CARRYING))),
                                           cb.and(SearchSpecification
                                                .likeByField(dto.getModelSearch(),
                                                                    cb,
                                                                    root.get(FieldName.MODEL))),
                                            cb.and(SearchSpecification
                                                .byBooleanField(false,
                                                                        cb,
                                                                        root.get(FieldName.PROCESS))));
    }

}
