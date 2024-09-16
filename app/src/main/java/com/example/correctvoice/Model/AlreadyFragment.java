package com.example.correctvoice.Model;

import androidx.fragment.app.Fragment;

import com.example.correctvoice.fragments.BusinessFragment;
import com.example.correctvoice.fragments.EntertainmentFragment;
import com.example.correctvoice.fragments.HealthFragment;
import com.example.correctvoice.fragments.ScienceFragment;
import com.example.correctvoice.fragments.SportsFragment;
import com.example.correctvoice.fragments.TechnologyFragment;
import com.example.correctvoice.fragments.TopFragment;

public class AlreadyFragment {
        static Fragment top = new TopFragment();
        static Fragment entertainment = new EntertainmentFragment();
        static Fragment business = new BusinessFragment();
        static Fragment health = new HealthFragment();
        static Fragment science = new ScienceFragment();
        static Fragment sports = new SportsFragment();
        static Fragment technology = new TechnologyFragment();

    public static Fragment getTop() {
        return top;
    }

    public static Fragment getEntertainment() {
        return entertainment;
    }

    public static Fragment getBusiness() {
        return business;
    }

    public static Fragment getHealth() {
        return health;
    }

    public static Fragment getScience() {
        return science;
    }

    public static Fragment getSports() {
        return sports;
    }

    public static Fragment getTechnology() {
        return technology;
    }
}
