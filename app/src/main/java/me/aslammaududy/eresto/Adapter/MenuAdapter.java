package me.aslammaududy.eresto.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;
import java.util.List;

import me.aslammaududy.eresto.Model.Menu;
import me.aslammaududy.eresto.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<Menu> menuList, orderedMenu;
    private OnOrderedMenuListener listener;

    public MenuAdapter(List<Menu> menuList) {
        this.menuList = menuList;
        orderedMenu = new ArrayList<>();
    }

    public void setOnOrderedMenuListener(OnOrderedMenuListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_item_menu, parent, false);

        final MenuViewHolder menuViewHolder = new MenuViewHolder(view);

        menuViewHolder.menuOrder.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener() {
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    orderedMenu.add(menuList.get(menuViewHolder.getAdapterPosition()));
                } else {
                    orderedMenu.remove(menuList.get(menuViewHolder.getAdapterPosition()));
                }

                listener.onOrderedMenu(orderedMenu);

            }
        });

        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.menuName.setText(menuList.get(position).getName());
        holder.menuDiscount.setText(menuList.get(position).getDiscount());
        holder.menuImage.setImageResource(menuList.get(position).getImageResId());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public interface OnOrderedMenuListener {
        void onOrderedMenu(List<Menu> orderedMenu);
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView menuName, menuDiscount;
        private ImageView menuImage;
        private BootstrapButton menuOrder;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            menuImage = itemView.findViewById(R.id.menu_img);
            menuName = itemView.findViewById(R.id.menu_name);
            menuDiscount = itemView.findViewById(R.id.menu_discount);
            menuOrder = itemView.findViewById(R.id.menu_order);
        }
    }
}
