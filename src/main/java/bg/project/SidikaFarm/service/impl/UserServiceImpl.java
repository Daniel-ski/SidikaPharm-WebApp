package bg.project.SidikaFarm.service.impl;

import bg.project.SidikaFarm.mapper.UserMapper;
import bg.project.SidikaFarm.model.entity.DeliveryDetails;
import bg.project.SidikaFarm.model.entity.Product;
import bg.project.SidikaFarm.model.entity.Role;
import bg.project.SidikaFarm.model.entity.User;
import bg.project.SidikaFarm.model.entity.enums.RoleType;
import bg.project.SidikaFarm.repository.UserRepository;
import bg.project.SidikaFarm.service.interfaces.DeliveryService;
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

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final DeliveryService deliveryService;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleService roleService,
                           DeliveryService deliveryService,
                           ProductService productService,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.deliveryService = deliveryService;
        this.productService = productService;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(CreateRegisterDTO createRegisterDTO) {
        User user = this.userMapper.mapFromCreateRegisterDTO(createRegisterDTO);
        user.setPassword(passwordEncoder.encode(createRegisterDTO.getPassword()));

        Role userRole = roleService.findByRoleType(RoleType.USER);
        user.getRoles().add(userRole);

        userRepository.save(user);

    }

    @Override
    public UserProfileDTO getUserProfile(String email) {
        User user = this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        UserProfileDTO userProfileDTO = this.userMapper.mapToUserProfileDTO(user);

        DeliveryDetailsDTO deliveryDetailsDTO = this.deliveryService.mapToDeliveryDetailsDTO(user.getDeliveryDetails());
        userProfileDTO.setDeliveryDetails(deliveryDetailsDTO);

        return userProfileDTO;
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

        if (deliveryDetailsOpt.isPresent()) {
            DeliveryDetails deliveryDetails = deliveryDetailsOpt.get();

            return this.deliveryService.mapToDeliveryDetailsDTO(deliveryDetails);
        }
        return null;
    }

    @Transactional
    @Override
    public boolean userProfileShippingUpdate(UserProfileDTO userProfileDTO) {

        String currentUserEmail = this.getCurrentUserEmail();

        Optional<DeliveryDetails> deliveryDetailsOpt = this.userRepository.findDeliveryDetailsByUserEmail(currentUserEmail);
        DeliveryDetailsDTO deliveryDetailsDTO = userProfileDTO.getDeliveryDetails();

        boolean isSuccessUpdate = false;

        if (deliveryDetailsDTO == null) {
            return false;
        }

        if (deliveryDetailsOpt.isEmpty()) {
            DeliveryDetails savedDeliveryDetails = this.deliveryService.saveDeliveryDetails(new DeliveryDetails());

            DeliveryDetails updatedDeliveryDetails = this.deliveryService.mapDeliveryDetailsDTOToEntity(deliveryDetailsDTO, savedDeliveryDetails);

            userRepository.updateDeliveryDetailsByEmail(currentUserEmail, updatedDeliveryDetails);
            isSuccessUpdate = true;

        } else {
            DeliveryDetails deliveryDetails = deliveryDetailsOpt.get();

            if (!checkBlankOrNull(deliveryDetailsDTO.getFirstName())) {
                deliveryDetails.setFirstName(deliveryDetailsDTO.getFirstName());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(deliveryDetailsDTO.getLastName())) {
                deliveryDetails.setLastName(deliveryDetailsDTO.getLastName());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(deliveryDetailsDTO.getTown())) {
                deliveryDetails.setTown(deliveryDetailsDTO.getTown());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(deliveryDetailsDTO.getPostCode())) {
                deliveryDetails.setPostCode(deliveryDetailsDTO.getPostCode());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(deliveryDetailsDTO.getStreet())) {
                deliveryDetails.setStreet(deliveryDetailsDTO.getStreet());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(String.valueOf(deliveryDetailsDTO.getNumber()))) {
                deliveryDetails.setNumber(deliveryDetailsDTO.getNumber());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(String.valueOf(deliveryDetailsDTO.getFloor()))) {
                deliveryDetails.setFloor(deliveryDetailsDTO.getFloor());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(String.valueOf(deliveryDetailsDTO.getApartment()))) {
                deliveryDetails.setApartment(deliveryDetailsDTO.getApartment());
                isSuccessUpdate = true;
            }
            if (!checkBlankOrNull(deliveryDetailsDTO.getNote())) {
                deliveryDetails.setNote(deliveryDetailsDTO.getNote());
                isSuccessUpdate = true;
            }
            userRepository.updateDeliveryDetailsByEmail(currentUserEmail, deliveryDetails);
        }
        return isSuccessUpdate;
    }


    @Override
    public UserProfileManagementDTO getUserProfileManagementDTO(String email) {

        return this.userRepository
                .getUserProfileManagementDTO(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

    }

    @Override
    public User getCurrentUser() {
        String email = getCurrentUserEmail();

        return this.userRepository
                .findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @Override
    public String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public void addProductToFavorite(Long id) {
        User currentUser = getCurrentUser();

        Product productById = this.productService.getProductById(id);

        currentUser.setFavoriteProduct(productById);

        this.userRepository.save(currentUser);
    }


    @Override
    public void profileManagementUpdate(String email, UserProfileManagementDTO userProfileManagementDTO) {
        User userToUpdate = this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        String firstNameDTO = userProfileManagementDTO.getFirstName();
        String lastNameDTO = userProfileManagementDTO.getLastName();
        String emailDTO = userProfileManagementDTO.getEmail();

        if (!checkBlankOrNull(firstNameDTO)) {
            userToUpdate.setFirstName(firstNameDTO);
        }
        if (!checkBlankOrNull(lastNameDTO)) {
            userToUpdate.setLastName(lastNameDTO);
        }
        if (!checkBlankOrNull(emailDTO)) {
            userToUpdate.setEmail(emailDTO);
        }
        this.userRepository.save(userToUpdate);
    }

    @Override
    public boolean profilePasswordUpdate(String email, UserProfilePasswordUpdateDTO userProfilePasswordUpdateDTO) {
        User user = this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        boolean oldPasswordVerify = passwordEncoder.matches(userProfilePasswordUpdateDTO.getOldPassword(), user.getPassword());

        if (oldPasswordVerify) {
            if (userProfilePasswordUpdateDTO.getNewPassword().equals(userProfilePasswordUpdateDTO.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encode(userProfilePasswordUpdateDTO.getNewPassword()));

                this.userRepository.save(user);

                return true;
            }
        }
        return false;
    }


    private boolean checkBlankOrNull(String string) {
        return Optional
                .ofNullable(string)
                .map(String::isBlank)
                .orElse(true);
    }

}
