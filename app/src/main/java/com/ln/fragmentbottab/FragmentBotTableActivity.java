package com.ln.fragmentbottab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ln.fragmentbottab.adapter.MyFragmentStateVPAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentBotTableActivity extends AppCompatActivity {

    private List<Fragment> mfragmentList;
    private ViewPager mviewPager;

    private BottomNavigationView  mbottomNavigationView;
    private MyFragmentStateVPAdapter stateVPAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_bot_table);
        mviewPager=findViewById(R.id.vp);
        mbottomNavigationView=findViewById(R.id.bottom_item_menu);

//        initView();
        initData();
//        创建适配器，view设置适配器
        stateVPAdapter=new MyFragmentStateVPAdapter(getSupportFragmentManager(),mfragmentList);
        mviewPager.setAdapter(stateVPAdapter);

        //ViewPager联动底部导航按钮 添加监听器
        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(FragmentBotTableActivity.this, "当前第" + position + "页", Toast.LENGTH_SHORT).show();

                onPageChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                //根据不同的id改变我们的viewpager  R.id.menu_home使用不了swic 换成if else
                if (itemId == R.id.menu_home) {
                    mviewPager.setCurrentItem(0);
                } else if (itemId == R.id.menu_find) {
                    mviewPager.setCurrentItem(1);
                } else if (itemId == R.id.menu_mine) {
                    mviewPager.setCurrentItem(2);
                }
                return true;
            }
        });
        BadgeDrawable badge = mbottomNavigationView.getOrCreateBadge(R.id.menu_mine);
        badge.setNumber(100);
        badge.setMaxCharacterCount(3);
    }

    //页面切换的处理
    private void onPageChange(int position) {
        switch (position){
            case 0: mbottomNavigationView.setSelectedItemId(R.id.menu_home); break;
            case 1: mbottomNavigationView.setSelectedItemId(R.id.menu_find);break;
            case 2:
                mbottomNavigationView.removeBadge(R.id.menu_mine);
                mbottomNavigationView.setSelectedItemId(R.id.menu_mine);break;
            default:break;
        }
    }

    private void initView() {

    }

    private void initData() {
        mfragmentList=new ArrayList<>();
        VPHomeFragment vpHomeFragmen = VPHomeFragment.newInstance("首页", "-");
        mfragmentList.add(vpHomeFragmen);
        ExampleFragment findFragment = ExampleFragment.newInstance("搜素", "-");
        ExampleFragment mineFragment = ExampleFragment.newInstance("我的", "-");
        mfragmentList.add(findFragment);
        mfragmentList.add(mineFragment);
    }
}