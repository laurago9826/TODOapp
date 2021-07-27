package com.todoproject.restapi.tools;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class SearchCriteria<T> {
	
	public enum Operation { GT, LT, EQUAL }
	
    private String key;
    private Operation operation;
    private Object value;
    
    public Predicate createPredicate(CriteriaBuilder cb, Root<T> entity) {
    	if (operation == Operation.GT) {
    		return cb.greaterThan(entity.get(key), value.toString());
    	} else if (operation == Operation.LT) {
    		return cb.lessThan(entity.get(key), value.toString());
    	} else { // equal
    		return cb.equal(entity.get(key), value);
    	}
    }
}
