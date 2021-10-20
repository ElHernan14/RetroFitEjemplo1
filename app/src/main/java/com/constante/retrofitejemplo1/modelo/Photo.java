package com.constante.retrofitejemplo1.modelo;

public class Photo {
    private String title;
    private int albumId;
    private String url;
    private int id;

    public Photo(String title, String url, int albumId, int id) {
        this.title = title;
        this.url = url;
        this.albumId = albumId;
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
