package com.example.navigationmenu;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.navigationmenu.adapters.ViewPagerAdapter;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPage1;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPage10;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPage2;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPage3;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPage4;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPage5;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPage6;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPage7;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPage8;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPage9;
import com.example.navigationmenu.fragments.fragmentspager.FragmentPageIntroduction;

public class Rules extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules_layout);
        viewPager=findViewById(R.id.viewPager);
        ViewPagerAdapter vpAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new FragmentPageIntroduction(),"Introducción");
        vpAdapter.addFragment(new FragmentPage1(),"Regla 1");
        vpAdapter.addFragment(new FragmentPage2(),"Regla 2");
        vpAdapter.addFragment(new FragmentPage3(),"Regla 3");
        vpAdapter.addFragment(new FragmentPage4(),"Regla 4");
        vpAdapter.addFragment(new FragmentPage5(),"Regla 5");
        vpAdapter.addFragment(new FragmentPage6(),"Regla 6");
        vpAdapter.addFragment(new FragmentPage7(),"Regla 7");
        vpAdapter.addFragment(new FragmentPage8(),"Regla 8");
        vpAdapter.addFragment(new FragmentPage9(),"Regla 9");
        vpAdapter.addFragment(new FragmentPage10(),"Regla 10");
        viewPager.setAdapter(vpAdapter);
    }
}
