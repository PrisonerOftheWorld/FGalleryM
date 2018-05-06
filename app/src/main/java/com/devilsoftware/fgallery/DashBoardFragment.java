package com.devilsoftware.fgallery;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devilsoftware.App;
import com.devilsoftware.fgallery.API.Models.Response;


import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;

public class DashBoardFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_item_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            updateList(context);
        }
        return view;
    }


    void updateList(final Context context) {
        final List<String> list = new ArrayList<>();

        // TODO: если поменять слово nature, будут другие картинки), green - цвет картинок
        String key = "8918368-7255bf0de69df27a4ce9491b8";
        App.getAPI().getPhotos(key,"nature",1,"nature","green").enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                Response body = response.body();
                if(body!=null){
                    for(int i = 0; i<body.getHits().size(); i++){
                        list.add(body.getHits().get(i).getPreviewURL());
                    }
                    recyclerView.setAdapter(new PhotoItemRecyclerViewAdapter(context,list,body.getHits()));
                }


            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

            }
        });
    }

}
