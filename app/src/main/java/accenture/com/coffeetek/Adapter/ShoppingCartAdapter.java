package accenture.com.coffeetek.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import accenture.com.coffeetek.Model.Product;
import accenture.com.coffeetek.R;
import accenture.com.coffeetek.ViewHolder.ShowShoppingCartViewHolder;

public class ShoppingCartAdapter  extends RecyclerView.Adapter<ShowShoppingCartViewHolder> {

    private Context context;
    private List<Product> cartList;

    int maxLength = 18;

    public ShoppingCartAdapter(Context context, List<Product> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @Override
    public ShowShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate( R.layout.show_shopping_cart_layout, parent, false );

        return new ShowShoppingCartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowShoppingCartViewHolder holder, final int position) {

        String title = " " + cartList.get(position).getTitle();
        holder.product_name_shopping_cart.setText( title );

        String amount = " " + String.valueOf( cartList.get(position).getAmount() );
        holder.product_amount_shopping_cart.setText( amount );

        String additional = "";

        if ( context.getResources() != null){

            if ( cartList.get(position).getAdditional_Cream() == 1 )
                additional += context.getResources().getString(R.string.cream_text);

            if ( cartList.get(position).getAdditional_Milk() == 1 ){
                String milk = context.getResources().getString(R.string.milk_text);
                if (!additional.equals("")) additional += ", ";
                if ( (additional.length() + milk.length()) > maxLength  && !additional.contains("\n") )
                    additional += "\n";
                additional += context.getResources().getString(R.string.milk_text);
            }

            if ( cartList.get(position).getAdditional_Chocolate() == 1 ){
                String chocolate = context.getResources().getString(R.string.chocolate_text);
                if (!additional.equals("")) additional += ", ";
                if ( (additional.length() + chocolate.length()) > maxLength ) additional += "\n";
                additional += chocolate;
            }

            if ( cartList.get(position).getAdditional_Coffee() == 1 ){
                String coffee = context.getResources().getString(R.string.coffee_text);
                if (!additional.equals("")) additional += ", ";
                if ( (additional.length() + coffee.length() ) > maxLength  && (!additional.contains("\n") ) )
                    additional += "\n";
                additional += coffee;
            }

            if ( cartList.get(position).getAdditional_Cinnamon() == 1 ){
                String cinnamon = context.getResources().getString(R.string.cinnamon_text);
                if (!additional.equals("")) additional += ", ";
                if ( (additional.length() + cinnamon.length()) > maxLength && (!additional.contains("\n") ) )
                    additional += "\n";
                additional += cinnamon;
            }



        }


        String additional_text = " " + additional;

        holder.product_additional_shopping_cart.setText( additional_text );

        holder.image_shopping_cart_list.setImageBitmap( cartList.get(position).getImage() );


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void removeItem( int position ){
        cartList.remove(position);
    }

    public void restoreItem( Product item, int position ){
        cartList.add(position, item);
        //notifyItemInserted(position);
    }

    public Product getItem(int position){
        return cartList.get(position);
    }

}
