package com.example.gymandlifestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.gymandlifestyle.databinding.ActivityTeretaneListBinding;

import java.util.ArrayList;

import classes.ListAdapter;
import classes.Teretana;
import db.DBHelper2;

public class TeretaneListActivity extends AppCompatActivity {

    private ActivityTeretaneListBinding binding;

    private DBHelper2 DB ;
    private ArrayList<Teretana> teretanaArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityTeretaneListBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());

        kreiraj(teretanaArrayList);
    }

    private void kreiraj(ArrayList<Teretana> teretanaArrayList) {
        DB = new DBHelper2(this);
        Intent intent = this.getIntent();

        if(intent !=null) {
            String username = intent.getStringExtra("username");
        }

        int[] imageId = {R.drawable.nonstop, R.drawable.prime, R.drawable.synergy, R.drawable.classic, R.drawable.xgym,
                R.drawable.edu, R.drawable.ozzy, R.drawable.best};
        String[] gym = {"Non stop", "Prime", "Synergy", "Classic gym", "X-gym", "Edu fit",
                "Ozzy", "Best fit"};
        String[] adresa = {"Narodnih heroja 1", "Svetozara Miletića 43", "Veselina Malseše 74", "Bulevar oslobođenja 83", "Tozin sokak 8", "Vojvode Putnika 68b",
                "Bulevar patrijarha Pavla 6", "Bulevar oslobođenja 127"};

        if(teretanaArrayList.isEmpty()) {
            for (int i = 0; i < imageId.length; i++) {
                float ocena = DB.vratiProsecnuOcenuTeretane(gym[i]);
                Teretana t = new Teretana(gym[i], adresa[i], ocena, imageId[i]);
                teretanaArrayList.add(t);
            }
        }
        ListAdapter listAdapter = new ListAdapter(TeretaneListActivity.this, teretanaArrayList);

        binding.listView.setAdapter(listAdapter);
        binding.listView.setClickable(true);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String username = intent.getStringExtra("username");

                Intent i = new Intent(TeretaneListActivity.this,GymActivity.class);
                i.putExtra("username",username);
                i.putExtra("gym",gym[position]);
                float ocena = DB.vratiProsecnuOcenuTeretane(gym[position]);
                i.putExtra("rate",ocena);

                i.putExtra("imageId",imageId[position]);
                startActivity(i);
            }
        });


        EditText searchText = findViewById(R.id.searchGym);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<Teretana> filteredGym = new ArrayList<>() ;

        for (Teretana gym : teretanaArrayList ){
            if(gym.getName().toLowerCase().contains(text.toLowerCase())){
                filteredGym.add(gym);
            }
        }
        ListAdapter listAdapter = new ListAdapter(TeretaneListActivity.this, filteredGym);
        listAdapter.filterList();
        kreiraj(filteredGym);
    }
}