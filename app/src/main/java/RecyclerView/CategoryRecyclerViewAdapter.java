package RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_shop.ProductViewActivity;
import com.example.e_shop.R;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryRecyclerViewHolder>{
    String[] title;
    String[] price;
    Context context;
    Bitmap[] images;

    public CategoryRecyclerViewAdapter(Context context, String[] title, String[] price, Bitmap[] images) {
        this.title = title;
        this.price = price;
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_recyclerview_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(context, ProductViewActivity.class);
                intent.putExtra("ProdName",((TextView)view.findViewById(R.id.category_recyclerviewitem_title)).getText().toString());
                context.startActivity(intent);
            }
        });
        return new CategoryRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolder holder, int position) {
        holder.title.setText(title[position]);
        holder.price.setText(price[position]);
        holder.image.setImageBitmap(images[position]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class CategoryRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView image;
        public CategoryRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.category_recyclerviewitem_imageView);
            title = itemView.findViewById(R.id.category_recyclerviewitem_title);
            price = itemView.findViewById(R.id.category_recyclerviewitem_price);
        }
    }
}
