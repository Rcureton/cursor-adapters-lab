package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        ShoppingListSQLiteHelper helper= ShoppingListSQLiteHelper.getInstance(MainActivity.this);
        Cursor cursor= helper.getShoppingList();

        ListView listView= (ListView)findViewById(R.id.shopping_list_view);

        CursorAdapter cursorAdapter= new CursorAdapter(MainActivity.this,cursor,0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.shopping_list_items,parent,false);

            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                ListView listView= (ListView)view.findViewById(R.id.shopping_list_view);
                TextView textView= (TextView)view.findViewById(R.id.textView1);
                TextView textPrice= (TextView)view.findViewById(R.id.textView2);
                TextView textDescription= (TextView)view.findViewById(R.id.textView3);
                TextView textType= (TextView)view.findViewById(R.id.textView4);

                String itemName= cursor.getString(cursor.getColumnIndex("ITEM_NAME"));
                String itemPrice= cursor.getString(cursor.getColumnIndex("PRICE"));
                String itemDescription= cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                String itemType= cursor.getString(cursor.getColumnIndex("TYPE"));

                textView.setText(itemName);
                textPrice.setText(itemPrice);
                textDescription.setText(itemDescription);
                textType.setText(itemType);
            }
        }; listView.setAdapter(cursorAdapter);

    }
}
