package com.opengl.zhanf.nestrecyclerview.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.opengl.zhanf.nestrecyclerview.R;
import com.opengl.zhanf.nestrecyclerview.adapter.ZhanfPagerAdapter;
import com.opengl.zhanf.nestrecyclerview.widget.VerticalViewPager;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_outer;
    private VerticalViewPager viewPager;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {
//        rv_outer = findViewById(R.id.rv_outer);
//        rv_outer.setAdapter(new OuterAdapter(this));
//        rv_outer.setLayoutManager(new LinearLayoutManager(this));
//        new NestSnapHelper().attachToRecyclerView(rv_outer);

        viewPager = findViewById(R.id.vvp);
        final ZhanfPagerAdapter adapter = new ZhanfPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() < adapter.getCount() - 1 ? viewPager.getCurrentItem() + 1 : 0);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println(position + "," + positionOffset + "," + positionOffsetPixels + ",");
            }

            @Override
            public void onPageSelected(int position) {
                System.out.println(position + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println(state + "");
            }
        });
    }
}
