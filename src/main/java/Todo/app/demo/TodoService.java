package Todo.app.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepositiory todoRepository;
    @Autowired
    public TodoService(TodoRepositiory todoRepositiory) {
        this.todoRepository = todoRepositiory;
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodo(Long id) {
        return todoRepository.findById(id);
    }

    public Todo addTodo(Todo newTodo) {
        Optional<Todo> fetchedTodo = todoRepository.findTodoByTitle(newTodo.getTitle()) ;
        if(fetchedTodo.isPresent()) {
            throw new IllegalStateException("Title already taken");
        }

        newTodo.setCompleted(false);
        newTodo.setDoc(LocalDate.now());

        return todoRepository.save(newTodo);
    }

     @Transactional
    public Todo updateTodo(Long todoId, Todo updatedTodo) {
         String newTitle = updatedTodo.getTitle();
       Todo fetchedTodo = todoRepository.findById(todoId)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Todo Not Found"));
       Optional<Todo> todoOptional = todoRepository.findTodoByTitle(newTitle);

       if(newTitle != null && newTitle.length()>0 && !newTitle.equals(fetchedTodo.getTitle())) {
        if(todoOptional.isPresent()) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title already taken");
        }

        fetchedTodo.setTitle(newTitle);

    }
         return fetchedTodo;
    }

    public void deleteTodo(Long todoId) {
         todoRepository.findById(todoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Todo Not Found"));

         todoRepository.deleteById(todoId);
    }
}
