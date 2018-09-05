package accenture.com.coffeetek.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import accenture.com.coffeetek.Interface.ItemClickListener;
import accenture.com.coffeetek.R;

public class ShowShoppingCartViewHolder extends RecyclerView.ViewHolder {


    public TextView product_name_shopping_cart, product_amount_shopping_cart, product_additional_shopping_cart;
    public ImageView image_shopping_cart_list;
    public LinearLayout linear_Layout_Shopping_Cart_List;



    public ShowShoppingCartViewHolder(View itemView) {
        super(itemView);
        product_name_shopping_cart = (TextView) itemView.findViewById(R.id.product_name_shopping_cart);
        product_amount_shopping_cart = (TextView) itemView.findViewById(R.id.product_amount_shopping_cart);
        product_additional_shopping_cart = (TextView) itemView.findViewById(R.id.product_additional_shopping_cart);
        image_shopping_cart_list = (ImageView) itemView.findViewById(R.id.image_shopping_cart_list);
        linear_Layout_Shopping_Cart_List = (LinearLayout) itemView.findViewById(R.id.linear_Layout_Shopping_Cart_List);

    }

}
