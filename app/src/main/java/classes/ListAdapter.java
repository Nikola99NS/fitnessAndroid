package classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gymandlifestyle.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Teretana> {

    public ListAdapter(Context context, ArrayList<Teretana> teretanaArrayList){
        super(context, R.layout.list_item,teretanaArrayList);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)  {

        Teretana t = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView userName = convertView.findViewById(R.id.personName);
        TextView adresa = convertView.findViewById(R.id.adresa);
        TextView ocena = convertView.findViewById(R.id.ocena);

        imageView.setImageResource(t.imageId);
        userName.setText(t.getName());
        adresa.setText(t.getAdresa());
        ocena.setText("Ocena: "+ t.ocena);



        return convertView;
    }

    public void filterList() {
        notifyDataSetChanged();
    }
}
