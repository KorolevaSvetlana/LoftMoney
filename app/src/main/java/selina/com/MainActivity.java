package selina.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 100;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button callAddButton=findViewById(R.id.call_add_item_activity);
        callAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddItemActivity.class), REQUEST_CODE);
            }
        });

        RecyclerView recyclerView =findViewById(R.id.budget_item_list);
        adapter = new ItemsAdapter();
        recyclerView.setAdapter(adapter);
        adapter.AddItem(new Item("Бензин", 1200));
        adapter.AddItem(new Item("Сапоги", 4500));
        adapter.AddItem(new Item("Куртка мембранная р.42", 5600));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int price;
        try {
            price=Integer.parseInt(data.getStringExtra("price"));
        } catch(NumberFormatException e) {
            price = 0;
        }

        if (requestCode==REQUEST_CODE && resultCode==RESULT_OK) {
            adapter.AddItem(new Item(data.getStringExtra("name"), price));
        }
    }
}
