package derobin.smapp.Tool;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import derobin.smapp.Activities.loginActivity;
import derobin.smapp.Activities.profileActivity;

/**
 * Created by Robin on 13-4-2016.
 */
public class DrawerMenu {
    List<MenuItem> menuItems;

    private static enum ITEM {
        Profile,
        Logout
    }
    public DrawerMenu() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(ITEM.Profile, profileActivity.class));
        menuItems.add(new MenuItem(ITEM.Logout, loginActivity.class));
    }

    private class MenuItem {
        public ITEM item;
        public Class referral;
        public MenuItem(ITEM item, Class referral) {
            this.item=item;
            this.referral = referral;
        }
    }

    public String[] getMenuItems(){
        String[] items = new String[menuItems.size()];
        for(int i=0; i<menuItems.size(); i++) {
            items[i]= ITEM.values()[i].toString();
        }
        return items;
    }

    public void OpenMenu(Context context, int position) {
        Intent i= new Intent(context, menuItems.get(position).referral);
        context.startActivity(i);
    }
}
