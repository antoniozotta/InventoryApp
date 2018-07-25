package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

public class BookContract {

    //Empty constructor
    private BookContract() {
    }

    /**
     * Each entry in table represents single book
     */
    public static final class BookEntry implements BaseColumns {
        //Table name
        public final static String TABLE_NAME = "books";
        //Unique ID
        public final static String _ID = BaseColumns._ID;
        //Book title ~String
        public final static String COLUMN_TITLE = "title";
        //Book price ~Integer
        public final static String COLUMN_PRICE = "price";
        //Quantity ~Integer
        public final static String COLUMN_QUANTITY = "quantity";
        //Supplier name ~Text
        public final static String COLUMN_SUPPLIER_NAME = "supplier";
        //Supplier phone number ~String
        public final static String COLUMN_SUPPLIER_PHONE = "phone";
    }
}