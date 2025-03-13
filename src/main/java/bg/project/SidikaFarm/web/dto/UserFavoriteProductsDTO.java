package bg.project.SidikaFarm.web.dto;

import java.util.Set;

public class UserFavoriteProductsDTO {
    private Set<ProductDTO> favouriteProductsDTO;

    public UserFavoriteProductsDTO() {
    }

    public Set<ProductDTO> getFavouriteProductsDTO() {
        return favouriteProductsDTO;
    }

    public UserFavoriteProductsDTO setFavouriteProductsDTO(Set<ProductDTO> favouriteProductsDTO) {
        this.favouriteProductsDTO = favouriteProductsDTO;
        return this;
    }
}
