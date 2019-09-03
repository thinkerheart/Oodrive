package com.thinkzi.oodrive.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * provide UserDataModel for saving current user and root item data of data layer in Clean Architecture
 * */
public class UserDataModel {

    @SerializedName("firstName")
    @Expose
    private String _firstName;

    @SerializedName("lastName")
    @Expose
    private String _lastName;

    @SerializedName("rootItem")
    @Expose
    private ItemDataModel _rootItem;

    public UserDataModel() {
        this._firstName = "";
        this._lastName = "";
        this._rootItem = new ItemDataModel();
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

    public ItemDataModel getRootItem() {
        return _rootItem;
    }

    public void setRootItem(ItemDataModel _rootItem) {
        this._rootItem = _rootItem;
    }

}
