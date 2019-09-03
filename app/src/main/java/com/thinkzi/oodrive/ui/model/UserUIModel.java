package com.thinkzi.oodrive.ui.model;

/**
 * provide UserUIModel for saving current user and root item data of app(ui) layer in Clean Architecture
 * */
public class UserUIModel {

    private String _firstName;

    private String _lastName;

    private ItemUIModel _rootItem;

    public UserUIModel() {
        this._firstName = "";
        this._lastName = "";
        this._rootItem = new ItemUIModel();
    }

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

    public ItemUIModel getRootItem() {
        return _rootItem;
    }

    public void setRootItem(ItemUIModel _rootItem) {
        this._rootItem = _rootItem;
    }

}
