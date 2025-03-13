package bg.project.SidikaFarm.web.dto;

import bg.project.SidikaFarm.model.entity.Category;
import bg.project.SidikaFarm.model.entity.Image;

import java.math.BigDecimal;
import java.util.Set;

public class CreateProductDTO {
    private String title;
    private String brand;
    private BigDecimal price;
    private String image;
    private String mainDescription;
    private String detailedDescription;
    private String usageInstructions;
    private String ingredients;
    private Set<Category> categories;
    private String availability;
}
