package com.thinkzi.oodrive.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.thinkzi.oodrive.ui.R;
import com.thinkzi.oodrive.ui.databinding.ActivityListItemBinding;
import com.thinkzi.oodrive.ui.model.ItemUIModel;
import com.thinkzi.oodrive.ui.view.adapter.ItemUIModelListAdapter;
import com.thinkzi.oodrive.ui.viewmodel.ItemUIModelListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * provide activity for show list of ItemUIModel
 * */
public class ItemUIModelListActivity extends BaseActivity {

    @Inject
    ItemUIModelListViewModel _itemUIModelListViewModel;

    // android data binding
    private ActivityListItemBinding _activityListItemBinding;

    // adapter for a list of ItemUIModel
    private ItemUIModelListAdapter _itemUIModelListAdapter;

    // intent contain source context and destination context (this Activity) for Navigator
    public static Intent getCallingIntent(Context _context) {
        return new Intent(_context, ItemUIModelListActivity.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inject dependancy for this Activity
        this.getApplicationComponent().inject(this);

        // android data binding
        _activityListItemBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_item);

        _activityListItemBinding.setLifecycleOwner(this);

        // set viewmodel for databiding
        _activityListItemBinding.setItemUIModelListViewModel(_itemUIModelListViewModel);

        // initialize adapter with onItemClick event for list of ItemUIModel
        _itemUIModelListAdapter = new ItemUIModelListAdapter(getApplicationContext(), new ArrayList<ItemUIModel>(), new ItemUIModelListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemUIModel itemUIModel) {
                Toast.makeText(ItemUIModelListActivity.this, itemUIModel.getName(), Toast.LENGTH_LONG).show();
            }
        });

        // set adapter for RecyclerView
        _activityListItemBinding.rcvItemList.setLayoutManager(new LinearLayoutManager(ItemUIModelListActivity.this));
        _activityListItemBinding.rcvItemList.setAdapter(_itemUIModelListAdapter);

        // live data to observe the change of list of ItemUIModel
        _itemUIModelListViewModel.getItemUIModelListMutableLiveData().observe(ItemUIModelListActivity.this, new Observer<List<ItemUIModel>>() {
            @Override
            public void onChanged(List<ItemUIModel> itemUIModelList) {
                _itemUIModelListAdapter.setItemUIModelList(itemUIModelList);

                Toast.makeText(ItemUIModelListActivity.this, itemUIModelList.size() + "", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        finish();
        System.exit(0);

    }
}