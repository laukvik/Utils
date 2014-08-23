package org.laukvik.utils.iphoto;

import java.util.Vector;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Album {

    private Vector<Photo> photos = new Vector<Photo>();
    private String name;
    private Node node;
    private Library library;
    private Node listOfMasterImages;
    private Vector<String> ids = new Vector<String>();
    private String albumID;

    public Album(Node node) {
        this.node = node;
        this.name = getProperty("AlbumName");
    }

    public Album(String name) {
        this.name = name;
    }

    public Album() {
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public String getAlbumID() {
        return albumID;
    }

    public String getComments() {
        return getProperty("Comments");
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Photo photo) {
        photo.setAlbum(this);
        photos.add(photo);
    }

    public void remove(Photo photo) {
        photos.remove(photo);
        photo.setAlbum(null);
    }

    public int size() {
        cache();
        return photos.size();
    }

    public Photo list(int index) {
        cache();
        return photos.get(index);
    }

    public void cache() {
        if (listOfMasterImages != null) {
            for (int n = 0; n < ids.size(); n++) {
                addPhoto((String) ids.get(n));
            }

        }
        listOfMasterImages = null;
    }

    public Photo[] list() {
        cache();

        Photo[] photoArr = new Photo[photos.size()];
        return photos.toArray(photoArr);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
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
        return null;
    }

    public String toString() {
        return name;
    }

    public void log(String message) {
        System.out.println(message);
    }

    public void addPhoto(String photoID) {
        NodeList list = listOfMasterImages.getChildNodes();
        for (int x = 0; x < list.getLength(); x++) {
            Node node = list.item(x);
            if (node.getNodeName().equalsIgnoreCase("key")) {
                if (node.getTextContent().equalsIgnoreCase(photoID)) {
                    Node imageDict = list.item(x + 2);
                    photos.add(new Photo(imageDict));
                }
            }
        }
    }

    public void addById(String photoID, Node listOfMasterImages) {
        this.listOfMasterImages = listOfMasterImages;
        ids.add(photoID);
    }
}
