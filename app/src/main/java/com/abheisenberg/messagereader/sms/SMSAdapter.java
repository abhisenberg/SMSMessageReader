package com.abheisenberg.messagereader.sms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.abheisenberg.messagereader.R;
import com.abheisenberg.messagereader.utils.BaseViewHolder;

import java.util.ArrayList;

public class SMSAdapter extends RecyclerView.Adapter<SMSAdapter.ViewHolder> {

    ArrayList<SMS> smsArrayList;
    Context context;

    public SMSAdapter(Context context, ArrayList<SMS> smsArrayList){
        this.smsArrayList = smsArrayList;
        this.context = context;
    }

    public void updateList(ArrayList<SMS> list){
        this.smsArrayList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return smsArrayList.size();
    }

    class ViewHolder extends BaseViewHolder {

        TextView tv_sender, tv_body, tv_date, tv_amount;
        LinearLayout ll_parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_amount = itemView.findViewById(R.id.tv_amount);
            tv_body = itemView.findViewById(R.id.tv_body);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_sender = itemView.findViewById(R.id.tv_sender);
            ll_parent = itemView.findViewById(R.id.ll_parent);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            if(position != RecyclerView.NO_POSITION){
                final SMS currentSMS = smsArrayList.get(position);

                String displayAmount = "₹"+currentSMS.getAmount();
                String displayDate = currentSMS.getDate().substring(0,10);

                tv_amount.setText(displayAmount);
                tv_sender.setText(currentSMS.getSenderName());
                tv_date.setText(displayDate);

                tv_body.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TransitionManager.beginDelayedTransition(ll_parent);
                        tv_body.setText(currentSMS.getBody());
                    }
                });
            }
        }

        @Override
        protected void clear() {
            tv_amount.setText("");
            tv_sender.setText("");
            tv_body.setText(context.getResources().getString(R.string.view_full_msg));
            tv_date.setText("");
        }
    }
}
