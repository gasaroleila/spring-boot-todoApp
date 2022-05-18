package Todo.app.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepositiory extends JpaRepository<Todo, Long> {

//    @Query("SELECT * FROM todo t WHERE t.title=?1")
    Optional<Todo> findTodoByTitle(String title);

}

