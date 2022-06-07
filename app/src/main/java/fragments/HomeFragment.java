package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import classes.ApiUtilities;
import com.example.gymandlifestyle.R;

import java.util.ArrayList;

import classes.AdapterVesti;
import classes.MainNews;
import classes.ModelClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    String api = "d5b01d23a8ef460c813a1e21908c5c93";
    ArrayList<ModelClass> modelClassArrayList;
    AdapterVesti adapter;
    String country = "in";
    private RecyclerView recyclerViewOfHome;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.homefragment,null);

        recyclerViewOfHome = v.findViewById(R.id.recycleViewOfHome);
        modelClassArrayList = new ArrayList<>();
        recyclerViewOfHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdapterVesti(getContext(), modelClassArrayList);
        recyclerViewOfHome.setAdapter(adapter);

        findNews();

        return v;

    }

    private void findNews() {

        ApiUtilities.getApiInterface().getNews(country, 100, api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }
}
