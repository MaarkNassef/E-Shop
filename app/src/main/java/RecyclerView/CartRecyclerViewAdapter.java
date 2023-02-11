package RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_shop.ProductViewActivity;
import com.example.e_shop.R;

import java.util.ArrayList;
import java.util.Arrays;

import Database.EShopDatabaseHelper;
import Session.Session;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.CartRecyclerViewHolder>{
    Context context;
    String[] title;
    String[] price;
    String[] quantity;
    Bitmap[] image;
    TextView totalPrice;

    public CartRecyclerViewAdapter(Context context, String[] title, String[] price, Bitmap[] images,String[] quantity, TextView totalPrice) {
        this.context = context;
        this.title = title;
        this.price = price;
        this.image=images;
        this.quantity=quantity;
        this.totalPrice = totalPrice;
    }

    @NonNull
    @Override
    public CartRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_recyclerview_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(context, ProductViewActivity.class);
                intent.putExtra("ProdName",((TextView)view.findViewById(R.id.cart_recyclerviewitem_title)).getText().toString());
                context.startActivity(intent);
            }
        });
        return new CartRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(title[position]);
        holder.price.setText(price[position]);
        holder.quantity.setText(quantity[position]);
        holder.image.setImageBitmap(image[position]);
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity[position] = ""+(Integer.parseInt(quantity[position])+1);
                holder.quantity.setText(quantity[position]);

                EShopDatabaseHelper db = new EShopDatabaseHelper(context);
                db.ordIncrementQuantity(db.getOrderId(Session.getInstance().getCustomer().getId()), title[position]);

                int sum = 0;
                for (int i = 0; i < price.length; i++) {
                    sum += Integer.parseInt(price[i])*Integer.parseInt(quantity[i]);
                }
                totalPrice.setText(String.valueOf(sum));
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(quantity[position])-1 >= 1) {
                    quantity[position] = "" + (Integer.parseInt(quantity[position]) - 1);
                    holder.quantity.setText(quantity[position]);

                    EShopDatabaseHelper db = new EShopDatabaseHelper(context);
                    db.ordDecrementQuantity(db.getOrderId(Session.getInstance().getCustomer().getId()), title[position]);

                    int sum = 0;
                    for (int i = 0; i < price.length; i++) {
                        sum += Integer.parseInt(price[i])*Integer.parseInt(quantity[i]);
                    }
                    totalPrice.setText(String.valueOf(sum));
                }
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Remove
                String itemDeleted = title[position];

                ArrayList<String> tempTitle = new ArrayList<String>(Arrays.asList(title));
                tempTitle.remove(position);
                title = tempTitle.toArray(tempTitle.toArray(new String[tempTitle.size()]));

                ArrayList<String> tempPrice = new ArrayList<String>(Arrays.asList(price));
                tempPrice.remove(position);
                price = tempPrice.toArray(new String[tempPrice.size()]);

                ArrayList<Bitmap> tempImage = new ArrayList<Bitmap>(Arrays.asList(image));
                tempImage.remove(position);
                image = tempImage.toArray(new Bitmap[tempImage.size()]);

                ArrayList<String> tempQuantity = new ArrayList<String>(Arrays.asList(quantity));
                tempQuantity.remove(position);
                quantity = tempQuantity.toArray(new String[tempQuantity.size()]);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, tempTitle.size());

                EShopDatabaseHelper db = new EShopDatabaseHelper(context);
                db.ordDeleteProduct(db.getOrderId(Session.getInstance().getCustomer().getId()), itemDeleted);

                int sum = 0;
                for (int i = 0; i < tempPrice.size(); i++) {
                    sum += Integer.parseInt(price[i])*Integer.parseInt(quantity[i]);
                }
                totalPrice.setText(String.valueOf(sum));
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class CartRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, quantity;
        ImageView image;
        ImageButton plus, minus, remove;

        public CartRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cart_recyclerviewitem_imageView);
            title = itemView.findViewById(R.id.cart_recyclerviewitem_title);
            price = itemView.findViewById(R.id.cart_recyclerviewitem_price);
            quantity = itemView.findViewById(R.id.cart_quantity);
            plus = itemView.findViewById(R.id.cart_plus);
            minus = itemView.findViewById(R.id.cart_minus);
            remove = itemView.findViewById(R.id.cart_remove);
        }

    }
}
