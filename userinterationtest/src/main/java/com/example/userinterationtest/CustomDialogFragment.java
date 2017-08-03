package com.example.userinterationtest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

/**
 * Created by zhouyiran on 2017/8/1.
 */

public class CustomDialogFragment extends DialogFragment {

    EditText mUsername, mPassword;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.custom_dialog_fragmnet, null);
        mUsername = view.findViewById(R.id.id_txt_username);
        mPassword = view.findViewById(R.id.id_txt_password);
        builder.setView(view)
                .setPositiveButton("Login In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onLoginInterface loginInterface = (onLoginInterface) getActivity();
                        loginInterface.onLoginInputComplete(mUsername.getText().toString(), mPassword.getText().toString());
                    }
                }).setNegativeButton("Cancel", null);
        return builder.create();
    }

    public interface onLoginInterface {

        void onLoginInputComplete(String account, String password);
    }
}
