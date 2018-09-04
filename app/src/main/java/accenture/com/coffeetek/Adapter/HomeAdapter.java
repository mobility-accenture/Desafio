package accenture.com.coffeetek.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import accenture.com.coffeetek.Details;
import accenture.com.coffeetek.Interface.ItemClickListener;
import accenture.com.coffeetek.Model.MenuItem;
import accenture.com.coffeetek.Model.Product;
import accenture.com.coffeetek.R;
import accenture.com.coffeetek.ViewHolder.ShowHomeViewHolder;

public class HomeAdapter extends RecyclerView.Adapter<ShowHomeViewHolder> {

    private Context context;
    private List<Product> productList;

    public HomeAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ShowHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate( R.layout.show_menu_layout, parent, false );

        return new ShowHomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowHomeViewHolder holder, final int position) {

        holder.name_menu_list.setText( productList.get(position).getTitle() );
        holder.image_menu_list.setImageBitmap( productList.get(position).getImage() );

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                Intent intent = new Intent( context, Details.class );
                intent.putExtra("position", position);

                context.startActivity( intent );

            }
        });

    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void removeItem( int position ){
        productList.remove(position);
    }

    public void restoreItem( Product item, int position ){
        productList.add(position, item);
        //notifyItemInserted(position);
    }

    public Product getItem(int position){
        return productList.get(position);
    }
}
