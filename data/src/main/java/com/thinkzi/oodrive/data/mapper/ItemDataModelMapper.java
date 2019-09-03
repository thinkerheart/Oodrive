package com.thinkzi.oodrive.data.mapper;

import com.thinkzi.oodrive.data.model.ItemDataModel;
import com.thinkzi.oodrive.domain.entity.Item;

import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * provide a mapper to map data between 2 layers data and domain
 * */
@Singleton
public class ItemDataModelMapper {

    @Inject
    public ItemDataModelMapper() {
    }

    /**
     * transform a ItemDataModel object of data layer to a Item object of domain layer
     * */
    public Item transform(ItemDataModel _itemDataModel) {
        if (_itemDataModel == null)
            throw new IllegalArgumentException("Cannot transform a null ItemDataModel");

        final Item _item = new Item();
        _item.setId(_itemDataModel.getId());
        _item.setIsDir(_itemDataModel.getIsDir());
        _item.setContentType(_itemDataModel.getContentType());
        _item.setName(_itemDataModel.getName());
        _item.setParentId(_itemDataModel.getParentId());
        _item.setSize(_itemDataModel.getSize());
        _item.setModificationDate(DateTime.parse(_itemDataModel.getModificationDate()));

        return _item;
    }

    /**
     * transform a Item object of domain layer to a ItemDataModel object of data layer
     * */
    public ItemDataModel transform(Item _item) {
        if (_item == null)
            throw new IllegalArgumentException("Cannot transform a null Item");

        final ItemDataModel _itemDataModel = new ItemDataModel();
        _itemDataModel.setId(_item.getId());
        _itemDataModel.setIsDir(_item.getIsDir());
        _itemDataModel.setContentType(_item.getContentType());
        _itemDataModel.setName(_item.getName());
        _itemDataModel.setParentId(_item.getParentId());
        _itemDataModel.setSize(_item.getSize());
        _itemDataModel.setModificationDate(_item.getModificationDate().toString());

        return _itemDataModel;
    }
}
