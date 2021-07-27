package com.todoproject.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.todoproject.restapi.dao.TodoRepository;
import com.todoproject.restapi.entity.Priority;
import com.todoproject.restapi.entity.TodoEntity;

@SpringBootTest
public class RestApiApplicationTests {

	@Autowired
	private TodoRepository todoRepo;
	
	@Test
	public void test() {
		todoRepo.insert(
				TodoEntity.builder()
				.name("TASK").creationDate(System.currentTimeMillis())
				.deadline(System.currentTimeMillis() + 10000).priority(Priority.HIGH).build());
//		List<TODOEntity> entities = todoRepo.findAll();
		int a = 0;
	}
}
