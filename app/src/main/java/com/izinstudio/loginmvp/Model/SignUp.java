package com.izinstudio.loginmvp.Model;

import com.izinstudio.loginmvp.Presenter.onSignUpFinish;
import com.izinstudio.loginmvp.db.User;
import com.izinstudio.loginmvp.db.UserDAO;

public class SignUp implements SignUpInteractor {
    UserDAO userDAO;

    public SignUp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void saveUser(User user, String userName, String email, onSignUpFinish signUpFinish) {
        for (int i = 0; i < userDAO.getUser().size(); i++) {
            if (userName.equals(userDAO.getUser().get(i).getUsername())) {
                signUpFinish.onUsernameError();
                return;
            } else if (email.equals(userDAO.getUser().get(i).getEmail())) {
                signUpFinish.onEmailError();
                return;
            }
        }
        userDAO.insertUser(user);
        signUpFinish.onFinish();
    }

}
