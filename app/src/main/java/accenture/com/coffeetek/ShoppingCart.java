package accenture.com.coffeetek;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import accenture.com.coffeetek.Adapter.ShoppingCartAdapter;
import accenture.com.coffeetek.Util.Common;
import accenture.com.coffeetek.Util.NetworkUtils;

public class ShoppingCart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    ShoppingCartAdapter adapter;
    
    TextView empty_shopping_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        LoadViews();

        if ( Common.cartList != null )
        {
            empty_shopping_cart.setVisibility(View.INVISIBLE);
            LoadCart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        NetworkUtils.checkConection( ShoppingCart.this, ShoppingCart.this);

    }

    private void LoadCart() {

        adapter = new ShoppingCartAdapter( this, Common.cartList );
        recyclerView.setAdapter(adapter);
    }

    private void LoadViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.shopping_cart_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


            Drawable backArrow = getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
            getSupportActionBar().setHomeAsUpIndicator(backArrow);

        }


        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        if (getResources() != null)
            toolbarTitle.setText(getResources().getString(R.string.cart_text));

        empty_shopping_cart = (TextView) findViewById(R.id.empty_shopping_cart);

        recyclerView = (RecyclerView) findViewById( R.id.recycler_shopping_cart_menu );
        layoutManager = new LinearLayoutManager( ShoppingCart.this );
        recyclerView.setLayoutManager( layoutManager );

    }

}
