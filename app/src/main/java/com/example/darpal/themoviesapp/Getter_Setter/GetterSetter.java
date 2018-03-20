package com.example.darpal.themoviesapp.Getter_Setter;

public class GetterSetter {

    String moviename;
    String release;
    String rate;
    String synopsis;
    String imgURL;
    String path = "https://image.tmdb.org/t/p/w500/";

    public String getMoviename() {
        return moviename;
    }

    public String getRelease() {
        return release;
    }

    public String getRate() {
        return rate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getImgURL() {
        return path + imgURL;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
