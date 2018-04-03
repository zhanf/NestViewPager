package com.opengl.zhanf.nestrecyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.opengl.zhanf.nestrecyclerview.R;

/**
 * Created by zhanf on 2018/1/16.
 */

public class ZhanfAdapter extends RecyclerView.Adapter<ZhanfAdapter.Holder> {
    private Context context;

    public ZhanfAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_inner_0, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (position == 0) {
            holder.cl_inner.setBackgroundColor(Color.parseColor("#880000ff"));
        } else if (1 == position) {
            holder.cl_inner.setBackgroundColor(Color.parseColor("#88ff0000"));
        } else {
            holder.cl_inner.setBackgroundColor(Color.parseColor("#88cccccc"));

        }
        for (int i = 0; i < holder.cl_inner.getChildCount(); i++) {
            LinearLayout layout = (LinearLayout) holder.cl_inner.getChildAt(i);
            for (int j = 0; j < 4; j++) {
                TextView textView = new TextView(context);
                textView.setText(i + "");
                layout.addView(textView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ConstraintLayout cl_inner;

        public Holder(View itemView) {
            super(itemView);
            cl_inner = (ConstraintLayout) itemView.findViewById(R.id.cl_inner);
        }
    }
}
