package com.example.appcare;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcare.Pojos.ProfilePojo;

import java.util.List;

import static com.example.appcare.Room.AppDatabase.getAppDatabase;

public class Fragemnt_view_1 extends Fragment {
    RecyclerView recyclerView_1;
    ProfileAdapter profileAdapter_1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fargemnt_view_data, container, false);
        recyclerView_1=view.findViewById(R.id.recyele_view);
        recyclerView_1.setLayoutManager(new LinearLayoutManager(getContext()));
        getSavedProfiles();
        return view;
    }

    private void getSavedProfiles() {
        new AsyncTask<Void, Void, List<ProfilePojo>>() {
            @Override
            protected List<ProfilePojo> doInBackground(Void... voids) {
                return getAppDatabase(getActivity()).profileDao().getAllProfile();

            }

            @Override
            protected void onPostExecute(List<ProfilePojo> profilePojosList) {
                super.onPostExecute(profilePojosList);
                profileAdapter_1=new ProfileAdapter(Fragemnt_view_1.this,profilePojosList);
                recyclerView_1.setAdapter(profileAdapter_1);
                profileAdapter_1.notifyDataSetChanged();




            }
        }.execute();
    }

    public void deleteProfile(ProfilePojo profilePojo) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                 getAppDatabase(getActivity()).profileDao().delete(profilePojo);
                 return  null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getSavedProfiles();
                Toast.makeText(getActivity(), "Profile Deleted  Successfully", Toast.LENGTH_SHORT).show();
            }
        }.execute();


    }
}
