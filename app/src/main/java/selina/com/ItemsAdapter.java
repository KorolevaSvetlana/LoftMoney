package selina.com;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static selina.com.R.id.price_view;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Item> mItemsList = new ArrayList<Item>();
    private int position;

    public ItemsAdapter(int position) {this.position=position;}

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.item_view, null);
        if (position == 1) {
            TextView textView = itemView.findViewById(R.id.price_view);
            textView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.apple_green));
        }
        return new ItemViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bindItem(mItemsList.get(position));
    }

    public void AddItem(Item item) {
        mItemsList.add(item);
        notifyDataSetChanged();
    }

    public void  clearItems() {
        mItemsList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameView;
        private TextView mPriceView;

        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

        mNameView =itemView.findViewById(R.id.name_view);
        mPriceView =itemView.findViewById(price_view);
        }

        public void bindItem (final Item item){
            mNameView.setText(item.getName());
            mPriceView.setText(
                    mPriceView.getContext().getResources().getString(R.string.price_with_currency, String.valueOf(item.getPrice()))
                    );
        }
    }
}

