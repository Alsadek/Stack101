package org.sadek.stackmachine.ui;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.csc205.template.stackmachine.R;

public class ToolActivity extends AppCompatActivity {

    private Fragment fragment;
    private int fragmentId;

    //OnCreate Method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);

        fragmentId = R.id.activityTool_container;

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.logo_template);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Check if fragment already exists
        FragmentManager fm = getFragmentManager();
        fragment = fm.findFragmentById(fragmentId);
        if (fragment == null) {
            fragment = StackMachineFragment.newInstance();

            fm.beginTransaction().add(fragmentId, fragment).commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_info: {
                FragmentManager fm = getFragmentManager();
                InfoDialogFragment dialogFragment;
                dialogFragment = InfoDialogFragment.newInstance(R.raw.info);
                dialogFragment.show(fm, InfoDialogFragment.dialogTag);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
