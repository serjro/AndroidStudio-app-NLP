package com.example.sqlitetest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
public class SampleDialogFragment extends DialogFragment {

    public SampleDialogFragment() {        super();
    }



    final static CharSequence[] Options = {"Режим обучения","Случайный выбор", "Пользовательский"};
    int choice;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MainActivity ret = new MainActivity();
        AlertDialog.Builder builder = new AlertDialog
                .Builder(getActivity());
        builder.setTitle("Настройки");
        builder.setSingleChoiceItems(Options, MainActivity.set1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int
                            idx) {
                        choice = idx;
// Отмечаем выбранное
                    }
                });
        builder.setPositiveButton("Ок",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int
                            which) {

                        ret.setTitle("123");
                        //settitle("");
// Пьем

                    }
                });
        return builder.create();
    }
}