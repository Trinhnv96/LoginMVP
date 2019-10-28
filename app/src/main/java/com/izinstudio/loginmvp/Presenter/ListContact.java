package com.izinstudio.loginmvp.Presenter;

import com.izinstudio.loginmvp.BasePresenter;
import com.izinstudio.loginmvp.BaseView;
import com.izinstudio.loginmvp.db.User;

public interface ListContact {
    interface Presenter extends BasePresenter {
        void save(User user,String username,String email);

        boolean validate(User user);
    }

    interface View extends BaseView<Presenter> {
        void showErrorMessage(int field);
    }
}
