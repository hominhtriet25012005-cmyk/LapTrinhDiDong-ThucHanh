package com.example.laptrinhdidong;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int MENU_REGISTRATION_LAYOUT = 1;
    private static final int MENU_CALCULATOR_LAYOUT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENU_REGISTRATION_LAYOUT, Menu.NONE,
                        R.string.open_registration_layout)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.add(Menu.NONE, MENU_CALCULATOR_LAYOUT, Menu.NONE,
                        R.string.open_calculator_layout)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == MENU_REGISTRATION_LAYOUT) {
            startActivity(new Intent(this, MainActivity2.class));
            return true;
        }
        if (item.getItemId() == MENU_CALCULATOR_LAYOUT) {
            startActivity(new Intent(this, MainActivity3.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
