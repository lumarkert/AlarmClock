package com.lukas.alarmclock;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

/**
 * Created by Lukas on 27.02.2018.
 */

public class PickTimeDialogFragment extends DialogFragment {

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(short hours, short minutes, boolean before);
    }

    NoticeDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) context;
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
        View mView = inflater.inflate(R.layout.time_dialog, null);
        final CustomNumberPicker hoursPicker = (CustomNumberPicker) mView.findViewById(R.id.hoursPicker);
        final CustomNumberPicker minutesPicker = (CustomNumberPicker) mView.findViewById(R.id.minutesPicker);
        final RadioGroup beforeGroup = (RadioGroup) mView.findViewById(R.id.beforeGroup);
        beforeGroup.check(R.id.danachButton);

        builder.setView(mView)
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NoticeDialogListener activity = (NoticeDialogListener) getActivity();
                        boolean isBefore = true;
                        if (beforeGroup.getCheckedRadioButtonId() == R.id.vorherButton) {
                            isBefore = true;
                        } else if (beforeGroup.getCheckedRadioButtonId() == R.id.danachButton) {
                            isBefore = false;
                        }
                        activity.onDialogPositiveClick((short) hoursPicker.getValue(), (short) minutesPicker.getValue(), isBefore);
                        PickTimeDialogFragment.this.getDialog().dismiss();
                        // mListener.onDialogPositiveClick(PickTimeDialogFragment.this);
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PickTimeDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }
}
