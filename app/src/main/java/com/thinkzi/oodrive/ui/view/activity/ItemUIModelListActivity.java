package com.thinkzi.oodrive.ui.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.thinkzi.oodrive.ui.R;
import com.thinkzi.oodrive.ui.databinding.ActivityListItemBinding;
import com.thinkzi.oodrive.ui.model.ItemUIModel;
import com.thinkzi.oodrive.ui.view.adapter.ItemUIModelListAdapter;
import com.thinkzi.oodrive.ui.view.fragment.CreateNewFolderDialogFragment;
import com.thinkzi.oodrive.ui.viewmodel.ItemUIModelListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.inject.Inject;

/**
 * provide activity for show list of ItemUIModel
 * */
public class ItemUIModelListActivity extends BaseActivity implements CreateNewFolderDialogFragment.CreateNewFolderDialogListener {

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
                _itemUIModelListViewModel.getRemoteFolderContent(itemUIModel);
            }
        }, new ItemUIModelListAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(final ItemUIModel _itemUIModel) {

                new AlertDialog.Builder(ItemUIModelListActivity.this)
                        .setTitle(R.string.title_dialog_delete_item)
                        .setMessage(R.string.message_dialog_delete_item)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ItemUIModelListActivity.this, _itemUIModel.getName(), Toast.LENGTH_LONG).show();
                                _itemUIModelListViewModel.deleteItem(_itemUIModel.getId());
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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

                //Toast.makeText(ItemUIModelListActivity.this, itemUIModelList.size() + "", Toast.LENGTH_LONG).show();
            }
        });

        _itemUIModelListViewModel.getItemUIModelStackMutableLiveData().observe(ItemUIModelListActivity.this, new Observer<Stack<ItemUIModel>>() {
            @Override
            public void onChanged(Stack<ItemUIModel> itemUIModels) {
                if (itemUIModels.size() > 1) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_itemuimodel_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (_itemUIModelListViewModel.getItemUIModelStackMutableLiveData().getValue().size() > 1) {

                    _itemUIModelListViewModel.goBackToParentFolder(_itemUIModelListViewModel.popItemUIModelStackMutableLiveData().getParentId());

                }
                return true;

            case R.id.action_create_new_folder:
                if (_itemUIModelListViewModel.getItemUIModelStackMutableLiveData().getValue().size() > 0) {
                    showCreateNewFolderDialogFragment();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showCreateNewFolderDialogFragment() {
        CreateNewFolderDialogFragment _createNewFolderDialogFragment = new CreateNewFolderDialogFragment();
        _createNewFolderDialogFragment.show(getSupportFragmentManager(), "CreateNewFolderDialogFragment");
    }

    @Override
    public void onBackPressed() {

        if (_itemUIModelListViewModel.getItemUIModelStackMutableLiveData().getValue().size() > 1) {

            _itemUIModelListViewModel.goBackToParentFolder(_itemUIModelListViewModel.popItemUIModelStackMutableLiveData().getParentId());

        } else {
            finish();
            System.exit(0);
        }

    }

    @Override
    public void onDialogPositiveClick(String _folderName) {

        _itemUIModelListViewModel.createNewFolder(_folderName);

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }
}
