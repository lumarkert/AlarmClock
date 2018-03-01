package com.lukas.alarmclock;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Lukas on 28.02.2018.
 */

public class NewProfileDialogFragment extends DialogFragment {

    public interface NewProfileDialogListener {
        public void onDialogPositiveClick(String profileName);
    }

    NewProfileDialogFragment.NewProfileDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NewProfileDialogFragment.NewProfileDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.profile_dialog, null);
        final EditText profileName = (EditText)mView.findViewById(R.id.editText);


        builder.setView(mView)
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NewProfileDialogFragment.NewProfileDialogListener activity = (NewProfileDialogFragment.NewProfileDialogListener) getActivity();
                        activity.onDialogPositiveClick(profileName.getText().toString());
                        NewProfileDialogFragment.this.getDialog().dismiss();
                        // mListener.onDialogPositiveClick(PickTimeDialogFragment.this);
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NewProfileDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }
}
