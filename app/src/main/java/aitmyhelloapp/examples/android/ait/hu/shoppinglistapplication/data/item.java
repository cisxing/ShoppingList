package aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.data;

import java.io.Serializable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import aitmyhelloapp.examples.android.ait.hu.shoppinglistapplication.R;

/**
 * Created by xinyunxing on 10/16/2014.
 */

public class item implements Serializable {

    //changed it from placetype

    public enum Category {
        MEAT(0, R.drawable.food_image1),
        FRUITS(1,R.drawable.apple_image1),
        BOOK(2,R.drawable.book_image2)
        ;
        private int value;
        private int iconId;

        private Category(int value, int iconId) {
            this.value = value;
            this.iconId = iconId;
        }

        public static Category fromInt(int value) {
            for (Category c : Category.values()) {
                if (c.value == value) {
                    return c;
                }
            }
            return MEAT;
        }

        public int getIconId() {
            return iconId;
        }

        public int getValue(){
            return value;
        }
    }

    private Category category;
    private String itemName;
    private String description;
    private String estimatedPrice;
    private boolean ifBought;
    private long id;

    public item(Category category, String itemName, String description, String estimatedPrice) {
        this.category = category;
        this.itemName = itemName;
        this.description = description;
        this.estimatedPrice = estimatedPrice;
        ifBought = false;
    }

    public Category getCategory() {
        return category;
    }

    public String getItemName() {
        return itemName;
    }

    public String getEstimatedPrice() {
        return estimatedPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setifBought(boolean bought)
    {
        ifBought=bought;
    }
    public boolean getifBought()
    {
        return ifBought;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("categoryValue=").append(category.getValue());
        sb.append(", ").append("categoryIcon=").append(category.getIconId());
        sb.append(", ").append("itemName=").append(itemName);
        sb.append(", ").append("description=").append(description);
        sb.append(", ").append("estimatedPrice=").append(estimatedPrice);

        return sb.toString();
    }

    public long getId()
    {
        return id;
    }

    public void setId(long newid)
    {
        id= newid;
    }

}
