package accenture.com.coffeetek.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import accenture.com.coffeetek.Interface.ItemClickListener;
import accenture.com.coffeetek.R;

public class ShowHomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public ImageView image_menu_list;
    public TextView name_menu_list;
    public ImageView arrow_menu_list;
    public LinearLayout linear_Layout_Menu_List;

    private ItemClickListener itemClickListener;

    public ShowHomeViewHolder(View itemView) {
        super(itemView);
        name_menu_list = (TextView) itemView.findViewById(R.id.name_menu_list);
        image_menu_list = (ImageView) itemView.findViewById(R.id.image_menu_list);
        arrow_menu_list = (ImageView) itemView.findViewById(R.id.arrow_menu_list);
        linear_Layout_Menu_List = (LinearLayout) itemView.findViewById(R.id.linear_Layout_Menu_List);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick( view, getAdapterPosition(), false );
    }

    public void setItemClickListener(ItemClickListener itemClickListener ){
        this.itemClickListener = itemClickListener;

    }


}
