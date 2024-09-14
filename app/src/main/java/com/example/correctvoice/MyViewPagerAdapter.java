package com.example.correctvoice;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
                return new TopFragment();
            case 1:
                return new EntertainmentFragment();
            case 2:
                return new SportsFragment();
            case 3:
                return new BusinessFragment();
            case 4:
                return new ScienceFragment();
            case 5:
                return new TechnologyFragment();
            case 6:
                return new HealthFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
