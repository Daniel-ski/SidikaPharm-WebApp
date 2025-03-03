package bg.project.SidikaFarm.service.impl;

import bg.project.SidikaFarm.model.entity.Article;
import bg.project.SidikaFarm.model.entity.Role;
import bg.project.SidikaFarm.model.entity.User;
import bg.project.SidikaFarm.model.entity.enums.RoleType;
import bg.project.SidikaFarm.repository.BlogRepository;
import bg.project.SidikaFarm.service.interfaces.BlogService;
import bg.project.SidikaFarm.web.dto.CreateArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void createArticle(CreateArticleDTO createArticleDTO) {
        Article article = new Article();
        User user = new User();
        Role role = new Role();
            role.setRoleType(RoleType.ADMIN);

//        user.setAge(20);
        user.setCity("Varna");
        user.setEmail("plondereks@abv.bg");
        user.setFirstName("Daniel");
        user.setLastName("Ivanov");
        user.setRoles(Set.of(role));
        user.setPassword("topsecret");
        user.setRegisteredOn(LocalDateTime.now());

        article.setTitle(createArticleDTO.getTitle());
        article.setAuthor(user);
        article.setContent(createArticleDTO.getContent());
        article.setCreated(createArticleDTO.getCreated());
        article.setImageUrl(createArticleDTO.getImageUrl());

        blogRepository.save(article);
    }
}
