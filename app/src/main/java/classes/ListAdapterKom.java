package classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gymandlifestyle.R;

import java.util.ArrayList;

public class ListAdapterKom extends ArrayAdapter<Komentar> {

    public ListAdapterKom(Context context, ArrayList<Komentar> komentari){
        super(context, R.layout.komentar_item,komentari);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)  {

        Komentar k = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.komentar_item,parent,false);
        }

        TextView user = convertView.findViewById(R.id.user);
        TextView kom = convertView.findViewById(R.id.komentar);


        user.setText(k.username);
        kom.setText(k.komentar);




        return convertView;
    }
}
