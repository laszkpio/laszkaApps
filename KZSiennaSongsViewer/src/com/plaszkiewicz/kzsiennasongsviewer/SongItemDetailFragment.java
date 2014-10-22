package com.plaszkiewicz.kzsiennasongsviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plaszkiewicz.kzsiennasongsviewer.dummy.SongsContent;

/**
 * A fragment representing a single SongItem detail screen. This fragment is
 * either contained in a {@link SongItemListActivity} in two-pane mode (on
 * tablets) or a {@link SongItemDetailActivity} on handsets.
 */
public class SongItemDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The content this fragment is presenting.
	 */
	private SongsContent.SongItem mItem;
	
	private TextView songItemDetailsView;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public SongItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			String itemId = getArguments().getString(ARG_ITEM_ID);
			mItem = SongsContent.ITEM_MAP.get(itemId);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_songitem_detail,
				container, false);

		// Show the content as text in a TextView.
		if (mItem != null) {
			songItemDetailsView = (TextView)rootView.findViewById(R.id.songitem_detail);
			songItemDetailsView.setText(mItem.content);
		}

		return rootView;
	}

	public TextView getSongItemDetailsView() {
		return songItemDetailsView;
	}
	
}
