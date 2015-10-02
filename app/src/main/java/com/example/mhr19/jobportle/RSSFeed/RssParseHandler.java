package com.example.mhr19.jobportle.RSSFeed;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * SAX tag handler
 * 
 * @author ITCuties
 *
 */
public class RssParseHandler extends DefaultHandler {

	private List<Item> rssItems;
	
	// Used to reference item while parsing
	private Item currentItem;
	
	// Parsing title indicator
	private boolean parsingTitle;
	// Parsing link indicator
	private boolean parsingLink;

    private boolean parsingDate;

    private boolean parsingDescritpion;
	
	public RssParseHandler() {
		rssItems = new ArrayList<Item>();
	}
	
	public List<Item> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("item".equals(qName)) {
			currentItem = new Item();
		} else if ("title".equals(qName)) {
			parsingTitle = true;
		}
        else if ("description".equals(qName)) {
            parsingDescritpion = true;
        }
        else if ("date".equals(qName)) {
            parsingDate = true;
        }else if ("link".equals(qName)) {
			parsingLink = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("item".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("title".equals(qName)) {
			parsingTitle = false;
		}
        else if ("description".equals(qName)) {
            parsingDescritpion = false;
        }
        else if ("date".equals(qName)) {
            parsingDate = false;
        }else if ("link".equals(qName)) {
			parsingLink = false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
        if (parsingTitle) {
            if (currentItem != null)
                currentItem.setTitle(new String(ch, start, length));
        } else if (parsingDescritpion) {
            if (currentItem != null) {
                currentItem.setDescription(new String(ch, start, length));
                parsingLink = false;
            } }
        else if (parsingDate) {
                if (currentItem != null) {
                    currentItem.setDate(new String(ch, start, length));
                    parsingLink = false;
                }}
        else if (parsingLink) {
                    if (currentItem != null) {
                        currentItem.setLink(new String(ch, start, length));
                        parsingLink = false;
                    }
                }
            }

        }

