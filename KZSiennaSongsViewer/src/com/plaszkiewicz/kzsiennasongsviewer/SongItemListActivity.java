package com.plaszkiewicz.kzsiennasongsviewer;

import java.io.InputStream;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.plaszkiewicz.kzsiennasongsviewer.aom.SongsContent;
import com.plaszkiewicz.kzsiennasongsviewer.aom.SongsImporter;

/**
 * An activity representing a list of SongItems. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link SongItemDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link SongItemListFragment} and the item details (if present) is a
 * {@link SongItemDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link SongItemListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class SongItemListActivity extends FragmentActivity implements
		SongItemListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	
	// Search EditText
    EditText inputSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_songitem_list);
		
		InputStream songsInputStream = this.getResources().openRawResource(R.raw.songs);
		if (SongsContent.ITEMS.size() == 0){
			SongsImporter songsImporter = new SongsImporter();
			try {
				songsImporter.importSongs(songsInputStream);
			}
			catch (Exception e){
				System.err.println("Error when loading songs!");
			}
		}
		

		if (findViewById(R.id.songitem_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((SongItemListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.songitem_list))
					.setActivateOnItemClick(true);
		}
		
		SongItemListFragment songItemListFragment = (SongItemListFragment) getSupportFragmentManager().findFragmentById(R.id.songitem_list);
		inputSearch = (EditText) songItemListFragment.getActivity().findViewById(R.id.inputSearch);
		inputSearch = (EditText) findViewById(R.id.inputSearch);
         
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
            	SongItemListFragment songItemListFragment = (SongItemListFragment) getSupportFragmentManager().findFragmentById(R.id.songitem_list);
                ArrayAdapter adapter = (ArrayAdapter) songItemListFragment.getListAdapter();
                adapter.getFilter().filter(cs);   
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub                          
            }
        });

		// TODO: If exposing deep links into your app, handle intents here.
	}
	
	public void clearButtonClicked(View v) {
		inputSearch = (EditText) findViewById(R.id.inputSearch);
    	inputSearch.setText("");
	 } 

	/**
	 * Callback method from {@link SongItemListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(SongItemDetailFragment.ARG_ITEM_ID, id);
			SongItemDetailFragment fragment = new SongItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.songitem_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, SongItemDetailActivity.class);
			detailIntent.putExtra(SongItemDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
