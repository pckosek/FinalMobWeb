package com.example.pckosek.statusmonitor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class EditDialogFragment extends DialogFragment {

    private TextView tv_id, tv_st, tv_op, tv_usr;
    private View mRootView;

    public EditDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditDialogFragment newInstance(String s_id, String s_st, String s_op, String s_usr) {
        EditDialogFragment frag = new EditDialogFragment();
        Bundle args = new Bundle();
        args.putString("id", s_id);
        args.putString("st", s_st);
        args.putString("op", s_op);
        args.putString("usr", s_usr);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_dialog, container);
        tv_id  = mRootView.findViewById(R.id.lbl_id);
        tv_st  = mRootView.findViewById(R.id.lbl_status);
        tv_op  = mRootView.findViewById(R.id.lbl_op);
        tv_usr = mRootView.findViewById(R.id.lbl_usr);

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String s_id  = getArguments().getString("id", "null");
        String s_st  = getArguments().getString("st", "null");
        String s_op  = getArguments().getString("op", "null");
        String s_usr = getArguments().getString("usr", "null");

        tv_id.setText("Event Number: " + s_id);
        tv_st.setText("Event Action: " +s_st);
        tv_op.setText(s_op);
        tv_usr.setText(s_usr);
    }
}