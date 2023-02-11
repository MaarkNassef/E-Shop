package RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_shop.BrowseCategoryActivity;
import com.example.e_shop.R;

import Database.EShopDatabaseHelper;
import Session.Product;

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.CategoriesRecyclerViewHolder>{
    Context context;
    String[] titles;
    RecyclerViewAdapter[] recyclerViewAdapter;

    public CategoriesRecyclerViewAdapter(Context context, String[] titles) {
        this.context = context;
        this.titles = titles;
        recyclerViewAdapter = new RecyclerViewAdapter[titles.length];
    }

    @NonNull
    @Override
    public CategoriesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_component, parent, false);
        view.findViewById(R.id.category_component_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BrowseCategoryActivity.class);
                intent.putExtra("CatName", ((TextView)view.findViewById(R.id.category_component_title)).getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return new CategoriesRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesRecyclerViewHolder holder, int position) {
        holder.title.setText(titles[position]);
        EShopDatabaseHelper db = new EShopDatabaseHelper(context);
        Product[] products = db.getProductsInCategory(titles[position]);
        String[] prod_titles = new String[products.length];
        String[] prod_prices = new String[products.length];
        Bitmap[] prod_images = new Bitmap[products.length];
        for (int i = 0; i < products.length; i++) {
            prod_titles[i] = products[i].getName();
            prod_prices[i] = products[i].getPrice();
            prod_images[i] = db.getImage(prod_titles[i]);
        }
        recyclerViewAdapter[position] = new RecyclerViewAdapter(context, prod_titles, prod_prices, prod_images);
        holder.recyclerView.setAdapter(recyclerViewAdapter[position]);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class CategoriesRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        RecyclerView recyclerView;
        public CategoriesRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.category_component_title);
            recyclerView = itemView.findViewById(R.id.category_component_recycler_view_products);
        }
    }
}
