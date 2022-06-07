package classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gymandlifestyle.R;

import java.util.ArrayList;

import db.DBHelper5;

public class ListAdapterTreneri extends ArrayAdapter<Trener> {


    private DBHelper5 db;

    public ListAdapterTreneri(Context context, ArrayList<Trener> trenerArrayList){
        super(context, R.layout.activity_trener_item,trenerArrayList);
    }

    public void setDB(DBHelper5 db){
        this.db = db;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)  {


        Trener trener =getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_trener_item,parent,false);
        }

        int[] imageId = {R.drawable.prva, R.drawable.druga, R.drawable.treca, R.drawable.cetvrta, R.drawable.peta,
                R.drawable.sesta, R.drawable.sedma, R.drawable.osma,R.drawable.deveta,R.drawable.deseta,R.drawable.jedanaesta,R.drawable.dvanaesta,R.drawable.trinaesta,R.drawable.cetrnaesta,R.drawable.petnaesta};

        ImageView imageView = convertView.findViewById(R.id.profile_pic);

        TextView name = convertView.findViewById(R.id.personName);
        TextView godine  = convertView.findViewById(R.id.godine);
        TextView kursevi = convertView.findViewById(R.id.kursevi);
        Button zakazi = convertView.findViewById(R.id.zakazi);


        int idSlike = trener.idSlike;
        name.setText(trener.getIme());
        godine.setText(trener.getGodine());
        kursevi.setText("Kursevi : " + trener.getKursevi());


        for (int i=0;i<imageId.length;i++) {
            if(i==idSlike){
                imageView.setImageResource(imageId[i]);
            }
        }

        zakazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u = trener.getUser();
                String t = trener.getIme();

                if(!db.checkUser(u, t)) {
                    int broj = db.vratiBrojSlobodnihMesta(t);

                    if (broj > 0) {
                        db.insertData(u, t);
                        int broj2 = broj - 1;
                        Toast.makeText(getContext().getApplicationContext(),"Broj preotalih mesta " + broj2,Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext().getApplicationContext(),"Mesta su popunjena",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext().getApplicationContext(),"Vec ste se prijavili kod ovog trenera",Toast.LENGTH_SHORT).show();
                }
            }
        });


        return convertView;
    }
}
