package com.thinkzi.oodrive.data.mapper;

import com.thinkzi.oodrive.data.model.UserDataModel;
import com.thinkzi.oodrive.domain.entity.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * provide a mapper to map data between 2 layers data and domain
 * */
@Singleton
public class UserDataModelMapper {

    @Inject
    ItemDataModelMapper _itemDataModelMapper;

    @Inject
    public UserDataModelMapper() {
    }

    /**
     * transform a UserDataModel object of data layer to a User object of domain layer
     * */
    public User transform(UserDataModel _userDataModel) {
        if (_userDataModel == null)
            throw new IllegalArgumentException("Cannot transform a null UserDataModel");

        final User _user = new User();
        _user.setFirstName(_userDataModel.getFirstName());
        _user.setLastName(_userDataModel.getLastName());
        _user.setRootItem(_itemDataModelMapper.transform(_userDataModel.getRootItem()));

        return _user;
    }
}
