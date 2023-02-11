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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    String[] title;
    String[] price;
    Bitmap[] images;
    Context context;

    public RecyclerViewAdapter(Context context,String[] title, String[] price, Bitmap[] images) {
        this.title = title;
        this.price = price;
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(context, ProductViewActivity.class);
                intent.putExtra("ProdName",((TextView)view.findViewById(R.id.recyclerviewitem_title)).getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return new RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.title.setText(title[position]);
        holder.price.setText(price[position]);
        holder.image.setImageBitmap(images[position]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView image;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.recyclerviewitem_imageView);
            title = itemView.findViewById(R.id.recyclerviewitem_title);
            price = itemView.findViewById(R.id.recyclerviewitem_price);
        }

    }
}
