package com.izinstudio.loginmvp.Presenter;

import com.izinstudio.loginmvp.Model.SignUp;
import com.izinstudio.loginmvp.Model.SignUpInteractor;
import com.izinstudio.loginmvp.db.User;
import com.izinstudio.loginmvp.db.UserDAO;
import com.izinstudio.loginmvp.Utils.Util;

public class SignUpPresenter implements ListContact.Presenter,onSignUpFinish {
    ListContact.View mView;
    private SignUpInteractor signUpInteractor;

    public SignUpPresenter(ListContact.View view, UserDAO userDAO) {
        this.mView=view;
        this.signUpInteractor = new SignUp(userDAO);
    }


    @Override
    public void save(User user,String userName,String email) {
        signUpInteractor.saveUser(user,userName,email,this);
    }

    @Override
    public boolean validate(User user) {
        if (user.getUsername().isEmpty() || !Util.isValidName(user.getUsername())) {
            mView.showErrorMessage(0);
            return false;
        }
        if (user.getEmail().isEmpty() || !Util.isValidEmail(user.getEmail())) {
            mView.showErrorMessage(1);
            return false;
        }

        if (user.getPassword().isEmpty() || !Util.isValidPass(user.getPassword())) {
            mView.showErrorMessage(2);
            return false;
        }
        return true;
    }

    @Override
    public void start() {

    }

    @Override
    public void onUsernameError() {
        mView.showErrorMessage(3);
    }

    @Override
    public void onEmailError() {
        mView.showErrorMessage(4);
    }

    @Override
    public void onFinish() {
        mView.showErrorMessage(5);
    }
}
