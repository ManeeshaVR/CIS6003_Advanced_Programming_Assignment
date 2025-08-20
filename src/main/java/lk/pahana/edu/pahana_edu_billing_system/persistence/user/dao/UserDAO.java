package lk.pahana.edu.pahana_edu_billing_system.persistence.user.dao;

import lk.pahana.edu.pahana_edu_billing_system.business.user.model.User;

public interface UserDAO {

    User findByUsernameAndPassword(String username, String password);

}
