package RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_shop.R;

import Session.Rating;

public class FeedbackRecyclerViewAdapter extends RecyclerView.Adapter<FeedbackRecyclerViewAdapter.FeedbackRecyclerViewHolder>{
    private Context context;
    private Rating[] ratings;

    public FeedbackRecyclerViewAdapter(Context context, Rating[] rating) {
        this.context = context;
        this.ratings = rating;
    }

    @NonNull
    @Override
    public FeedbackRecyclerViewAdapter.FeedbackRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.feedback_recycler_view_item, parent, false);
        return new FeedbackRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackRecyclerViewAdapter.FeedbackRecyclerViewHolder holder, int position) {
        holder.CustName.setText(ratings[position].getCustomerName());
        holder.ratingBar.setRating(ratings[position].getRate());
        holder.Comment.setText(ratings[position].getComment());
    }

    @Override
    public int getItemCount() {
        return ratings.length;
    }

    public class FeedbackRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView CustName, Comment;
        RatingBar ratingBar;
        public FeedbackRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            CustName = itemView.findViewById(R.id.feedback_item_customer_name);
            Comment = itemView.findViewById(R.id.feedback_item_comment);
            ratingBar = itemView.findViewById(R.id.feedback_item_rating_bar);
        }
    }
}
