package bg.project.SidikaFarm.service.impl;

import bg.project.SidikaFarm.model.entity.*;
import bg.project.SidikaFarm.model.entity.enums.OrderStatus;
import bg.project.SidikaFarm.model.entity.enums.RoleType;
import bg.project.SidikaFarm.repository.BaseProductInfoRepository;
import bg.project.SidikaFarm.repository.DeliveryDetailsRepository;
import bg.project.SidikaFarm.repository.OrderRepository;
import bg.project.SidikaFarm.repository.UserRepository;
import bg.project.SidikaFarm.service.interfaces.ProductService;
import bg.project.SidikaFarm.service.interfaces.RoleService;
import bg.project.SidikaFarm.service.interfaces.UserService;
import bg.project.SidikaFarm.web.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final DeliveryDetailsRepository deliveryDetailsRepository;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final BaseProductInfoRepository baseProductInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, DeliveryDetailsRepository deliveryDetailsRepository, OrderRepository orderRepository, ProductService productService, BaseProductInfoRepository baseProductInfoRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.deliveryDetailsRepository = deliveryDetailsRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.baseProductInfoRepository = baseProductInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(CreateRegisterDTO userRegisterDTO){

        User user = User.mapFromDTO(userRegisterDTO,passwordEncoder);

        Role userRole = roleService.findByRoleType(RoleType.USER);

        user.getRoles().add(userRole);

         userRepository.save(user);

    }
    @Override
    public UserProfileDTO getUserProfile(String email){
       User user = this.userRepository
               .findByEmail(email)
               .orElseThrow(()->new UsernameNotFoundException("User not found."));

       return mapToUserProfileDTO(user);
    }



    @Override
    public BaseUserInfoDTO getBaseUserInfo(String email) {
       return this.userRepository
                .getBaseUserInfo(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public DeliveryDetailsDTO getUserDeliveryDetails(String email) {
        Optional<DeliveryDetails> deliveryDetailsOpt = this.userRepository.findDeliveryDetailsByUserEmail(email);

        if (deliveryDetailsOpt.isPresent()){
            DeliveryDetails deliveryDetails = deliveryDetailsOpt.get();
            DeliveryDetailsDTO deliveryDetailsDTO = new DeliveryDetailsDTO();

                return deliveryDetailsDTO
                        .setFirstName(deliveryDetails.getFirstName())
                        .setLastName(deliveryDetails.getLastName())
                        .setTown(deliveryDetails.getTown())
                        .setPostCode(deliveryDetails.getPostCode())
                        .setStreet(deliveryDetails.getStreet())
                        .setNumber(deliveryDetails.getNumber())
                        .setApartment(deliveryDetails.getApartment())
                        .setFloor(deliveryDetails.getFloor())
                        .setNote(deliveryDetails.getNote())
                        .setDeliveryType(deliveryDetails.getDeliveryType())
                        .setOfficeAddress(deliveryDetails.getOfficeAddress());


        }
        return null;
    }

    @Transactional
    @Override
    public boolean userProfileShippingUpdate(String email, UserProfileDTO userProfileDTO) {
        boolean present = this.userRepository.findDeliveryDetailsByUserEmail(email).isPresent();
        DeliveryDetails deliveryDetails1 = this.userRepository.findDeliveryDetailsByUserEmail(email).orElseThrow(() -> new UsernameNotFoundException("not found"));
        DeliveryDetailsDTO deliveryDetailsDTO = userProfileDTO.getDeliveryDetails();
        DeliveryDetails deliveryDetails = new DeliveryDetails();
        boolean isSuccessUpdate = false;

        if (deliveryDetailsDTO == null){
            return false;
        }

        if (!present){
            DeliveryDetails savedDeliveryDetails = deliveryDetailsRepository.saveAndFlush(deliveryDetails);

            DeliveryDetails updatedDeliveryDetails = mapDeliveryDetailsDTOToEntity(deliveryDetailsDTO,savedDeliveryDetails);

            userRepository.updateDeliveryDetailsByEmail(email,updatedDeliveryDetails);
            isSuccessUpdate = true;

        }else {
            if (!checkBlankOrNull(deliveryDetailsDTO.getFirstName())){
                deliveryDetails1.setFirstName(deliveryDetailsDTO.getFirstName());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(deliveryDetailsDTO.getLastName())){
                deliveryDetails1.setLastName(deliveryDetailsDTO.getLastName());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(deliveryDetailsDTO.getTown())){
                deliveryDetails1.setTown(deliveryDetailsDTO.getTown());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(deliveryDetailsDTO.getPostCode())){
                deliveryDetails1.setPostCode(deliveryDetailsDTO.getPostCode());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(deliveryDetailsDTO.getStreet())){
                deliveryDetails1.setStreet(deliveryDetailsDTO.getStreet());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(String.valueOf(deliveryDetailsDTO.getNumber()))){
                deliveryDetails1.setNumber(deliveryDetailsDTO.getNumber());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(String.valueOf(deliveryDetailsDTO.getFloor()))){
                deliveryDetails1.setFloor(deliveryDetailsDTO.getFloor());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(String.valueOf(deliveryDetailsDTO.getApartment()))){
                deliveryDetails1.setApartment(deliveryDetailsDTO.getApartment());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(deliveryDetailsDTO.getNote())){
                deliveryDetails1.setNote(deliveryDetailsDTO.getNote());
                isSuccessUpdate = true;
            }
            userRepository.updateDeliveryDetailsByEmail(email,deliveryDetails1);
        }
        return isSuccessUpdate;
    }

    @Override
    public void createNewOrder(CreateOrderDTO createOrderDTO, String email) {
        Order orderToSafe = mapToOrder(createOrderDTO, email);



        this.orderRepository.save(orderToSafe);

//        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not found."));

    }

    @Override
    public UserProfileManagementDTO getUserProfileManagementDTO(String email) {

        UserProfileManagementDTO userProfileManagementDTO = this.userRepository
                .getUserProfileManagementDTO(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return userProfileManagementDTO;

    }

    @Override
    public User getCurrentUser() {
        String email = getCurrentUserEmail();

        return this.userRepository
                .findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @Override
    public String getCurrentUserEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public void addProductToFavorite(Long id) {
        User currentUser = getCurrentUser();

        Product productById = this.productService.getProductById(id);

        currentUser.setFavoriteProduct(productById);

        this.userRepository.save(currentUser);
    }

    private Order mapToOrder(CreateOrderDTO createOrderDTO, String email) {
        Order order = new Order();
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        order
                .setOwner(user)
                .setCreatedOn(LocalDateTime.now())
                .setStatus(OrderStatus.IN_PROGRESS)
                .setProducts(mapToBaseProductInfo(createOrderDTO.getCartProductData()))
                .setDeliveryDetails(mapDeliveryDetailsDTOToEntity(createOrderDTO.getDeliveryDetailsDTO(),new DeliveryDetails()))
                .setTotalPrice(calculateTotalPrice(createOrderDTO.getCartProductData()));

        return order;
    }

    private BigDecimal calculateTotalPrice(Map<Long, CartCheckoutProductDTO> cartProductData) {
        BigDecimal totalPrice = new BigDecimal(0);

        return cartProductData
             .values()
             .stream()
             .map(dto -> dto.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())))
             .reduce(BigDecimal.ZERO,BigDecimal::add);

    }

    private Set<BaseProductInfo> mapToBaseProductInfo(Map<Long, CartCheckoutProductDTO> cartProductData) {
        Set<BaseProductInfo> productInfoSet = new HashSet<>();

        cartProductData
                .forEach((productId,dto) -> {


                  productInfoSet.add(new BaseProductInfo()
                            .setProductId(productId)
                            .setName(dto.getName())
                            .setPrice(dto.getPrice())
                            .setQuantity(dto.getQuantity())
                            .setImage(dto.getImage()));
                });

        this.baseProductInfoRepository.saveAll(productInfoSet);

        return productInfoSet;
    }

    @Override
    public void profileManagementUpdate(String email, UserProfileManagementDTO userProfileManagementDTO) {
        User userToUpdate = this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        String firstNameDTO = userProfileManagementDTO.getFirstName();
        String lastNameDTO = userProfileManagementDTO.getLastName();
        String emailDTO = userProfileManagementDTO.getEmail();

        if (!checkBlankOrNull(firstNameDTO)){
            userToUpdate.setFirstName(firstNameDTO);
        }
        if (!checkBlankOrNull(lastNameDTO)){
            userToUpdate.setLastName(lastNameDTO);
        }
        if (!checkBlankOrNull(emailDTO)){
            userToUpdate.setEmail(emailDTO);
        }
        this.userRepository.save(userToUpdate);
    }

    @Override
    public boolean profilePasswordUpdate(String email, UserProfilePasswordUpdateDTO userProfilePasswordUpdateDTO) {
        User user = this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        boolean oldPasswordVerify = passwordEncoder.matches(userProfilePasswordUpdateDTO.getOldPassword(),user.getPassword());

        if (oldPasswordVerify){
            if (userProfilePasswordUpdateDTO.getNewPassword().equals(userProfilePasswordUpdateDTO.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encode(userProfilePasswordUpdateDTO.getNewPassword()));

                this.userRepository.save(user);

                return true;
            }
        }
        return false;
    }

    private DeliveryDetails mapDeliveryDetailsDTOToEntity(DeliveryDetailsDTO deliveryDetailsDTO,DeliveryDetails deliveryDetails) {

        deliveryDetails
                .setFirstName(deliveryDetailsDTO.getFirstName())
                .setLastName(deliveryDetailsDTO.getLastName())
                .setTown(deliveryDetailsDTO.getTown())
                .setPostCode(deliveryDetailsDTO.getPostCode())
                .setStreet(deliveryDetailsDTO.getStreet())
                .setNumber(deliveryDetailsDTO.getNumber())
                .setFloor(deliveryDetailsDTO.getFloor())
                .setApartment(deliveryDetailsDTO.getApartment())
                .setOfficeAddress(deliveryDetailsDTO.getOfficeAddress())
                .setDeliveryType(deliveryDetailsDTO.getDeliveryType())
                .setNote(deliveryDetailsDTO.getNote())
                .setPaymentMethod(deliveryDetailsDTO.getPaymentMethod());

        this.deliveryDetailsRepository.save(deliveryDetails);
        
        return deliveryDetails;

    }

    private UserProfileDTO mapToUserProfileDTO(User user) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        DeliveryDetailsDTO userDeliveryDetails = this.mapToDeliveryDetailsDTO(user.getDeliveryDetails());

        userProfileDTO
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setCity(user.getCity())
                .setEmail(user.getEmail())
                .setRegisteredOn(user.getRegisteredOn())
                .setCart(user.getCart())
                .setFavoriteProducts(user.getFavoriteProducts())
                .setArticles(user.getArticles())
                .setRoles(user.getRoles())
                .setDeliveryDetails(userDeliveryDetails);
        return userProfileDTO;
    }

    private DeliveryDetailsDTO mapToDeliveryDetailsDTO(DeliveryDetails deliveryDetails){
        DeliveryDetailsDTO deliveryDetailsDTO = new DeliveryDetailsDTO();

        deliveryDetailsDTO
                .setFirstName(deliveryDetails.getFirstName())
                .setLastName(deliveryDetails.getLastName())
                .setTown(deliveryDetails.getTown())
                .setPostCode(deliveryDetails.getPostCode())
                .setStreet(deliveryDetails.getStreet())
                .setNumber(deliveryDetails.getNumber())
                .setFloor(deliveryDetails.getFloor())
                .setApartment(deliveryDetails.getApartment())
                .setOfficeAddress(deliveryDetails.getOfficeAddress())
                .setDeliveryType(deliveryDetails.getDeliveryType())
                .setNote(deliveryDetails.getNote())
                .setPaymentMethod(deliveryDetails.getPaymentMethod());

        return deliveryDetailsDTO;

    }

    private boolean checkBlankOrNull(String string){
        return Optional
                .ofNullable(string)
                .map(String::isBlank)
                .orElse(true);
    }

}
