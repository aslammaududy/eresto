package me.aslammaududy.eresto.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.aslammaududy.eresto.Model.Menu;
import me.aslammaududy.eresto.R;

public class OrderMenuAdapter extends RecyclerView.Adapter<OrderMenuAdapter.OrderMenuViewHolder> {
    List<Menu> orderedMenuList;

    public OrderMenuAdapter(List<Menu> orderedMenuList) {
        this.orderedMenuList = orderedMenuList;
    }

    @NonNull
    @Override
    public OrderMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_menu, parent, false);

        OrderMenuViewHolder orderMenuViewHolder = new OrderMenuViewHolder(view);
        return orderMenuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderMenuViewHolder holder, int position) {
        holder.orderedMenuName.setText(orderedMenuList.get(position).getName());
        holder.orderedMenuImage.setImageResource(orderedMenuList.get(position).getImageResId());
    }

    @Override
    public int getItemCount() {
        return orderedMenuList.size();
    }

    public class OrderMenuViewHolder extends RecyclerView.ViewHolder {
        private TextView orderedMenuName;
        private ImageView orderedMenuImage;

        public OrderMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            orderedMenuImage = itemView.findViewById(R.id.ordered_menu_img);
            orderedMenuName = itemView.findViewById(R.id.ordered_menu_name);
        }
    }
}
