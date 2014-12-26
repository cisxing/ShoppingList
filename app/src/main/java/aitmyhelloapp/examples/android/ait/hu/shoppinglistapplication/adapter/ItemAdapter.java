package aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.R;
import aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data.item;

/**
 * Created by xinyunxing on 10/16/2014.
 */
public class ItemAdapter  extends BaseAdapter{

        private Context context;
        //private List<item> placesToVisit;
        private List<item> itemToBuy;

        public ItemAdapter(Context context, List<item> itemToBuy) {
            this.context = context;
            this.itemToBuy = itemToBuy;
        }

        public void addItem(item Item) {
            itemToBuy.add(Item);
        }

        public void removeItem(int index) {
            itemToBuy.remove(index);
        }

        @Override
        public int getCount() {
            return itemToBuy.size();
        }

        @Override
        public Object getItem(int i) {
            return itemToBuy.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public List<item> getItemToBuy() {
            return itemToBuy;
        }

    static class ViewHolder {
            ImageView ivIcon;
            TextView tvItem;
            TextView tvPrice;
            TextView tvDesc;
            CheckBox checkBox;
            Button deleteButton;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                v = inflater.inflate(R.layout.row_item, null);
                ViewHolder holder = new ViewHolder();
                holder.ivIcon = (ImageView) v.findViewById(R.id.ivIcon);
                holder.tvItem = (TextView) v.findViewById(R.id.tvItem);
                holder.tvPrice = (TextView) v.findViewById(R.id.tvPrice);
                holder.tvDesc= (TextView) v.findViewById(R.id.tvDescription);
                holder.checkBox= (CheckBox) v.findViewById(R.id.checkbox);

                holder.deleteButton= (Button) v.findViewById(R.id.delete_item);
                v.setTag(holder);
            }
           // Place place = placesToVisit.get(position);
            final item Item = itemToBuy.get(position);
            if (Item != null) {
                final ViewHolder holder = (ViewHolder) v.getTag();
                if(Item.getifBought()==true)
                {
                    //holder.checkBox.setChecked(true);
                    holder.checkBox.setChecked(true);
                    Log.d("datasource","checkbox is checked right");
                }
                else
                {
                    Log.d("datasource","bought is false? is that even possible, I will still set the checkbox true");
                    holder.checkBox.setChecked(false);
                }
                holder.tvItem.setTextColor(Color.RED);
                holder.tvItem.setText(Item.getItemName());
                holder.tvPrice.setText("Price: "+Item.getEstimatedPrice());
                holder.tvDesc.setText("Description: "+Item.getDescription());
               /* if(Item.getifBought()==true)
                {
                    //holder.checkBox.setChecked(true);
                    holder.checkBox.setChecked(true);
                    Log.d("datasource","checkbox is checked right");
                }
                else
                {
                    holder.checkBox.setChecked(false);
                }*/
                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(holder.checkBox.isChecked())
                        {
                            Item.setifBought(true);
                            Toast.makeText(v.getContext(),Item.getItemName()+" is purchased!",Toast.LENGTH_LONG).show();
                            Log.d("datasource","tell me if it is checked"+Item.getifBought());
                        }
                        else
                        {
                            Item.setifBought(false);
                            Log.d("datasource","tell me if it is checked and here is not checked"+Item.getifBought());
                        }
                    }
                });
                //this is a test. May crash
                holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       itemToBuy.remove(Item);

                        notifyDataSetChanged();

                    }
                });

                holder.ivIcon.setImageResource(Item.getCategory().getIconId());
            }
            return v;
        }


}
