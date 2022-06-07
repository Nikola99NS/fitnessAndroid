package com.example.gymandlifestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.gymandlifestyle.databinding.ActivityInfoBinding;
import com.example.gymandlifestyle.databinding.ActivityTvojNutrucionistaBinding;

public class TvojNutrucionistaActivity extends AppCompatActivity {

    ActivityTvojNutrucionistaBinding binding;

//
//    private EditText dorucak;
//    private EditText rucak;
//    private EditText vecera;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTvojNutrucionistaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = this.getIntent();
        if(intent !=null) {


            int kalorije = intent.getIntExtra("kcal",0);

            String k = String.valueOf(kalorije);
            binding.kcal.setText(k);

            int d = (int) (kalorije*0.35);
            int r = (int) (kalorije*0.4);
            int v = (int) (kalorije*0.25);

            String dor = String.valueOf(d);
            String ruc = String.valueOf(r);
            String vec = String.valueOf(v);

            binding.dorucak.setText(dor + " kcal");
            binding.rucak.setText(ruc + " kcal");
            binding.vecera.setText(vec + " kcal");


            String[] breakfast = {"omlet=240","Kuvana jaja=180","Topli sendvic=380","Ovsena kasa=300","Vege pizza=350","Smuti=370","Kačamak=330"};
            String[] lunch = {"Pasulj=350","Grašak=250","Kроmpir i batak=330","pirinač i piletina=230","musaka=400","makarone sa sirom=410","gulaš=360","lazanje=420","riba=200"};
            String[] dinner = {"omlet=240","Kuvana jaja=180","Topli sendvic=380","Ovsena kasa=300","Vege pizza=350","Smuti=370","Kačamak=330","Proteinska salata=190","tunjevina=200"};

            int[] imageD = {R.drawable.omlet, R.drawable.jaja, R.drawable.sendvic, R.drawable.ovsene, R.drawable.pizza,
                    R.drawable.smuti, R.drawable.kacamak};
            int[] imageR ={R.drawable.pasulj,R.drawable.grasak,R.drawable.krompir,R.drawable.pirinac,R.drawable.musaka,R.drawable.makarone,R.drawable.gulas,R.drawable.lazanje,R.drawable.riba};
            int[] imageV = {R.drawable.omlet, R.drawable.jaja, R.drawable.sendvic, R.drawable.ovsene, R.drawable.pizza,
                    R.drawable.smuti, R.drawable.kacamak,R.drawable.salata,R.drawable.konzerva};

            int min=0;
            int maxD=6;
            int randomD = (int)Math.floor(Math.random()*(maxD-min+1)+min);
            String[] gramIDor = vratiGramazuINazivJela(d,randomD,breakfast);
            String gram = gramIDor[1];
            String naziv = gramIDor[0];
            binding.dorucakCard.setText(gram + " g " + naziv);
            binding.imgDor.setImageResource(imageD[randomD]);

            int maxR=8;
            int randomR = (int)Math.floor(Math.random()*(maxR-min+1)+min);
            String[] gramIRuc = vratiGramazuINazivJela(r,randomR,lunch);
            String gram2 = gramIRuc[1];
            String naziv2 = gramIRuc[0];
            binding.rucakCard.setText(gram2 + " g "+ naziv2);
            binding.imgRucak.setImageResource(imageR[randomR]);

            int maxV=8;
            int randomV = (int)Math.floor(Math.random()*(maxV-min+1)+min);
            String[] gramIVec = vratiGramazuINazivJela(r,randomV,dinner);
            String gram3=gramIVec[1];
            String naziv3=gramIVec[0];
            binding.veceraCard.setText(gram3 + " g " + naziv3);
            binding.imgVecera.setImageResource(imageV[randomV]);


        }
    }

    public String[] vratiGramazuINazivJela(int d,int random, String[] jelo){
        String dor =jelo[random];
        String[] s = dor.split("=",2);
        String naziv = s[0];
        double kcal = Double.parseDouble(s[1]);
        double d2=Double.valueOf(d);

        double g = (d2/kcal)*100;
        int gr = Integer.valueOf((int) g);

        String[] vrati = {naziv, String.valueOf(gr)};
        return vrati;
    }
}