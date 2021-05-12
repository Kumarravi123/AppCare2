package com.example.appcare;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appcare.Pojos.ProfilePojo;
import com.example.appcare.Room.AppDatabase;

import java.io.File;
import java.io.IOException;
import java.util.List;

import id.zelory.compressor.Compressor;

import static android.app.Activity.RESULT_OK;
import static com.example.appcare.Room.AppDatabase.getAppDatabase;

public class Fragemnt_save_1 extends Fragment {
    private static final int PICK_IMAGE = 1;
    ImageView imageView_1;
    EditText edt_name, edt_deptname;
    Button btn_submit_1;
    AppDatabase appDatabase;
    Uri picturePath;
    List<ProfilePojo> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragemt_save, container, false);
        imageView_1 = view.findViewById(R.id.image_view);
        edt_name = view.findViewById(R.id.edit_name);
        edt_deptname = view.findViewById(R.id.edit_dept);
        btn_submit_1 = view.findViewById(R.id.btn_submit);
        getSavedProfiles();
        btn_submit_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (TextUtils.isEmpty(picturePath)) {
                    Toast.makeText(getContext(), "please select a picture from gallery", Toast.LENGTH_SHORT).show();
                } else*/ if (TextUtils.isEmpty(edt_name.getText().toString())) {
                    Toast.makeText(getContext(), "please enter name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edt_deptname.getText().toString())) {
                    Toast.makeText(getActivity(), "please enter dept name", Toast.LENGTH_SHORT).show();
                } else if (isExists()){
                    Toast.makeText(getActivity(), "Already this name exists", Toast.LENGTH_SHORT).show();
                }else {
                    saveProfile();
                }


            }
        });
        imageView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickphoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickphoto, PICK_IMAGE);

            }
        });

        return view;
    }
    boolean isExists(){
    boolean isExist_bool = false;
        if (list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().toString().equals(edt_name.getText().toString()) && list.get(i).getDeptname().equals(edt_deptname.getText().toString())) {
                    isExist_bool = true;
                    Toast.makeText(getActivity(), "Already this name exists", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
        return isExist_bool;

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

              list = profilePojosList;


            }
        }.execute();
    }

    private void saveProfile() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                ProfilePojo profilePojo = new ProfilePojo();
                profilePojo.setName(edt_name.getText().toString());
                profilePojo.setDeptname(edt_deptname.getText().toString());
                profilePojo.setImage(String.valueOf(picturePath));
               // profilePojo.setImage("");
                getAppDatabase(getActivity()).profileDao().insert(profilePojo);


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Toast.makeText(getActivity(), "Profile Saved Successfully", Toast.LENGTH_SHORT).show();
                edt_name.setText("");
                edt_deptname.setText("");


                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE)
        {
            picturePath=data.getData();
            imageView_1.setImageURI(picturePath);
        }
    }
    }
