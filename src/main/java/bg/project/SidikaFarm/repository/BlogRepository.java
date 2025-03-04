package bg.project.SidikaFarm.repository;

import bg.project.SidikaFarm.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {

}
