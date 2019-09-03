package com.thinkzi.oodrive.domain.repository;

import com.thinkzi.oodrive.domain.entity.Item;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * provide item data repository interface
 * */
public interface IItemRepository {

    /**
     * get root item data from server
     * */
    Single<Item> getRemoteRootItem();

    /**
     * get content of a folder from server
     */
    Observable<Item> getRemoteFolderContent(String _id);

}
