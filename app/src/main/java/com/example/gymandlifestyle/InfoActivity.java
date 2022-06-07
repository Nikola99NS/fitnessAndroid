package com.example.gymandlifestyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gymandlifestyle.databinding.ActivityInfoBinding;

import db.DBHelper4;

public class InfoActivity extends AppCompatActivity{

//    private Spinner aktivnosti;
    private EditText aktivnost;
    private EditText ime;
    private EditText prezime;
    private EditText godine;
    private EditText masti;

    private Button next;

    ActivityInfoBinding binding;

    DBHelper4 DB;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"notifikacija")
                .setSmallIcon(R.drawable.ic_baseline_login_24)
                .setContentTitle("Registracija")
                .setContentText("Uspesno ste registrovali")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        Intent intent = this.getIntent();
        if(intent !=null) {
            String user = intent.getStringExtra("username");


            ime = findViewById(R.id.ime);
            prezime = findViewById(R.id.prezime);
            godine = findViewById(R.id.godine);
            aktivnost = findViewById(R.id.aktivnosti);
            masti = findViewById(R.id.masti);
            next = findViewById(R.id.next);


            DB = new DBHelper4(this);


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String g = godine.getText().toString();
                    String i = ime.getText().toString();
                    String p = prezime.getText().toString();
                    int god =  Integer.parseInt(g);
                    String a = aktivnost.getText().toString();
                    int akt = Integer.parseInt(a);
                    String m = masti.getText().toString();
                    int mas = Integer.parseInt(m);

                    int kcal = izrKalorije(god, akt, mas);

                    Boolean insert = DB.insertData(user,kcal,i,p,g);

                    Toast.makeText(InfoActivity.this,"Uspesno ste uneli kalorije za usera",Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(getApplicationContext(), HomeActivity.class);
                    in.putExtra("username",user);
                    in.putExtra("ime",ime.getText().toString());
                    in.putExtra("prezime",prezime.getText().toString());
                    in.putExtra("godine",godine.getText().toString());
                    startActivity(in);

                    notificationManager.notify(100,builder.build());
                }
            });
    }
    }

    public int izrKalorije(int god,int akt,int mas){
        int g=0;double m=0;double a=0;
        if(god>15 && god<=25){
            g=5;
        }else if(god>25 && god<=39){
            g=4;
        }else if(g>39){
            g=3;
        }
        if(akt==5){
            a=3;
        }else if(akt==4){
            a=2.5;
        }else if(akt==3){
            a=2;
        }else if(akt==2){
            a=1.5;
        }else if(akt==1){
            a=1.2;
        }
        if(m<11){
            m=3;
        }else if(m<16){
            m=2.5;
        }else if(m<26){
            m=2;
        }
        Log.i("kalorije1", String.valueOf(g));
        Log.i("kalorije2", String.valueOf(a));
        Log.i("kalorije3", String.valueOf(m));



        return (int) (g*a*m*100);
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "studentChannel";
            String description = "Channel for nesto";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifikacija",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

}