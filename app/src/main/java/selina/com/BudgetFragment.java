package selina.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BudgetFragment extends Fragment {

    private static final int REQUEST_CODE = 100;
    private ItemsAdapter adapter;
    private int position;

    public BudgetFragment(int position) {this.position=position;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_budget, null);

        Button callAddButton=view.findViewById(R.id.call_add_item_activity);
        callAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), AddItemActivity.class), REQUEST_CODE);
            }
        });

        RecyclerView recyclerView =view.findViewById(R.id.budget_item_list);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation()));

        adapter = new ItemsAdapter(position);
        recyclerView.setAdapter(adapter);
        adapter.AddItem(new Item("Бензин", 1200));
        adapter.AddItem(new Item("Сапоги", 4500));
        adapter.AddItem(new Item("Куртка мембранная р.42", 5600));



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int price;
        try {
            price=Integer.parseInt(data.getStringExtra("price"));
        } catch(NumberFormatException e) {
            price = 0;
        }

        if (requestCode==REQUEST_CODE && resultCode== Activity.RESULT_OK) {
            adapter.AddItem(new Item(data.getStringExtra("name"), price));
        }
    }

}
