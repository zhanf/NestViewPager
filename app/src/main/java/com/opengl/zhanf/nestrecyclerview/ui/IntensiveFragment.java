package com.opengl.zhanf.nestrecyclerview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.opengl.zhanf.nestrecyclerview.R;
import com.opengl.zhanf.nestrecyclerview.adapter.ZhanfAdapter;

public class IntensiveFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String CURRENT_POSITION = "current_position";

    private RecyclerView rvIntensiveReview;
    private ZhanfAdapter innerAdapter;
    private int currentPosition;

    public IntensiveFragment() {
    }

    public static IntensiveFragment newInstance(int position) {
        IntensiveFragment fragment = new IntensiveFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT_POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPosition = getArguments().getInt(CURRENT_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intensive, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvIntensiveReview = view.findViewById(R.id.rv_intensive_review);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rvIntensiveReview.setRecycledViewPool(viewPool);
        innerAdapter = new ZhanfAdapter(getContext());
        rvIntensiveReview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvIntensiveReview.setAdapter(innerAdapter);

        new PagerSnapHelper().attachToRecyclerView(rvIntensiveReview);
        ((TextView) view.findViewById(R.id.tv_position)).setText("垂直方向上的position:"+currentPosition + "");

    }

}