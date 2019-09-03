package com.thinkzi.oodrive.domain.entity;

/**
 * provide User for saving current user and root item data of domain layer (business logic layer) in Clean Architecture
 * */
public class User {

    private String _firstName;

    private String _lastName;

    private Item _rootItem;

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }

    public Item getRootItem() {
        return _rootItem;
    }

    public void setRootItem(Item _rootItem) {
        this._rootItem = _rootItem;
    }

}
