package com.example.darpal.themoviesapp;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.darpal.themoviesapp.Adapter.NowPlaying_RecyclerAdapter;
import com.example.darpal.themoviesapp.Getter_Setter.GetterSetter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingTabFragment extends Fragment {

    String key = "75b9d26d4dcc7e83af97eb5251c8a158";
    RecyclerView recyclerView;
    String URL ="https://api.themoviedb.org/3/movie/now_playing?api_key=" + key;
    ArrayList<GetterSetter> arrayList = new ArrayList<>();
    NowPlaying_RecyclerAdapter nowPlaying_recyclerAdapter;

    String imgURL = "poster_path";
    String Moviename = "title";
    String RelDate = "release_date";
    String SynDetails = "overview";
    String Ratemovie = "vote_average";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_now_playing_tab, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.nowPlayingRecycler);

        nowPlaying_recyclerAdapter = new NowPlaying_RecyclerAdapter(getActivity(), arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(nowPlaying_recyclerAdapter);

        Log.d("URL VALUE", URL);
        new Getdata().execute();
        return view;
    }

    private class Getdata extends AsyncTask<Void, Void,Void> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("URLDATA",URL);
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Please wait");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("FIrst Line in DoIn", "inside the loop");
            ServiceHandler serviceHandler = new ServiceHandler();
            String result = serviceHandler.GetHTTPData(URL);
            try {
                JSONObject object = new JSONObject(result);
                JSONArray jsonArray = object.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String movie = jsonObject.getString(Moviename);
                    String date = jsonObject.getString(RelDate);
                    String rate = jsonObject.getString(Ratemovie).toString();
                    String syn = jsonObject.getString(SynDetails);
                    String img = jsonObject.getString(imgURL);

                    GetterSetter setterGetter = new GetterSetter();
                    setterGetter.setMoviename(movie);
                    setterGetter.setRelease(date);
                    setterGetter.setRate(rate);
                    setterGetter.setSynopsis(syn);
                    setterGetter.setImgURL(img);

                    arrayList.add(setterGetter);
                    Log.e("Inserted Data",URL);
                }
            } catch (Exception e) {
                    e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            Log.e("MSG","In Post Execute");
            //nowPlaying_recyclerAdapter = new NowPlaying_RecyclerAdapter(getActivity(),arrayList);
            //recyclerView.setAdapter(nowPlaying_recyclerAdapter);
            nowPlaying_recyclerAdapter.notifyDataSetChanged();
        }
    }

}
