package com.chan.weava.chandroidapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.chan.weava.chandroidapp.data.ChanThread;
import com.chan.weava.chandroidapp.fragments.CatalogFragment;
import com.chan.weava.chandroidapp.networkRetrieve.CatalogCreator;
import com.chan.weava.chandroidapp.utils.BundleIdentityStrings;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Catalog Activity
 *
 * This class contains the base activity for the catalog view. It handles
 * all data manipulation for the front end UI for the catalog thread
 * viewer.
 *
 * Using @see CatalogCreator, this class retrieves a list of all the threads
 * posted based on board selected and page number. It then sends that
 * list to a fragment to be translated into UI elements.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v[Insert Version Number]
 * @since 9/24/14
 */
public class CatalogActivity extends ActionBarActivity implements CatalogFragment.OnDataPass
{
    private ArrayList<ChanThread> threads;
    private String boardLink;
    private String boardTitle;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Intent intent = getIntent();
        boardLink = intent.getStringExtra(BundleIdentityStrings.BOARD_INTENT_IDENTIFIER);
        boardTitle = intent.getStringExtra(BundleIdentityStrings.BOARD_INTENT_TITLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        toolbar.setTitle("/" + boardLink + "/ " + boardTitle);
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setTintColor(getResources().getColor(R.color.action_bar_color));
        tintManager.setNavigationBarTintColor(getResources().getColor(R.color.nav_bar_color));
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        this.threads = new ArrayList<>();

        try
        {
            CatalogCreator catalogCreator = new CatalogCreator(boardLink, 1);
            catalogCreator.parseJsonObjectToArray();
            this.threads = catalogCreator.getThreads();
            this.bundleWithFragment();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void bundleWithFragment()
    {
        Parcelable[] toPass = new Parcelable[threads.size()];
        for(int i = 0; i < threads.size(); i++)
        {
            toPass[i] = threads.get(i);
        }

        System.out.println("TOPASS LENGTH: " + toPass.length);

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(BundleIdentityStrings.THREAD_IDENTIFIER, toPass);
        bundle.putString(BundleIdentityStrings.BOARD_LINK_IDENTIFIER, boardLink);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CatalogFragment catalogFragment = new CatalogFragment();
        catalogFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.catalog_fragment_container, catalogFragment);
        fragmentTransaction.commit();
    }

    public void goToThread(int position)
    {
        Intent i = new Intent(this, RepliesActivity.class);
        String threadNum = Integer.toString(threads.get(position).getPostNumber());
        i.putExtra(BundleIdentityStrings.REPLIES_INTENT_IDENTIFIER_LINK, boardLink);
        i.putExtra(BundleIdentityStrings.BOARD_INTENT_TITLE, boardTitle);
        i.putExtra(BundleIdentityStrings.REPLIES_INTENT_IDENTIFIER_NUM, threadNum);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(this, Main.class);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

    @Override
    public void onDataPass(int position)
    {
        goToThread(position);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}