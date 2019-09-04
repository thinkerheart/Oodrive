package com.thinkzi.oodrive.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * provide CreateNewFolderPOSTRequestBodyDataModel of data layer in Clean Architecture for POST request body to request creating a new folder
 * */
public class CreateNewFolderPOSTRequestBodyDataModel {

    @SerializedName("name")
    @Expose
    private String _name;

    public CreateNewFolderPOSTRequestBodyDataModel() {
        this._name = "";
    }

    public CreateNewFolderPOSTRequestBodyDataModel(String _name) {
        this._name = _name;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

}
