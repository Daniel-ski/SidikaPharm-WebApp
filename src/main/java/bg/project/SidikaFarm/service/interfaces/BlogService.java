package bg.project.SidikaFarm.service.interfaces;

import bg.project.SidikaFarm.web.dto.CreateArticleDTO;

public interface BlogService {
    void createArticle(CreateArticleDTO createArticleDTO);
}
