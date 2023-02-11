package RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_shop.R;

import Session.Report;

public class ReportsRecyclerViewAdapter extends RecyclerView.Adapter<ReportsRecyclerViewAdapter.ReportsRecyclerViewHolder>{
    private Context context;
    private Report[] reports;

    public ReportsRecyclerViewAdapter(Context context, Report[] reports) {
        this.context = context;
        this.reports = reports;
    }

    @NonNull
    @Override
    public ReportsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.report_recycler_view_item, parent, false);
        return new ReportsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsRecyclerViewHolder holder, int position) {
        holder.OrdDate.setText(reports[position].getOrderDate());
        holder.CustName.setText(reports[position].getCustomerName());
        holder.ProdName.setText(reports[position].getProductName());
        holder.ProdPrice.setText(reports[position].getProductPrice());
        holder.ProdQuantity.setText(reports[position].getProductQuantity());
    }

    @Override
    public int getItemCount() {
        return reports.length;
    }

    public class ReportsRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView OrdDate, CustName, ProdName, ProdQuantity, ProdPrice;
        public ReportsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            OrdDate = itemView.findViewById(R.id.report_item_order_date);
            CustName = itemView.findViewById(R.id.report_item_customer_name);
            ProdName = itemView.findViewById(R.id.report_item_product_name);
            ProdQuantity = itemView.findViewById(R.id.report_item_product_quantity);
            ProdPrice = itemView.findViewById(R.id.report_item_product_price);
        }
    }
}
