package com.thinkzi.oodrive.ui.mapper;

import com.thinkzi.oodrive.domain.entity.User;
import com.thinkzi.oodrive.ui.model.UserUIModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * provide a mapper to map data between 2 layers domain and app(ui)
 * */
@Singleton
public class UserUIModelMapper {

    @Inject
    ItemUIModelMapper _itemUIModelMapper;

    @Inject
    public UserUIModelMapper() {
    }

    /**
     * transform a UserUIModel object of app(ui) layer to a User object of domain layer
     * */
    public User transform(UserUIModel _userUIModel) {
        if (_userUIModel == null)
            throw new IllegalArgumentException("Cannot transform a null UserUIModel");

        final User _user = new User();
        _user.setFirstName(_userUIModel.getFirstName());
        _user.setLastName(_userUIModel.getLastName());
        _user.setRootItem(_itemUIModelMapper.transform(_userUIModel.getRootItem()));

        return _user;
    }

    /**
     * transform a User object of domain layer to a UserUIModel object of app(ui) layer
     * */
    public UserUIModel transform(User _user) {
        if (_user == null)
            throw new IllegalArgumentException("Cannot transform a null User");

        final UserUIModel _userUIModel = new UserUIModel();
        _userUIModel.setFirstName(_user.getFirstName());
        _userUIModel.setLastName(_user.getLastName());
        _userUIModel.setRootItem(_itemUIModelMapper.transform(_user.getRootItem()));

        return _userUIModel;
    }
}
