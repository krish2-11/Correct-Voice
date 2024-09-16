package com.example.correctvoice.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.correctvoice.Model.AlreadyFragment;
import com.example.correctvoice.fragments.BusinessFragment;
import com.example.correctvoice.fragments.EntertainmentFragment;
import com.example.correctvoice.fragments.HealthFragment;
import com.example.correctvoice.fragments.ScienceFragment;
import com.example.correctvoice.fragments.SportsFragment;
import com.example.correctvoice.fragments.TechnologyFragment;
import com.example.correctvoice.fragments.TopFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MyViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public MyViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return AlreadyFragment.getTop();
            case 1:
                return AlreadyFragment.getEntertainment();
            case 2:
                return AlreadyFragment.getSports();
            case 3:
                return AlreadyFragment.getBusiness();
            case 4:
                return AlreadyFragment.getScience();
            case 5:
                return AlreadyFragment.getTechnology();
            case 6:
                return AlreadyFragment.getHealth();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
