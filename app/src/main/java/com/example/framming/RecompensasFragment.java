package com.example.framming;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RecompensasFragment extends Fragment {

    public static RecompensasFragment getInstance(){
        RecompensasFragment fragment = new RecompensasFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_recompensas, parent, false);
        return view;
    }
}