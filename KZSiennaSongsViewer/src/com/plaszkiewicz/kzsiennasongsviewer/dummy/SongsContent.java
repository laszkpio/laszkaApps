package com.plaszkiewicz.kzsiennasongsviewer.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lista piosenek
 */
public class SongsContent {

	/**
	 * An array of sample items.
	 */
	public static List<SongItem> ITEMS = new ArrayList<SongItem>();

	/**
	 * A map of song items, by ID.
	 */
	public static Map<String, SongItem> ITEM_MAP = new HashMap<String, SongItem>();

	public static void addItem(SongItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class SongItem implements Comparable<SongItem>{
		public String id;
		public String title;
		public String content;

		public SongItem(String id, String title, String content) {
			this.id = id;
			this.title = title;
			this.content = content;
		}

		@Override
		public String toString() {
			return title;
		}

		@Override
		public int compareTo(SongItem another) {
			return this.title.compareTo(another.title);
		}
	}
}
