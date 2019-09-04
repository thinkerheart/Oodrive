package com.thinkzi.oodrive.ui.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.thinkzi.oodrive.ui.R;

public class CreateNewFolderDialogFragment extends DialogFragment {

    public interface CreateNewFolderDialogListener {
        void onDialogPositiveClick(String _folderName);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    CreateNewFolderDialogListener _createNewFolderDialogListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            _createNewFolderDialogListener = (CreateNewFolderDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_create_new_folder, null);
        final EditText _folderName = (EditText) view.findViewById(R.id.edtFolderName);
        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        _createNewFolderDialogListener.onDialogPositiveClick(_folderName.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        _createNewFolderDialogListener.onDialogNegativeClick(CreateNewFolderDialogFragment.this);
                    }
                });
        return builder.create();
    }
}
