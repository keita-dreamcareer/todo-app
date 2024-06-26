package com.example.sample_demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class MyController {
	@Autowired TodoService todoService;
	
	// GETリクエストを処理し、全てのToDoタスクを返す
	@GetMapping("/api/tasks")
	@CrossOrigin(origins = "http://localhost:3000")
	public TodoResponse showTodos(Model model) {
		List <Todo> todos = todoService.searchAll();
		return new TodoResponse(todos);
	}
	
	//POSTリクエストを処理し、タスクを追加する
	@PostMapping("/create")
	@CrossOrigin(origins = "http://localhost:3000")
	public void create(@RequestBody Todo todo) {
		System.out.println(todo);
		todoService.addTodo(todo.getTaskName(), todo.getTaskDescription(), todo.getAssignPersonName());
	}
	
	// POSTリクエストを処理し、タスクを削除する
	@PostMapping("/delete/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public void delete(@PathVariable int id) {
		System.out.println(id);
		todoService.deleteTodo(id);
	}
	
	@PostMapping("/edit")
	@CrossOrigin(origins = "http://localhost:3000")
	public void edit(@RequestBody Todo todo) {
		System.out.println(todo);
		todoService.editTodo(todo);
	}
	
	
	// POSTリクエストを処理し、filterされたTodoリストを返す
	@PostMapping("/filter/{filterWord}")
	@CrossOrigin(origins = "http://localhost:3000")
	public TodoResponse filter(@PathVariable String filterWord) {
		List <Todo> todos = todoService.filterResult(filterWord);
		return new TodoResponse(todos);
	}
	
	
}
