package com.thinkzi.oodrive.ui.mapper;

import com.thinkzi.oodrive.domain.entity.Item;
import com.thinkzi.oodrive.ui.model.ItemUIModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * provide a mapper to map data between 2 layers domain and app(ui)
 * */
@Singleton
public class ItemUIModelMapper {

    @Inject
    public ItemUIModelMapper() {
    }

    /**
     * transform a ItemUIModel object of app(ui) layer to a Item object of domain layer
     * */
    public Item transform(ItemUIModel _itemUIModel) {
        if (_itemUIModel == null)
            throw new IllegalArgumentException("Cannot transform a null ItemUIModel");

        final Item _item = new Item();
        _item.setId(_itemUIModel.getId());
        _item.setIsDir(_itemUIModel.getIsDir());
        _item.setContentType(_itemUIModel.getContentType());
        _item.setName(_itemUIModel.getName());
        _item.setParentId(_itemUIModel.getParentId());
        _item.setSize(_itemUIModel.getSize());
        _item.setModificationDate(_itemUIModel.getModificationDate());

        return _item;
    }

    /**
     * transform a Item object of domain layer to a ItemUIModel object of app(ui)
     * */
    public ItemUIModel transform(Item _item) {
        if (_item == null)
            throw new IllegalArgumentException("Cannot transform a null Item");

        final ItemUIModel _itemUIModel = new ItemUIModel();
        _itemUIModel.setId(_item.getId());
        _itemUIModel.setIsDir(_item.getIsDir());
        _itemUIModel.setContentType(_item.getContentType());
        _itemUIModel.setName(_item.getName());
        _itemUIModel.setParentId(_item.getParentId());
        _itemUIModel.setSize(_item.getSize());
        _itemUIModel.setModificationDate(_item.getModificationDate());

        return _itemUIModel;
    }
}
