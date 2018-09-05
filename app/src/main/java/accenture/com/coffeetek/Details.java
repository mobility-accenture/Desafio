package accenture.com.coffeetek;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import accenture.com.coffeetek.Model.Product;
import accenture.com.coffeetek.Util.Common;
import accenture.com.coffeetek.Util.NetworkUtils;

public class Details extends AppCompatActivity implements View.OnClickListener{

    LinearLayout linear_Layout_Image_Details, linear_Layout_Information_Details;

    ImageView small_cup, medium_cup, large_cup;
    ImageView no_sugar_details, sugar1_details, sugar2_details, sugar3_details;
    ImageView cream_details, chocolate_details, cinnamon_details, coffee_details, milk_details;
    ImageView subtract_details, add_details;

    ImageView main_image_details;
    TextView product_name_details, product_size_details;

    TextView amount_details;

    CardView add_to_cart_button_details;

    int size = 0, lastSize = 0;
    int sugar = 0, lastSugar = 0;
    int additional_cream = 0, additional_chocolate = 0, additional_cinnamon = 0, additional_coffee = 0, additional_milk = 0;
    int amount = 0;

    int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        LoadViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        NetworkUtils.checkConection( Details.this, Details.this);

    }


    private void LoadViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
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
            toolbarTitle.setText(getResources().getString(R.string.details_text));


        Intent data = getIntent();
        position = data.getIntExtra( "position", 0 );

        main_image_details = (ImageView) findViewById(R.id.main_image_details);
        product_name_details = (TextView) findViewById(R.id.product_name_details);
        product_size_details = (TextView) findViewById(R.id.product_size_details);

        Bitmap image = Common.productList.get(position).getImage();
        main_image_details.setImageBitmap( image );

        product_name_details.setText( Common.productList.get(position).getTitle() );
        LoadProductSizeText();

        size = Common.productList.get(position).getSize();
        sugar = Common.productList.get(position).getSugar();
        additional_cream = Common.productList.get(position).getAdditional_Cream();
        additional_chocolate = Common.productList.get(position).getAdditional_Chocolate();
        additional_cinnamon = Common.productList.get(position).getAdditional_Cinnamon();
        additional_coffee = Common.productList.get(position).getAdditional_Coffee();
        additional_milk = Common.productList.get(position).getAdditional_Milk();



        add_to_cart_button_details = (CardView) findViewById(R.id.add_to_cart_button_details);
        add_to_cart_button_details.setOnClickListener(this);


        int screenHeight, screenWidth;

        //Code to determine screen's height and width.

        Display display = getWindowManager().getDefaultDisplay();
        if (android.os.Build.VERSION.SDK_INT>=13) {
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
            screenWidth = size.x;
        }
        else {
            screenWidth = display.getWidth();
            screenHeight = display.getHeight();
        }


        linear_Layout_Image_Details = (LinearLayout) findViewById(R.id.linear_Layout_Image_Details);
        linear_Layout_Information_Details = (LinearLayout) findViewById(R.id.linear_Layout_Information_Details);

        linear_Layout_Image_Details.getLayoutParams().height = (screenHeight)/5;
        linear_Layout_Information_Details.getLayoutParams().height = (screenHeight*3)/5;




        small_cup = (ImageView) findViewById(R.id.small_cup);
        small_cup.setOnClickListener(this);
        medium_cup = (ImageView) findViewById(R.id.medium_cup);
        medium_cup.setOnClickListener(this);
        large_cup = (ImageView) findViewById(R.id.large_cup);
        large_cup.setOnClickListener(this);


        no_sugar_details = (ImageView) findViewById(R.id.no_sugar_details);
        no_sugar_details.setOnClickListener(this);
        sugar1_details = (ImageView) findViewById(R.id.sugar1_details);
        sugar1_details.setOnClickListener(this);
        sugar2_details = (ImageView) findViewById(R.id.sugar2_details);
        sugar2_details.setOnClickListener(this);
        sugar3_details = (ImageView) findViewById(R.id.sugar3_details);
        sugar3_details.setOnClickListener(this);


        cream_details = (ImageView) findViewById(R.id.cream_details);
        cream_details.setOnClickListener(this);
        chocolate_details = (ImageView) findViewById(R.id.chocolate_details);
        chocolate_details.setOnClickListener(this);
        cinnamon_details = (ImageView) findViewById(R.id.cinnamon_details);
        cinnamon_details.setOnClickListener(this);
        coffee_details = (ImageView) findViewById(R.id.coffee_details);
        coffee_details.setOnClickListener(this);
        milk_details = (ImageView) findViewById(R.id.milk_details);
        milk_details.setOnClickListener(this);


        subtract_details = (ImageView) findViewById(R.id.subtract_details);
        subtract_details.setOnClickListener(this);
        add_details = (ImageView) findViewById(R.id.add_details);
        add_details.setOnClickListener(this);
        amount_details = (TextView) findViewById(R.id.amount_details);


        LoadCupImage();

        LoadSugarImage();

        LoadAdditionalImage();


    }

    private void LoadProductSizeText() {

        if (getResources() != null){
            if (size == 0){
                product_size_details.setText( getResources().getString(R.string.small_size_text) );
            }
            if (size == 1){
                product_size_details.setText( getResources().getString(R.string.medium_size_text) );
            }
            if (size == 2){
                product_size_details.setText( getResources().getString(R.string.large_size_text) );
            }
        }



    }

    @Override
    public void onClick(View view) {
        //Cup size
        if (view == small_cup){
            size = 0;
            LoadCupImage();
            LoadProductSizeText();
        }
        else if (view == medium_cup){
            size = 1;
            LoadCupImage();
            LoadProductSizeText();
        }
        else if (view == large_cup){
            size = 2;
            LoadCupImage();
            LoadProductSizeText();
        }


        //Sugar
        else if (view == no_sugar_details){
            sugar = 0;
            LoadSugarImage();
        }
        else if (view == sugar1_details){
            sugar = 1;
            LoadSugarImage();
        }
        else if (view == sugar2_details){
            sugar = 2;
            LoadSugarImage();
        }
        else if (view == sugar3_details){
            sugar = 3;
            LoadSugarImage();
        }


        //Additional
        else if (view == cream_details){
            additional_cream = (additional_cream == 1 ? 0 : 1);
            LoadAdditionalImage();
        }
        else if (view == chocolate_details){
            additional_chocolate = (additional_chocolate == 1 ? 0 : 1);
            LoadAdditionalImage();
        }
        else if (view == cinnamon_details){
            additional_cinnamon = (additional_cinnamon == 1 ? 0 : 1);
            LoadAdditionalImage();
        }
        else if (view == coffee_details){
            additional_coffee = (additional_coffee == 1 ? 0 : 1);
            LoadAdditionalImage();
        }
        else if (view == milk_details){
            additional_milk = (additional_milk == 1 ? 0 : 1);
            LoadAdditionalImage();
        }


        //add or subtract amount
        else if (view == subtract_details){
            amount -= 1;
            LoadAmount();
        }
        else if (view == add_details){
            amount += 1;
            LoadAmount();
        }


        //add to cart
        else if (view == add_to_cart_button_details){

            if (amount > 0)
                addToCart();
            else {
                if (getResources() != null)
                    Toast.makeText(Details.this, getResources().getString(R.string.select_amount_text), Toast.LENGTH_SHORT ).show();
            }

        }

    }

    private void addToCart() {

        Product addProduct = new Product(
                Common.productList.get(position).getTitle(),
                Common.productList.get(position).getImage(),
                size,
                sugar,
                amount,
                additional_cream,
                additional_chocolate,
                additional_cinnamon,
                additional_coffee,
                additional_milk
        );

        if ( Common.cartList == null )
            Common.cartList = new ArrayList<>();

        Common.cartList.add(addProduct);
        finish();



    }


    private void LoadCupImage(){

        small_cup.setImageResource(R.drawable.cup_2);
        medium_cup.setImageResource(R.drawable.cup_2);
        large_cup.setImageResource(R.drawable.cup_2);

        if (size == 0){
            small_cup.setImageResource(R.drawable.cup);
        }
        if (size == 1){
            medium_cup.setImageResource(R.drawable.cup);
        }
        if (size == 2){
            large_cup.setImageResource(R.drawable.cup);
        }

    }

    private void LoadSugarImage(){

        no_sugar_details.setImageResource(R.drawable.no_sugar_unselected);
        sugar1_details.setImageResource(R.drawable.sugar1_unselected);
        sugar2_details.setImageResource(R.drawable.sugar2_unselected);
        sugar3_details.setImageResource(R.drawable.sugar3_unselected);

        if (sugar == 0){
            no_sugar_details.setImageResource(R.drawable.no_sugar);
        }
        if (sugar == 1){
            sugar1_details.setImageResource(R.drawable.sugar1);
        }
        if (sugar == 2){
            sugar2_details.setImageResource(R.drawable.sugar2);
        }
        if (sugar == 3){
            sugar3_details.setImageResource(R.drawable.sugar3);
        }
    }

    private void LoadAdditionalImage(){

        if (additional_cream == 0){
            cream_details.setImageResource(R.drawable.cream_unselected);
        }
        if (additional_cream == 1){
            cream_details.setImageResource(R.drawable.cream);
        }

        if (additional_chocolate == 0){
            chocolate_details.setImageResource(R.drawable.chocolate_unselected);
        }
        if (additional_chocolate == 1){
            chocolate_details.setImageResource(R.drawable.chocolate);
        }

        if (additional_cinnamon == 0){
            cinnamon_details.setImageResource(R.drawable.cinnamon_unselected);
        }
        if (additional_cinnamon == 1){
            cinnamon_details.setImageResource(R.drawable.cinnamon);
        }

        if (additional_coffee == 0){
            coffee_details.setImageResource(R.drawable.coffee_unselected);
        }
        if (additional_coffee == 1){
            coffee_details.setImageResource(R.drawable.coffee);
        }

        if (additional_milk == 0){
            milk_details.setImageResource(R.drawable.milk_unselected);
        }
        if (additional_milk == 1){
            milk_details.setImageResource(R.drawable.milk);
        }

    }

    private void LoadAmount(){

        if (amount < 0) amount = 0;
        if (amount > 99) amount = 99;

        amount_details.setText( String.valueOf(amount) );

    }
}
