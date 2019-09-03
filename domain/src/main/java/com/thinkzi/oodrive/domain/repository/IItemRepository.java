package com.thinkzi.oodrive.domain.repository;

import com.thinkzi.oodrive.domain.entity.Item;
import com.thinkzi.oodrive.domain.entity.User;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * provide item data repository interface
 * */
public interface IItemRepository {

    /**
     * get current user and retrieve the root item from server
     * */
    Single<User> getRemoteCurrentUser();

    /**
     * get content of a folder from server
     */
    Observable<Item> getRemoteFolderContent(String _id);

}
