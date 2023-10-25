package com.example.framming;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private EditText edittxtbusca;
    private ImageButton imgbtnpesquisa;
    private TextView txtnomeuser;
    public static List<Item> items = new ArrayList<>();

    public static String ID = null;
    public static String IDPopUp;
    public static RecyclerView recyclerViewPop;
    public static int usadobtn = 0;
    public static String IDUser;

    public static String IDPositionPop;
    public static boolean swtPosition = false;

    public static String IDPositionPopTela;

    public static String nomeusuario;


    View layout;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imgbtndrawer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        drawerLayout = getView().findViewById(R.id.drawer_layout);
        navigationView = getView().findViewById(R.id.nav_view);

        navigationView.bringToFront();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:{
                        Intent intent = new Intent(HomeFragment.this, HomeFragment.class);
                        startActivity(intent);
                        startActivity(new Intent(HomeFragment.this, HomeFragment.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }
                    case R.id.nav_perfil:{
                        startActivity(new Intent(HomeFragment.this, ProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }
                }
                return false;
            }
        });

        edittxtbusca = findViewById(R.id.edittxtbuscap);
        imgbtnpesquisa = findViewById(R.id.imgbtnpesquisa);
        txtnomeuser = findViewById(R.id.textView6);
        imgbtndrawer = findViewById(R.id.imgbtndrawer);

        layout = findViewById(R.id.constraint);
        buscaInfoUser();

        imgbtndrawer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                drawerLayout.openDrawer(Gravity.LEFT);

            }
        });


        edittxtbusca.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, PesquisaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

        recyclerViewPop = findViewById(R.id.recyclerViewPop);
        buscaInfoFilmePopular();
        Switch swthome = findViewById(R.id.swthome);
        swthome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    swtPosition = true;
                    items.clear();
                    buscaInfoFilmeEmBreve();
                }
                else{
                    swtPosition = false;
                    items.clear();
                    buscaInfoFilmePopular();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}