package ua.com.messagetest;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {

    ArrayList <String> massagers;
    LayoutInflater layoutInflater;


    public DataAdapter(Context context, ArrayList<String> massagers) {
        this.massagers = massagers;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.item_massage,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String msg = massagers.get(position);
        holder.massage.setText(msg);
    }

    @Override
    public int getItemCount() {
        return massagers.size();
    }
}
