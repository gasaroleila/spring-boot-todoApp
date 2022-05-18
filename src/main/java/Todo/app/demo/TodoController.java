package Todo.app.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getTodos() {
      return ResponseEntity.ok(ApiResponse.success(todoService.getTodos()));
    }

    @GetMapping(path = "{todoId}")
    public ResponseEntity<ApiResponse> getTodo(@PathVariable("todoId")  Long id) {
       return ResponseEntity.ok(ApiResponse.success(todoService.getTodo(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addTodo(@RequestBody Todo todo) {
        ;
        return ResponseEntity.ok(ApiResponse.success(todoService.addTodo(todo)));
    }

    @PutMapping(path = "{todoId}")
    public ResponseEntity<ApiResponse> updateTodo(@PathVariable("todoId") Long id, @RequestBody Todo title) {
      return ResponseEntity.ok(ApiResponse.success(todoService.updateTodo(id, title)));
    }

    @DeleteMapping(path = "{todoId}")
    public ResponseEntity<ApiResponse> deleteTod(@PathVariable("todoId") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok(ApiResponse.success("Deleted Sucessfully"));
    }
}
