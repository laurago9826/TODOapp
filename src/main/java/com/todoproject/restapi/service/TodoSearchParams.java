package com.todoproject.restapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.todoproject.restapi.entity.Priority;
import com.todoproject.restapi.entity.TodoEntity;
import com.todoproject.restapi.tools.SearchCriteria;
import com.todoproject.restapi.tools.SearchCriteria.Operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoSearchParams {
	
	private static final String ID_PARAM = "id";
	private static final String NAME_PARAM = "name";
	private static final String DEADLINE_PARAM = "deadline";
	private static final String CREATIONDATE_PARAM = "creationDate";
	private static final String PRIORITY_PARAM = "priority";
	
	private static final List<String> validParams
		= Arrays.asList(ID_PARAM, NAME_PARAM, DEADLINE_PARAM, CREATIONDATE_PARAM, PRIORITY_PARAM);

	private List<SearchCriteria<TodoEntity>> searchCriteria = new ArrayList<>();
	private String orderKey = ID_PARAM;
	private boolean asc = true;
	
	//------------SEARCH-------------
	public TodoSearchParams addSearchById(int id) {
		searchCriteria.add(new SearchCriteria<>(ID_PARAM, Operation.EQUAL, id));
		return this;
	}
	public TodoSearchParams addSearchByName(String name) {
		searchCriteria.add(new SearchCriteria<>(NAME_PARAM, Operation.EQUAL, name));
		return this;
	}
	public TodoSearchParams addSearchByDeadlineFrom(Long from) {
		searchCriteria.add(new SearchCriteria<>(DEADLINE_PARAM, Operation.GT, from));
		return this;
	}
	public TodoSearchParams addSearchByDeadlineUntil(Long until) {
		searchCriteria.add(new SearchCriteria<>(DEADLINE_PARAM, Operation.LT, until));
		return this;
	}
	public TodoSearchParams addSearchByCreationDateFrom(Long from) {
		searchCriteria.add(new SearchCriteria<>(CREATIONDATE_PARAM, Operation.GT, from));
		return this;
	}
	public TodoSearchParams addSearchByCreationDateUntil(Long until) {
		searchCriteria.add(new SearchCriteria<>(CREATIONDATE_PARAM, Operation.LT, until));
		return this;
	}
	public TodoSearchParams addSearchByPriority(Priority priority) {
		searchCriteria.add(new SearchCriteria<>(PRIORITY_PARAM, Operation.EQUAL, priority));
		return this;
	}
	
	//-------ORDER BY----------
	public TodoSearchParams orderBy(String prop) {
		if (validParams.contains(prop)) {
			orderKey = prop;
		}
		return this;
	}
	
	//----------ASC / DESC-------------
	public TodoSearchParams orderAsc(boolean asc) {
		this.asc = asc;
		return this;
	}
	
	

}
