package com.example.gymandlifestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymandlifestyle.databinding.ActivityHomeBinding;

import db.DBHelper4;

public class HomeActivity extends AppCompatActivity {

    private Button  listaTeretana;
    private TextView prvi;
    private TextView drugi;
    private TextView treci;
    private TextView vesti;
    private ActivityHomeBinding binding;
    private TextView user;

    DBHelper4 DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DB = new DBHelper4(this);

        Intent intent = this.getIntent();
        if(intent !=null) {
            String username = intent.getStringExtra("username");
            String ime = intent.getStringExtra("ime");
            String prezime = intent.getStringExtra("prezime");
            String godine = intent.getStringExtra("godine");

            Toast.makeText(HomeActivity.this, "username je " + username, Toast.LENGTH_SHORT).show();


            binding.user.setText(username);

//            listaTeretana = findViewById(R.id.btnListaTeretana);
            prvi = findViewById(R.id.prvi);
            prvi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent2 = new Intent(getApplicationContext(), TeretaneListActivity.class);
                    intent2.putExtra("username",username);
                    startActivity(intent2);
                }
            });

            drugi = findViewById(R.id.drugi);


            treci = findViewById(R.id.treci);
            treci.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("treci","treci");
                    int kcal = DB.vratiKcalZaUsera(username);
                    Toast.makeText(HomeActivity.this, "Broj kcal " + kcal, Toast.LENGTH_SHORT).show();
                    Intent intent4 = new Intent(getApplicationContext(), TvojNutrucionistaActivity.class);
                    intent4.putExtra("username",username);
                    intent4.putExtra("kcal",kcal);
                    startActivity(intent4);
                }
            });

            vesti = findViewById(R.id.vesti);
            vesti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(HomeActivity.this, VestiActivity.class);
                    Log.i("vaznesenje","neto1");
                    startActivity(i2);
                }
            });
        }






    }
}