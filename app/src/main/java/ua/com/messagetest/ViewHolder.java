package ua.com.messagetest;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{

    TextView massage;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        massage = itemView.findViewById(R.id.textView);
    }
}
