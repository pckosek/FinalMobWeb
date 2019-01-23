package com.example.pckosek.statusmonitor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LedActionAdapter extends RecyclerView.Adapter<LedActionAdapter.MyViewHolder> {


    /* ------------------------*/
    /*    member variables     */
    private List<LedAction> ledActionList;
    private RecyclerRowInterface mCallback;

    /* ------------------------*/
    /*    constructor          */
    public LedActionAdapter(List<LedAction> ledActionList, Context context) {
        this.ledActionList = ledActionList;
        this.mCallback = (RecyclerRowInterface) context;
    }


    /* ------------------------------------------*/
    /*    LIFECYCLE METHODS                      */

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        LedAction ledAction = ledActionList.get(position);
        holder.tvActionId.setText(ledAction.getIdString());
        holder.tvActionStatus.setText(ledAction.getStampString());
        holder.tvActionOp.setText(ledAction.getOpString());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LedAction ledAction = ledActionList.get(position);
                mCallback.showDialog(ledAction.getIdString(),
                        ledAction.getStampString(),
                        ledAction.getOpString(),
                        ledAction.getUser_nameString());
                Log.i("CLICKY", "CLICKY" + position);
            }
        });
    }


    /* ------------------------------------------*/
    /*    INTERFACE METHODS                      */

    @Override
    public int getItemCount() {
        return ledActionList.size();
    }

    /* ------------------------------------------*/
    /*    SUPPORT CLASSES                      */

    //   THE [JAVA] CLASS STRUCTURE OF A SINGLE RecyclerView ROW
    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView tvActionId, tvActionStatus, tvActionOp;
        public View rootView;

        public MyViewHolder(View view) {
            super(view);
            rootView = view;
            tvActionId = (TextView) view.findViewById(R.id.rv_id);
            tvActionStatus = (TextView) view.findViewById(R.id.rv_status);
            tvActionOp = (TextView) view.findViewById(R.id.rv_op);

        }
    }

    /* ------------------------------------------*/
    /*    CUSTOM INTERFACE                       */
    public interface RecyclerRowInterface {
        void showDialog(String s_id, String s_st, String s_op, String s_usr);
    }

}
