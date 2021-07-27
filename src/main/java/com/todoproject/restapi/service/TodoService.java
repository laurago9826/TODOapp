package com.todoproject.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoproject.restapi.dao.TodoRepository;
import com.todoproject.restapi.entity.TodoEntity;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepo;
	
	public TodoEntity findById(Integer id) {
		return todoRepo.findByCriteria(new TodoSearchParams().addSearchById(id), 1, 0)
				.stream().findAny().orElse(null);
	}
	
//	public List<TodoEntity> findByParams(TodoSearchParams params) {
//		return todoRepo.findByCriteria(params);
//	}
	
	public List<TodoEntity> findByParams(TodoSearchParams params, int pageNo, int pageSize) {
		int offset = (pageNo - 1) * pageSize;
		return todoRepo.findByCriteria(params, pageSize, offset);
	}
	
	public void insert(TodoEntity entity) {
		todoRepo.insert(entity);
	}
	
	public void update(TodoEntity entity) {
		todoRepo.update(entity);
	}
	
	public void remove(int id) {
		todoRepo.remove(findById(id));
	}
}
