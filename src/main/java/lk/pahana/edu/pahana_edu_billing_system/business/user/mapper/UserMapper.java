package lk.pahana.edu.pahana_edu_billing_system.business.user.mapper;

import lk.pahana.edu.pahana_edu_billing_system.business.user.dto.UserDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.user.model.User;

public class UserMapper {

    public static User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        return new User.Builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .fullName(dto.getFullName())
                .contactNumber(dto.getContactNumber())
                .email(dto.getEmail())
                .build();
    }

    public static UserDTO toDTO(User entity) {
        if (entity == null) {
            return null;
        }
        return new UserDTO.Builder()
                .userId(entity.getUserId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .fullName(entity.getFullName())
                .contactNumber(entity.getContactNumber())
                .email(entity.getEmail())
                .build();
    }

}
