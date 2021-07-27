package com.todoproject.restapi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todoproject.restapi.entity.TodoEntity;
import com.todoproject.restapi.service.TodoSearchParams;

@Repository
public class TodoRepository {

	@Autowired
	private EntityManager entityManager;
	
	public List<TodoEntity> findByCriteria(TodoSearchParams params, Integer limit, Integer offset) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TodoEntity> cq = cb.createQuery(TodoEntity.class);
		Root<TodoEntity> root = cq.from(TodoEntity.class);
		cq.select(root);
		cq.where(params.getSearchCriteria().stream().map(p -> p.createPredicate(cb, root)).toArray(Predicate[]::new));
		cq.orderBy(params.isAsc() ? cb.asc(root.get(params.getOrderKey())) : cb.desc(root.get(params.getOrderKey())));
		TypedQuery<TodoEntity> typedQuery = entityManager.createQuery(cq);
		if (limit != null) {
			typedQuery.setMaxResults(limit);
		}
		if (offset != null) {
			typedQuery.setFirstResult(offset);
		}
		return typedQuery.getResultList();
	}
	
	@Transactional
	public void update(TodoEntity instance) {
		entityManager.refresh(instance);
	}
	
	@Transactional
	public void insert(TodoEntity instance) {
		entityManager.persist(instance);
	}
	
	@Transactional
	public void remove(TodoEntity instance) {
		entityManager.remove(instance);
	}
}
