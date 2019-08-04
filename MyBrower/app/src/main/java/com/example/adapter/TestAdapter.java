package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modle.UserInfoBean;
import com.example.mybrower.R;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyHolder> {
    private String[] strings;
    private List<UserInfoBean> userInfoBeans;

    /**
     * 闯入字符数组
     *
     * @param strings
     */
    public TestAdapter(String[] strings) {
        this.strings = strings;
    }


    /**
     * 传入集合数据
     *
     * @param userInfoBeans
     */


    public TestAdapter(List<UserInfoBean> userInfoBeans) {
        this.userInfoBeans = userInfoBeans;
    }

    @NonNull
    @Override

    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.mainInfo.setText(strings[position]);
    }

    @Override
    public int getItemCount() {
        return strings.length;
        //return userInfoBeans.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView mainInfo;
        ImageView imgView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mainInfo = itemView.findViewById(R.id.main_info);
        }
    }

}
