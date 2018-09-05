package accenture.com.coffeetek.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import accenture.com.coffeetek.Model.Product;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "Desafio.s3db";
    private static final int DB_VER = 2;

    public Database ( Context context ){
        super( context, DB_NAME, null, DB_VER );
    }

    public void addToCart ( Product product ) {

    }
}
