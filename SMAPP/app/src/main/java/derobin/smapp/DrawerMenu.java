package derobin.smapp;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 13-4-2016.
 */
public class DrawerMenu {
    List<MenuItem> items;

    private static enum ITEM {
        Profile,
        Uitloggen
    }
    public DrawerMenu() {
        items = new ArrayList<>();
        items.add(new MenuItem(ITEM.Profile));
        items.add(new MenuItem(ITEM.Uitloggen));
    }

    private class MenuItem {
        public ITEM item;
        public MenuItem(ITEM item) {
            this.item=item;
        }
    }

    public String[] getMenuItems(){
        String[] v = new String[items.size()];
        for(int i=0; i<v.length; i++) {
            v[i] = ITEM.values()[i].toString();
        }
        return v;
    }

    public Intent getMenuToOpen(Context context, int position) {
        ITEM current = ITEM.values()[position];

        switch (current) {
            case Profile:
                return new Intent(context, profileActivity.class);
            case Uitloggen:
                return new Intent(context, loginActivity.class);
        }
        return null;
    }
}
