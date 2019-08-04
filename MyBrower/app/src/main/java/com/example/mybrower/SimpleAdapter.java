package com.example.mybrower;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
    //List指的是集合.<>是泛型
    private List<MainContent> mainContentList;//全局变量
  //  private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.main_image);
            textView = view.findViewById(R.id.main_info);
        }
    }

    /**
     * SimpleAdapter(@NonNull List<MainContent> lists,Context context)
     * 之前这么写，是需要在  SimpleAdapter simpleAdapter = new SimpleAdapter(mainContentList)这里
     * 传递MainActivity的context过来的
     * 所以按照之前的写法( public SimpleAdapter(@NonNull List<MainContent> lists,Context context))
     * 在MainActivity中初始化的时候是这样写的：SimpleAdapter simpleAdapter = new SimpleAdapter(mainContentList,this)
     **/
    /**
     * 你现在的写法
     *
     * @param lists
     */
    public SimpleAdapter(@NonNull List<MainContent> lists) {
        this.mainContentList = lists;
    }

    /**
     * 旧写法

    public SimpleAdapter(@NonNull List<MainContent> lists, Context context) {
        this.mainContentList = lists;
        this.context = context;
    }
*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from(parent.getContext())这句话是需要依赖到activity的上下文(context)的

        //创建holder之前，要有一个视图view
        //你现在下发的用法
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false);
        //旧写法的用法
        //View view = LayoutInflater.from(context).inflate(R.layout.content_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MainContent mainContent = mainContentList.get(position);

        holder.imageView.setImageResource(mainContent.getImageId());
        holder.textView.setText(mainContent.getName());
    }

    @Override
    public int getItemCount() {
        return mainContentList.size();
        // return strings.length;
        //return userInfoBeans.size();
    }


}
