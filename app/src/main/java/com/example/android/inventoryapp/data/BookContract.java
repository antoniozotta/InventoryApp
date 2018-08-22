package com.example.android.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class BookContract {

    /**
     * Content authority name for content provider
     * com.example.android.books"
     */
    public static final String CONTENT_AUTHORITY = "com.example.android.inventoryapp";
    /**
     * CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    /**
     * Possible path (appended to base content URI for possible URI's)
     */
    public static final String PATH_BOOKS = "books";

    //Empty constructor
    private BookContract() {
    }

    /**
     * Each entry in table represents single book
     */
    public static final class BookEntry implements BaseColumns {

        /**
         * The content URI to access the book data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of books.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single book.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        //Table name
        public final static String TABLE_NAME = "books";
        //Unique ID ~Integer
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