package com.example.appcare;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcare.Pojos.ProfilePojo;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    Fragemnt_view_1 fragemnt_view_1;
    List<ProfilePojo>profilePojos;

    public ProfileAdapter(Fragemnt_view_1 fragemnt_view_1, List<ProfilePojo> profilePojos) {
        this.fragemnt_view_1 = fragemnt_view_1;
        this.profilePojos = profilePojos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.data,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfilePojo profilePojo=profilePojos.get(position);
        holder.text_name.setText(profilePojo.getName());
        holder.text_dept.setText(profilePojo.getDeptname());
        holder.image_profile.setImageURI(Uri.parse(profilePojo.getImage()));
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragemnt_view_1.deleteProfile(profilePojo);

            }
        });

    }

    @Override
    public int getItemCount() {
        return profilePojos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_profile,image_delete;
        TextView text_name,text_dept;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_profile=itemView.findViewById(R.id.image_icon);
            image_delete=itemView.findViewById(R.id.image_delete);
            text_name=itemView.findViewById(R.id.name_1);
            text_dept=itemView.findViewById(R.id.dept_name_1);


        }
    }
}
