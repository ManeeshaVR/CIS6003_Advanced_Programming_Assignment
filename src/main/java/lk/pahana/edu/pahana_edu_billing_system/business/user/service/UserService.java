package lk.pahana.edu.pahana_edu_billing_system.business.user.service;

import lk.pahana.edu.pahana_edu_billing_system.business.user.dto.UserDTO;

public interface UserService {

    UserDTO findUserByUsernameAndPassword(String username, String password);

}
