package com.project.db.specification.util;

import com.project.db.constant.JourneyStatus;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.Objects;

public final class SearchSpecification {

    private SearchSpecification() {
    }

    public static Predicate byBooleanField(Boolean search, CriteriaBuilder builder, Expression<Boolean> path) {
        return search ? builder.isTrue(path) : builder.isFalse(path);
    }

    public static Predicate greaterThanIntegerField(Integer search, CriteriaBuilder builder, Expression<Integer> path) {
        if (search == 0) {
            return builder.conjunction();
        }
        return builder.greaterThanOrEqualTo(path, search);
    }

    public static Predicate greaterThanDoubleField(Double search, CriteriaBuilder builder, Expression<Double> path) {
        if (search == 0) {
            return builder.conjunction();
        }
        return builder.greaterThanOrEqualTo(path, search);
    }

    public static Predicate equalsByJourneyStatus(JourneyStatus status, CriteriaBuilder cb, Expression<String> path) {
        if (Objects.isNull(status)) {
            return cb.conjunction();
        }
        return cb.equal(path, status);
    }

    @SafeVarargs
    public static Predicate[] likeByField(String search, CriteriaBuilder cb, Expression<String>... paths) {
        String finalSearch = StringUtils.trimToNull(search);
        if (StringUtils.isBlank(finalSearch)) {
            return new Predicate[]{cb.conjunction()};
        }
        return Arrays
                .stream(paths)
                .map(path -> cb.like(path, '%' + finalSearch + '%'))
                .toArray(Predicate[]::new);
    }
}
