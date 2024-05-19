package com.ln.fragmentbottab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.ln.fragmentbottab.adapter.MyFragmentStateVPHomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VPHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VPHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ViewPager mviewPager;
    private TabLayout mtabLayout;

    private List<Fragment> mfragmentList;

    private MyFragmentStateVPHomeAdapter mhomeAdapter;
    private List<String> mtitleList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VPHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VPHomeFragmen.
     */
    // TODO: Rename and change types and number of parameters
    public static VPHomeFragment newInstance(String param1, String param2) {
        VPHomeFragment fragment = new VPHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_v_p_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mviewPager=view.findViewById(R.id.home_vp);
        mtabLayout=view.findViewById(R.id.tab_layout);
        initdata();

        //这里要注意  这里是一个fragment管理 子级的fragment  要使用getChildFragmentManager
        mhomeAdapter=new MyFragmentStateVPHomeAdapter(getChildFragmentManager(),mfragmentList,mtitleList);
        mviewPager.setAdapter(mhomeAdapter);

        //交互
        mtabLayout.setupWithViewPager(mviewPager);
    }

    private void initdata() {

        mfragmentList =new ArrayList<>();
        ExampleFragment f1 = ExampleFragment.newInstance("新闻", "-");
        ExampleFragment f2 = ExampleFragment.newInstance("热点", "-");
        ExampleFragment f3 = ExampleFragment.newInstance("八卦", "-");
        ExampleFragment f4 = ExampleFragment.newInstance("傻鸟", "-");
        mfragmentList.add(f1);
        mfragmentList.add(f2);
        mfragmentList.add(f3);
        mfragmentList.add(f4);

        mtitleList=new ArrayList<>();
        mtitleList.add("新闻");
        mtitleList.add("热点");
        mtitleList.add("八卦");
        mtitleList.add("傻鸟");
    }
}