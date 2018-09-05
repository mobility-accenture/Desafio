package accenture.com.coffeetek;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import accenture.com.coffeetek.Adapter.HomeAdapter;
import accenture.com.coffeetek.Model.Product;
import accenture.com.coffeetek.Util.Common;
import accenture.com.coffeetek.Util.JsonTask;
import accenture.com.coffeetek.Util.NetworkUtils;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;



    HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        LoadViews();

        if ( Common.productList != null ){
            adapter = new HomeAdapter( this, Common.productList );
            recyclerView.setAdapter(adapter);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        NetworkUtils.checkConection( Home.this, Home.this);

    }

    private void LoadProducts() {

        //List<Product> formList = new ArrayList<Product>();
        Common.productList = new ArrayList<Product>();

        try {
            JSONObject object = new JSONObject(loadJSONFromAsset());
            JSONArray json_products = object.getJSONArray("products");


            for (int i = 0; i < json_products.length(); i++) {

                Product product = new Product();

                JSONObject json_product = json_products.getJSONObject(i);

                String title_value = json_product.getString("title");
                String image_value = json_product.getString("image");

                String[] cutString = image_value.split(",");
                String image64 = "";

                if (cutString.length > 1){
                    image64 = cutString[1];
                }

                byte[] byteArray =  Base64.decode(image64, Base64.DEFAULT) ;
                Bitmap bmp1 = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                int size_value = json_product.getInt("size");
                int sugar_value = json_product.getInt("sugar");
                String additional_value = "";
                //List<String> list_additional = new ArrayList<>();

                int additional_cream = 0;
                int additional_chocolate = 0;
                int additional_cinnamon = 0;
                int additional_coffee = 0;
                int additional_milk = 0;

                if ( !json_product.isNull("additional") ){
                    JSONArray additional = json_product.getJSONArray("additional");

                    for(int j = 0; j < additional.length(); j++) {
                        additional_value = additional.get(j).toString();

                        if (getResources() != null){
                            if ( additional_value.trim().equals( getResources().getString(R.string.cream_text).toLowerCase() ) ){
                                additional_cream = 1;
                            }
                            if ( additional_value.trim().equals( getResources().getString(R.string.chocolate_text).toLowerCase() ) ){
                                additional_chocolate = 1;
                            }
                            if ( additional_value.trim().equals( getResources().getString(R.string.cinnamon_text).toLowerCase() ) ){
                                additional_cinnamon = 1;
                            }
                            if ( additional_value.trim().equals( getResources().getString(R.string.coffee_text).toLowerCase() ) ){
                                additional_coffee = 1;
                            }
                            if ( additional_value.trim().equals( getResources().getString(R.string.milk_text).toLowerCase() ) ){
                                additional_milk = 1;
                            }
                        }





                        //list_additional.add(additional_value);
                    }

                    //additional_value = json_product.getString("additional");
                }

                product.setTitle(title_value);
                product.setImage(bmp1);
                product.setSize(size_value);
                product.setSugar(sugar_value);
                product.setAdditional_Cream(additional_cream);
                product.setAdditional_Chocolate(additional_chocolate);
                product.setAdditional_Cinnamon(additional_cinnamon);
                product.setAdditional_Coffee(additional_coffee);
                product.setAdditional_Milk(additional_milk);

                //formList.add(product);
                Common.productList.add(product);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    private void LoadViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        if (getResources() != null)
            toolbarTitle.setText(getResources().getString(R.string.menu_text));

        recyclerView = (RecyclerView) findViewById( R.id.recycler_menu );
        layoutManager = new LinearLayoutManager( Home.this );
        recyclerView.setLayoutManager( layoutManager );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.shopping_cart){
            Intent intent = new Intent( Home.this, ShoppingCart.class );
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
