package com.example.gymandlifestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymandlifestyle.databinding.ActivityKomentarBinding;

import java.util.ArrayList;
import java.util.List;

import classes.Komentar;
import classes.ListAdapterKom;
import db.DBHelper3;

public class KomentarActivity extends AppCompatActivity {

    private DBHelper3 DB;
    private ActivityKomentarBinding binding;
    private EditText komentar;
    private Button dodaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKomentarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        DB = new DBHelper3(this);

        komentar = (EditText) findViewById(R.id.komentar);

        dodaj = findViewById(R.id.button2);


        Intent intent = this.getIntent();
        if(intent!=null) {
            String gym = intent.getStringExtra("gym");
            binding.gym.setText(gym);
            String username = intent.getStringExtra("user");

            izlistaj(gym,username,binding);

            dodaj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    komentar.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    String kom = komentar.getText().toString();
                    Boolean b = DB.insertData(username,gym,kom);

                    if(b==true){
                        Toast.makeText(KomentarActivity.this,"Uspesno ste uneli komentar",Toast.LENGTH_SHORT).show();
                        ArrayList<Komentar> komentariArrayList = new ArrayList<>();
                        izlistaj(gym,username,binding);
                    }
                }
            });
        }

    }


    public void izlistaj(String gym,String username,ActivityKomentarBinding binding){
               ArrayList<Komentar> komentariArrayList = new ArrayList<>();
                List<String> userKom = DB.vratiKomentareZaOvuTeretanu(gym);
                for(String uk : userKom){
                    Komentar comm = new Komentar(uk);
                    komentariArrayList.add(comm);
                }

                ListAdapterKom listAdapter = new ListAdapterKom(KomentarActivity.this, komentariArrayList);
                binding.komentari.setAdapter(listAdapter);
            }
}