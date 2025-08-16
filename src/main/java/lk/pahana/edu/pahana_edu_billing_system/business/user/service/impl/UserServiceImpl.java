package lk.pahana.edu.pahana_edu_billing_system.business.user.service.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.user.dto.UserDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.user.mapper.UserMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.user.service.UserService;
import lk.pahana.edu.pahana_edu_billing_system.persistence.user.dao.UserDAO;
import lk.pahana.edu.pahana_edu_billing_system.persistence.user.dao.impl.UserDAOImpl;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public UserDTO findUserByUsernameAndPassword(String username, String password) {
        return UserMapper.toDTO(userDAO.findByUsernameAndPassword(username, password));
    }
}
