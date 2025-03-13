package bg.project.SidikaFarm.web.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class UserCartDTO {

    private long id;
    private static double BALANCE_TO_FREE_SHIPPING;
    private BigDecimal totalPrice;
    private Set<BaseProductInfoDTO> baseProductsInfoDTO;
    private BigDecimal savedMoney;

    public UserCartDTO() {
        this.baseProductsInfoDTO = new HashSet<>();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public UserCartDTO setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Set<BaseProductInfoDTO> getProductsDTO() {
        return baseProductsInfoDTO;
    }

    public UserCartDTO setBaseProductsInfoDTO(Set<BaseProductInfoDTO> baseProductInfoDTO) {
        this.baseProductsInfoDTO = baseProductInfoDTO;
        return this;
    }

    public long getId() {
        return id;
    }

    public UserCartDTO setId(long id) {
        this.id = id;
        return this;
    }

    public static double getBalanceToFreeShipping() {
        return BALANCE_TO_FREE_SHIPPING;
    }

    public static void setBalanceToFreeShipping(double balanceToFreeShipping) {
        BALANCE_TO_FREE_SHIPPING = balanceToFreeShipping;
    }

    public BigDecimal getSavedMoney() {
        return savedMoney;
    }

    public UserCartDTO setSavedMoney(BigDecimal savedMoney) {
        this.savedMoney = savedMoney;
        return this;
    }

}
