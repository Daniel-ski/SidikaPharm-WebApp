package bg.project.SidikaFarm.web.controller;

import bg.project.SidikaFarm.service.interfaces.BlogService;
import bg.project.SidikaFarm.service.interfaces.ImageService;
import bg.project.SidikaFarm.web.dto.CreateArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminBlogsController {

    private final ImageService imageService;
    private final BlogService blogService;

    @Autowired
    public AdminBlogsController(ImageService imageService, BlogService blogService) {
        this.imageService = imageService;
        this.blogService = blogService;
    }


    @GetMapping("/blogs")
    public String blogsPage(){
        return "admin/blogs";
    }

    @GetMapping("/blogs/create-article")
    public String createArticlePage(Model model){

        if (!model.containsAttribute("createArticleDTO")){
            model.addAttribute("createArticleDTO" ,new CreateArticleDTO());
        }
        return "admin/create-article";
    }


    @PostMapping("/blogs/create-article")
    public String createArticle(CreateArticleDTO createArticleDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("createArticleDTO", createArticleDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.createArticleDTO", bindingResult);
            return "redirect:/admin/blogs/create-article";
        }

        MultipartFile image = createArticleDTO.getImage();

        if (!image.isEmpty()){
            String imageUrl = imageService.uploadImage(image);
            createArticleDTO.setImageUrl(imageUrl);
        }

        blogService.createArticle(createArticleDTO);


        return "redirect:/admin/blogs";
    }
}
