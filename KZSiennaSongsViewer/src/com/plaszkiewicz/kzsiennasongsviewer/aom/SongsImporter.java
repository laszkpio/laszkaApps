package com.plaszkiewicz.kzsiennasongsviewer.aom;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.plaszkiewicz.kzsiennasongsviewer.aom.SongsContent.SongItem;

public class SongsImporter {
	
	public SongsImporter(){};
	
	public void importSongs(InputStream songsInputStream) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(songsInputStream);
		// normalize text representation
		doc.getDocumentElement().normalize();

		NodeList piesni = doc.getElementsByTagName("ROW");
		
		ArrayList<SongItem> songs = new ArrayList<SongsContent.SongItem>();

		for (int s = 0; s < piesni.getLength(); s++) {
			Node pierwszaPiesnNode = piesni.item(s);
			if (pierwszaPiesnNode.getNodeType() == Node.ELEMENT_NODE) {
				Element pierwszaPiesnElement = (Element) pierwszaPiesnNode;
				NodeList tytul = pierwszaPiesnElement.getElementsByTagName("songtitle");
				Element tytulElement = (Element) tytul.item(0);

				NodeList textFNList = tytulElement.getChildNodes();
				String tytulS = ((Node) textFNList.item(0)).getNodeValue().trim();

				NodeList slowa = pierwszaPiesnElement.getElementsByTagName("lyrics");
				Element slowaElement = (Element) slowa.item(0);

				NodeList textLNList = slowaElement.getChildNodes();
				String slowaS = ((Node) textLNList.item(0)).getNodeValue().trim();
				
				if (tytulS!=null && !TextUtils.isEmpty(tytulS)){
					SongItem songItem = new SongItem(String.valueOf(s+1), tytulS, slowaS);
					songs.add(songItem);
				}
			}
		}
		
		Collections.sort(songs);
		for(SongItem songItem: songs){
			SongsContent.addItem(songItem);
		}
	}

}
