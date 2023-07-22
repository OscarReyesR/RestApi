package com.example.navigationmenu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.navigationmenu.Models.ListElementMain;
import com.example.navigationmenu.R;

import java.util.List;

public class ListAdapterMain extends RecyclerView.Adapter<ListAdapterMain.ViewHolder>{

    private List<ListElementMain> myData;
    private LayoutInflater myInflater;
    private Context context;

    private ViewPager viewPager;
    public ListAdapterMain(List<ListElementMain> itemList, Context context)
    {
        this.myInflater=LayoutInflater.from(context);
        this.context=context;
        this.myData=itemList;
    }
    @Override
    public int getItemCount()
    {
        return myData.size();
    }

    @Override
    public ListAdapterMain.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=myInflater.inflate(R.layout.item_recycler_main_layout,null);
        return new ListAdapterMain.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ListAdapterMain.ViewHolder  holder, final int position){
        holder.bindData(myData.get(position));
    }

    public void setItems(List<ListElementMain> items){
        myData=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo,subTitulo;
        ViewHolder(View itemView){
            super(itemView);
            titulo=itemView.findViewById(R.id.TextTitleCard);
            subTitulo=itemView.findViewById(R.id.TextSubTitleCard);
        }

        void bindData(final ListElementMain item){
            titulo.setText(item.getTitulo());
            subTitulo.setText(item.getSubtitulo());
        }
    }
}
