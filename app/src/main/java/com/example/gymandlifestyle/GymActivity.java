package com.example.gymandlifestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.gymandlifestyle.databinding.ActivityGymBinding;

import db.DBHelper2;


public class GymActivity extends AppCompatActivity {

    private DBHelper2 DB;
    private ActivityGymBinding binding;
    private Button button;
    private Button mapa;
    private Button komentar;
    private Button treneri;
    private RatingBar ratingStars1;
    private RatingBar ratingStars2;
    private RatingBar ratingStars3;
    private RatingBar ratingStars4;
    float rating1=0;
    float rating2=0;
    float rating3=0;
    float rating4=0;
    float zbir;
    int i=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGymBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DB = new DBHelper2(this);



        komentar = findViewById(R.id.komentar);
        button = findViewById(R.id.button);
        mapa = findViewById(R.id.mapa);
        treneri = findViewById(R.id.treneri);
        ratingStars1 = findViewById(R.id.opremljenost);
        ratingStars2 = findViewById(R.id.osoblje);
        ratingStars3 = findViewById(R.id.prostorije);
        ratingStars4 = findViewById(R.id.higijena);

        ratingStars1.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> { Toast.makeText(GymActivity.this,"Ocenili ste opremljenost",Toast.LENGTH_SHORT).show();
        rating1 = ratingBar.getRating();
        });
        ratingStars1.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            i++;
//            Toast.makeText(GymActivity.this,"Ocenili ste opremljenost",Toast.LENGTH_SHORT).show();
            rating1 = ratingBar.getRating();
        });

        ratingStars2.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
//            Toast.makeText(GymActivity.this,"Ocenili ste osoblje",Toast.LENGTH_SHORT).show();
            i++;
            rating2 = ratingBar.getRating();
        });

        ratingStars3.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
//            Toast.makeText(GymActivity.this,"Ocenili ste prostorije",Toast.LENGTH_SHORT).show();
            i++;
            rating3 = ratingBar.getRating();
        });

        ratingStars4.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
//            Toast.makeText(GymActivity.this,"Ocenili ste higijenu",Toast.LENGTH_SHORT).show();
            i++;
            rating4 = ratingBar.getRating();
        });





        Intent intent = this.getIntent();

        if(intent !=null){
            String username = intent.getStringExtra("username");
            String gym = intent.getStringExtra("gym");

            int imageId = intent.getIntExtra("imageId",R.drawable.nonstop);

            binding.gymImg.setImageResource(imageId);
            binding.gymName.setText(gym);

            button.setOnClickListener(v -> {
                    if(rating1!=0 && rating2!=0 && rating3!=0 && rating4!=0) {
                        zbir = (rating1 + rating2 + rating3 + rating4) / 4;
                        String userAndGym = username + gym;
                        Boolean b = DB.checkUserAndGym(userAndGym);
                        if (b == false) {
                            Boolean insert = DB.insertData(userAndGym, gym, zbir);
                            Toast t = Toast.makeText(GymActivity.this, "Uspešno si ocenio ovu tereteanu", Toast.LENGTH_LONG);
                            t.setGravity(Gravity.CENTER, 0, 0);
                            t.show();
                            float prosek = DB.vratiProsecnuOcenuTeretane(gym);
                            Toast.makeText(GymActivity.this, "Prosek je " + prosek, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GymActivity.this, "Već si ocenio ovu teretanu", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(GymActivity.this, "Nisi sve ocenio", Toast.LENGTH_LONG).show();

                    }
            });


        }


        komentar.setOnClickListener(v -> {
            Intent i = new Intent(GymActivity.this,KomentarActivity.class);

            String gym = intent.getStringExtra("gym");
            String user = intent.getStringExtra("username");
            i.putExtra("user",user);
            i.putExtra("gym",gym);


            startActivity(i);
        });

        mapa.setOnClickListener(v->{
            Intent i = new Intent(getApplicationContext(), MapActivity.class);
            String gym = intent.getStringExtra("gym");
            i.putExtra("gym",gym);
            startActivity(i);
        });

        treneri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(GymActivity.this, TreneriListActivity.class);
                String gym = intent.getStringExtra("gym");
                i2.putExtra("gym",gym);
                i2.putExtra("username",intent.getStringExtra("username"));
                startActivity(i2);


            }
        });

    }
}