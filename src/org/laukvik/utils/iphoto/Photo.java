/*
 * Copyright (C) 2014 Morten Laukvik
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.laukvik.utils.iphoto;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <key>MediaType</key>
 * <string>Image</string>
 * <key>Caption</key>
 * <string>bilde88stor</string>
 * <key>Comment</key>
 * <string></string>
 * <key>Aspect Ratio</key>
 * <real>0.749333</real>
 * <key>Rating</key>
 * <integer>0</integer>
 * <key>Roll</key>
 * <integer>2894</integer>
 * <key>DateAsTimerInterval</key>
 * <real>69666188.000000</real>
 * <key>ModDateAsTimerInterval</key>
 * <real>69666188.000000</real>
 * <key>MetaModDateAsTimerInterval</key>
 * <real>0.000000</real>
 * <key>ImagePath</key>
 * <string>/Users/morten/Pictures/iPhoto
 * Library/Originals/2003/Mats/bilde88stor.jpg</string>
 * <key>ThumbPath</key>
 * <string>/Users/morten/Pictures/iPhoto
 * Library/Data/2003/Mats/bilde88stor.jpg</string>
 *
 * @author morten
 *
 */
public class Photo {

    private String name;
    private String path;
    private String description;
    private Node node;
    private Album album;
    private String iphoto;

    public Photo(Node node) {
        this.node = node;
        this.name = getProperty("Caption");
        this.path = getProperty("ThumbPath");
        this.description = getProperty("Comment");
        this.iphoto = getProperty("ImagePath");
//		System.out.println( "Photo created: " + name + " " + path );
    }

    public Photo(String name, String path) {
        this.name = name;
        this.path = path;
//		System.out.println( "Photo created: " + name + " - " + path  );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getProperty(String key) {
        NodeList list = node.getChildNodes();
        for (int x = 0; x < list.getLength(); x++) {
            Node child = list.item(x);
            if (child.getNodeName().equalsIgnoreCase("key")) {
                if (child.getTextContent().equalsIgnoreCase(key)) {
                    return list.item(x + 2).getTextContent();
                }
            }
        }
        return "";
    }

    public String getIphoto() {
        return iphoto;
    }

}
