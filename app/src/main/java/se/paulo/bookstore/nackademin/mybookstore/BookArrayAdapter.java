package se.paulo.bookstore.nackademin.mybookstore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by paulo on 2016-04-12.
 */
public class BookArrayAdapter extends ArrayAdapter<Book> {

    Context context;
    List<Book> myBookstore;


    public BookArrayAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        myBookstore = (List) objects;
    }


    // Ett sätt att optimera ListView ytterligare
    static class MyViewHolder {
        ImageView icon;
        TextView bookName;
        TextView price;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Skapa den View som visas för varje item i en ListView
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View itemView;

        if(convertView == null){
            itemView = layoutInflater.inflate(R.layout.book_list_item, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder();

            myViewHolder.icon = (ImageView)itemView.findViewById(R.id.imageView);
            myViewHolder.bookName = (TextView)itemView.findViewById(R.id.nameTextItem);
            myViewHolder.price = (TextView)itemView.findViewById(R.id.priceTextItem);

            itemView.setTag(myViewHolder);

        }else {
            itemView = convertView;
        }


        MyViewHolder returnedViewHolder = (MyViewHolder) itemView.getTag();

        String bookName = myBookstore.get(position).getBookName();
        double price = myBookstore.get(position).getPrice();
        int imageId = myBookstore.get(position).getImageId();

        returnedViewHolder.bookName.setText(bookName);
        returnedViewHolder.price.setText("kr. " + price);

        Bitmap bitmap = ResizeImage.getOptimizedBitmap(context, imageId, 100, 100);
        returnedViewHolder.icon.setImageBitmap(bitmap);

        BitmapFactory.Options options = new BitmapFactory.Options();

        //bildens egenskaper(höjd, bred osv men inte bild själv)
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), imageId, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;

        Log.i("IMAGE ORIGINAL INFO ", "" + context.getResources().getResourceEntryName(imageId) + " : " + imageHeight + " : " + imageWidth + " : " + imageType);

        Log.i("AFTER DECODE ", "" + returnedViewHolder.icon.getHeight() + " New width: " + returnedViewHolder.icon.getWidth());



        return itemView;
    }
}
