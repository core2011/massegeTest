package ua.com.messagetest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int MAX_MASSEGE_LENGS = 100;
    FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = firebase.getReference("masseges");
    Button button;
    final static String TAG = "myLog";
    EditText editText;
    RecyclerView recyclerView;

    ArrayList<String> massages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.send_massege_but);
        editText = findViewById(R.id.massageIn);
        recyclerView = findViewById(R.id.massegeRecycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final DataAdapter dataAdapter = new DataAdapter(this,massages);
        recyclerView.setAdapter(dataAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();

                if (msg.equals("")){
                    Toast.makeText(getApplicationContext(),"Введите сообщение",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (msg.length()>MAX_MASSEGE_LENGS){
                    Toast.makeText(getApplicationContext(),"Cообщение слишком длинное",Toast.LENGTH_SHORT).show();
                    return;
                }
                myRef.push().setValue(msg);
                editText.setText("");
            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String msg = dataSnapshot.getValue(String.class);
                massages.add(msg);
                dataAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(massages.size());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
