package com.todoproject.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todoproject.restapi.entity.Priority;
import com.todoproject.restapi.entity.TodoEntity;
import com.todoproject.restapi.service.TodoSearchParams;
import com.todoproject.restapi.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@PostMapping("/update")
	public void update(@RequestBody TodoEntity entity) {
		todoService.update(entity);
	}
	
	@PostMapping("/new")
	public void insert(@RequestBody TodoEntity entity) {
		todoService.insert(entity);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		todoService.remove(id);
	}
	
	@GetMapping
	public List<TodoEntity> findAll(
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "deadline-from") Long deadlineFrom,
			@RequestParam(value = "deadline-until") Long deadlineUntil,
			@RequestParam(value = "creation-date-from") Long creationDateFrom,
			@RequestParam(value = "creation-date-until") Long creationDateUntil,
			@RequestParam(value = "priority") String priority,
			@RequestParam(value = "order") String orderProperty,
			@RequestParam(value = "asc", defaultValue = "true") boolean asc,
			@RequestParam(value = "limit") Integer limit,
			@RequestParam(value = "offset") Integer offset
			) {
		TodoSearchParams params = new TodoSearchParams();
		executeIfArgNotNull(id, () -> params.addSearchById(id));
		executeIfArgNotNull(name, () -> params.addSearchByName(name));
		executeIfArgNotNull(deadlineFrom, () -> params.addSearchByDeadlineFrom(deadlineFrom));
		executeIfArgNotNull(deadlineUntil, () -> params.addSearchByDeadlineUntil(deadlineUntil));
		executeIfArgNotNull(creationDateFrom, () -> params.addSearchByCreationDateFrom(creationDateFrom));
		executeIfArgNotNull(creationDateUntil, () -> params.addSearchByCreationDateUntil(creationDateUntil));
		executeIfArgNotNull(priority, () -> {
			try {
				Priority prior = Priority.valueOf(priority.toUpperCase());
				params.addSearchByPriority(prior);
			} catch (Exception e) {/*Invalid priority*/}
		});
		executeIfArgNotNull(orderProperty, () -> params.orderBy(orderProperty));
		params.orderAsc(asc);
		return todoService.findByParams(params, limit, offset);
	}
	
	private void executeIfArgNotNull(Object obj, Runnable function) {
		if (obj != null) {
			function.run();
		}
	}
}
