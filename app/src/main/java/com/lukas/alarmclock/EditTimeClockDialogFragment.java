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
import android.widget.RadioGroup;

/**
 * Created by Lukas on 01.03.2018.
 */

public class EditTimeClockDialogFragment extends DialogFragment {
    TimeClock m_timeClock;
    int position;

    public interface EditTimeClockDialogListener {
        public void onTimeClockPositiveClick(TimeClock tc, int position);
    }

    static EditTimeClockDialogFragment newInstance(TimeClock tc, int position) {
        EditTimeClockDialogFragment f = new EditTimeClockDialogFragment();
        f.setTimeClock(tc);
        f.setPosition(position);
        return f;
    }

    EditTimeClockDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (EditTimeClockDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setRetainInstance(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.time_dialog, null);
        final CustomNumberPicker hoursPicker = (CustomNumberPicker) mView.findViewById(R.id.hoursPicker);
        final CustomNumberPicker minutesPicker = (CustomNumberPicker) mView.findViewById(R.id.minutesPicker);
        final RadioGroup beforeGroup = (RadioGroup) mView.findViewById(R.id.beforeGroup);
        hoursPicker.setValue(m_timeClock.getHours());
        minutesPicker.setValue(m_timeClock.getMinutes());
        if (m_timeClock.isBefore()) {
            beforeGroup.check(R.id.vorherButton);
        } else {
            beforeGroup.check(R.id.danachButton);
        }


        builder.setView(mView)
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditTimeClockDialogListener activity = (EditTimeClockDialogListener) getActivity();
                        m_timeClock.setHours((short) hoursPicker.getValue());
                        m_timeClock.setMinutes((short) minutesPicker.getValue());
                        if (beforeGroup.getCheckedRadioButtonId() == R.id.vorherButton) {
                            m_timeClock.setBefore(true);
                        } else if (beforeGroup.getCheckedRadioButtonId() == R.id.danachButton) {
                            m_timeClock.setBefore(false);
                        }
                        activity.onTimeClockPositiveClick(m_timeClock, position);
                        EditTimeClockDialogFragment.this.getDialog().dismiss();

                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditTimeClockDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }

    public void setTimeClock(TimeClock timeClock) {
        this.m_timeClock = timeClock;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
