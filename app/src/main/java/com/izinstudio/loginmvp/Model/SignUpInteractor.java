package com.izinstudio.loginmvp.Model;

import com.izinstudio.loginmvp.Presenter.onSignUpFinish;
import com.izinstudio.loginmvp.db.User;

public interface SignUpInteractor {
    void saveUser(User user, String userName, String email, onSignUpFinish signUpFinish);

}

