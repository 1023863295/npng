package com.sanxiongdi.npng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sanxiongdi.npng.consts.BaseFounction;
import com.sanxiongdi.npng.guohaiyang.GhyFounction;
import com.sanxiongdi.npng.zhangbeibei.ZbbFounction;
import com.sanxiongdi.npng.liuliang.LlFounction;
import com.sanxiongdi.npng.util.ToastUtil;
import com.sanxiongdi.npng.zhangbeibei.stickygridheaders.Founction;
import com.sanxiongdi.npng.zhangbeibei.stickygridheaders.ItemDetailActivity;
import com.sanxiongdi.npng.zhangbeibei.stickygridheaders.ItemDetailFragment;
import com.sanxiongdi.npng.zhangbeibei.stickygridheaders.ItemListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * function:应用主页
 * zbb
 * 2016.10.26
 * hearstzhang@gmail.com
 */
public class MainActivity extends AppCompatActivity implements ItemListFragment.Callbacks {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ItemListFragment) getSupportFragmentManager().findFragmentById(R.id.item_list)).setActivateOnItemClick(true);
        }
        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link ItemListFragment.Callbacks} indicating that
     * the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(int id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(ItemDetailFragment.ARG_ITEM_ID, id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
