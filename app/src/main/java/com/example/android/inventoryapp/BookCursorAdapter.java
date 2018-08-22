package com.example.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter {

    private Context mContext;

    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        mContext = context;
    }

    // Create empty List_item view
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list_item layout
        TextView bookTitleTextView = view.findViewById(R.id.book_title);
        TextView bookPriceTextView = view.findViewById(R.id.book_price);
        TextView stockTextView = view.findViewById(R.id.stock);
        Button saleButton = view.findViewById(R.id.sale);

        // Find the columns of book attributes that we're interested in
        int titleColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_TITLE);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);

        final int id = cursor.getInt(cursor.getColumnIndex(BookEntry._ID));

        // Read the book attributes from the Cursor for the current book
        String bookTitle = cursor.getString(titleColumnIndex);
        String bookPrice = context.getString(R.string.Pound_sterling_symbol) + cursor.getString(priceColumnIndex);
        String bookQuantity = "In stock: " + cursor.getString(quantityColumnIndex);

        // Update the TextViews with the attributes for the current book
        bookTitleTextView.setText(bookTitle);
        bookPriceTextView.setText(bookPrice);
        stockTextView.setText(bookQuantity);

        //Convert Book quantity from String to Integer
        String remainingQuantityString = cursor.getString(quantityColumnIndex);
        final int remainingQuantity = Integer.valueOf(remainingQuantityString);

        //Sell button statement ~if stock is greater than 0 then we can remove 1 book
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remainingQuantity > 0) {
                    int finalQuantity = remainingQuantity - 1;
                    Uri uri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, id);
                    //Get quantity value and update to the new value -1
                    ContentValues values = new ContentValues();
                    values.put(BookEntry.COLUMN_QUANTITY, finalQuantity);
                    mContext.getContentResolver().update(uri, values, null, null);
                } else {
                    Toast.makeText(mContext, "Out of stock", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}