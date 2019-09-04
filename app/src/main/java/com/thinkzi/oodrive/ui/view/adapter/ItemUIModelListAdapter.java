package com.thinkzi.oodrive.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.thinkzi.oodrive.ui.databinding.ItemItemBinding;
import com.thinkzi.oodrive.ui.model.ItemUIModel;

import java.util.List;

/**
 * provide adapter for list of ItemUIModel for ItemUIModelListActivity
 * */
public class ItemUIModelListAdapter extends RecyclerView.Adapter<ItemUIModelListAdapter.ItemUIModelViewHolder> {

    /**
     * provide listener for onItemClick
     * */
    public interface OnItemClickListener {

        void onItemClick(ItemUIModel _itemUIModel);

    }

    /**
     * provide listener for onItemLongClick
     * */
    public interface OnItemLongClickListener {

        void onItemLongClick(ItemUIModel _itemUIModel);

    }

    // list of ItemUIModel
    private List<ItemUIModel> _itemUIModelList;

    // listener for onItemClick
    private OnItemClickListener _onItemClickListener;

    // listener for onItemClick
    private OnItemLongClickListener _onItemLongClickListener;

    // application context
    private Context _context;

    public ItemUIModelListAdapter(Context _context, List<ItemUIModel> _itemUIModelList, OnItemClickListener _onItemClickListener, OnItemLongClickListener _onItemLongClickListener) {

        this._context = _context;

        this._itemUIModelList = _itemUIModelList;

        this._onItemClickListener = _onItemClickListener;

        this._onItemLongClickListener = _onItemLongClickListener;
    }

    @NonNull
    @Override
    public ItemUIModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return(new ItemUIModelViewHolder(ItemItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)));

    }

    @Override
    public void onBindViewHolder(@NonNull ItemUIModelViewHolder holder, int position) {

        holder.bind(_itemUIModelList.get(position), _onItemClickListener, _onItemLongClickListener);

    }

    @Override
    public int getItemCount() {
        return _itemUIModelList.size();
    }

    /**
     * set list of ItemUIModel for adapter
     * */
    public void setItemUIModelList(List<ItemUIModel> _itemUIModelList) {

        this._itemUIModelList = _itemUIModelList;

        notifyDataSetChanged();
    }

    /**
     * provide a viewholder to bind data
     * */
    class ItemUIModelViewHolder extends RecyclerView.ViewHolder {

        private final ItemItemBinding _itemItemBinding;

        ItemUIModelViewHolder(ItemItemBinding _itemItemBinding) {
            super(_itemItemBinding.getRoot());
            this._itemItemBinding = _itemItemBinding;
        }

        void bind(final ItemUIModel _itemUIModel, final OnItemClickListener _onItemClickListener, final OnItemLongClickListener _onItemLongClickListener) {
            _itemItemBinding.lblItemTitle.setText(_itemUIModel.getName());

            _itemItemBinding.executePendingBindings();

            _itemItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _onItemClickListener.onItemClick(_itemUIModel);
                }
            });

            _itemItemBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    _onItemLongClickListener.onItemLongClick(_itemUIModel);
                    return false;
                }
            });
        }

    }
}
