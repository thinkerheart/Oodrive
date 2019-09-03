package com.thinkzi.oodrive.ui.model;

import org.joda.time.DateTime;

/**
 * provide ItemUIModel for saving file or folder data of app(ui) layer in Clean Architecture
 * */
public class ItemUIModel {

    private String _id;
    private boolean _isDir;
    private DateTime _modificationDate;
    private String _name;
    private String _parentId;
    private String _contentType;
    private int _size;

    public ItemUIModel() {
        this._id = "";
        this._isDir = false;
        this._modificationDate = DateTime.now();
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

    public DateTime getModificationDate() {
        return _modificationDate;
    }

    public void setModificationDate(DateTime _modificationDate) {
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
