package com.example.appcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    Button button_save_data,button_view_data;
    FrameLayout frameLayout_1;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragemnt_view_1 fragemnt_view_1;
    Fragemnt_save_1 fragemnt_save_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_save_data=findViewById(R.id.btn_save);
        button_view_data=findViewById(R.id.btn_view_data);
        frameLayout_1=findViewById(R.id.frame_layout_1);

         fragemnt_save_1=new Fragemnt_save_1();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_1,fragemnt_save_1);
        fragmentTransaction.commit();


        button_save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 fragemnt_save_1=new Fragemnt_save_1();
                 fragmentManager=getSupportFragmentManager();
                 fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragemnt_view_1);
                fragmentTransaction.add(R.id.frame_layout_1,fragemnt_save_1);
                fragmentTransaction.commit();


            }
        });
        button_view_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 fragemnt_view_1=new Fragemnt_view_1();
                 fragmentManager=getSupportFragmentManager();
                 fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout_1,fragemnt_view_1);
                fragmentTransaction.commit();

            }
        });




    }
}