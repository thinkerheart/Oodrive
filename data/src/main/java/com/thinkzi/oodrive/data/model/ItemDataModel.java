package com.thinkzi.oodrive.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * provide ItemDataModel for saving file or folder data of data layer in Clean Architecture
 * */
public class ItemDataModel {

    @SerializedName("id")
    @Expose
    private String _id;

    @SerializedName("isDir")
    @Expose
    private boolean _isDir;

    @SerializedName("modificationDate")
    @Expose
    private String _modificationDate;

    @SerializedName("name")
    @Expose
    private String _name;

    @SerializedName("parentId")
    @Expose
    private String _parentId;

    @SerializedName("contentType")
    @Expose
    private String _contentType;

    @SerializedName("size")
    @Expose
    private int _size;

    public ItemDataModel() {
        this._id = "";
        this._isDir = false;
        this._modificationDate = "";
        this._name = "";
        this._parentId = "";
        this._contentType = "";
        this._size = 0;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public boolean getIsDir() {
        return _isDir;
    }

    public void setIsDir(boolean _isDir) {
        this._isDir = _isDir;
    }

    public String getModificationDate() {
        return _modificationDate;
    }

    public void setModificationDate(String _modificationDate) {
        this._modificationDate = _modificationDate;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getParentId() {
        return _parentId;
    }

    public void setParentId(String _parentId) {
        this._parentId = _parentId;
    }

    public String getContentType() {
        return _contentType;
    }

    public void setContentType(String _contentType) {
        this._contentType = _contentType;
    }

    public int getSize() {
        return _size;
    }

    public void setSize(int _size) {
        this._size = _size;
    }

}
