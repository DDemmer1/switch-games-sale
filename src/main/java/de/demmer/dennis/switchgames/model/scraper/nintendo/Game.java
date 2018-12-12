package de.demmer.dennis.switchgames.model.scraper.nintendo;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class Game {

    private String title;
    private String publisher;
    private String url;
    private boolean nintendoClub;
    private String releaseDay;
    private String saleUntil;
    private String imageURL;
    private String priceDiscountpercentage;
    private List<String> categories;
    private boolean digitalVersion;
    private boolean physicalVersion;
    private String numberOfPlayers;
    private String ageRating;
    private String excerpt;
    private float price;

    private String metacriticScore;


    public Game(String title, String publisher, String url, boolean nintendoClub, String releaseDay, String saleUntil, String imageURL, String priceDiscountpercentage, List<String> categories, boolean digitalVersion, boolean physicalVersion, String numberOfPlayers, String ageRating, String excerpt, float price) {
        this.title = title;
        this.publisher = publisher;
        this.url = url;
        this.nintendoClub = nintendoClub;
        this.releaseDay = releaseDay;
        this.saleUntil = saleUntil;
        this.imageURL = imageURL;
        this.priceDiscountpercentage = priceDiscountpercentage;
        this.categories = categories;
        this.digitalVersion = digitalVersion;
        this.physicalVersion = physicalVersion;
        this.numberOfPlayers = numberOfPlayers;
        this.ageRating = ageRating;
        this.excerpt = excerpt;
        this.price = price;
    }


    public String getMetacriticScore() {
        return metacriticScore;
    }

    public void setMetacriticScore(String metacriticScore) {
        this.metacriticScore = metacriticScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isNintendoClub() {
        return nintendoClub;
    }

    public void setNintendoClub(boolean nintendoClub) {
        this.nintendoClub = nintendoClub;
    }

    public String getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(String releaseDay) {
        this.releaseDay = releaseDay;
    }

    public String getSaleUntil() {
        return saleUntil;
    }

    public void setSaleUntil(String saleUntil) {
        this.saleUntil = saleUntil;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPriceDiscountpercentage() {
        return priceDiscountpercentage;
    }

    public void setPriceDiscountpercentage(String priceDiscountpercentage) {
        this.priceDiscountpercentage = priceDiscountpercentage;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public boolean isDigitalVersion() {
        return digitalVersion;
    }

    public void setDigitalVersion(boolean digitalVersion) {
        this.digitalVersion = digitalVersion;
    }

    public boolean isPhysicalVersion() {
        return physicalVersion;
    }

    public void setPhysicalVersion(boolean physicalVersion) {
        this.physicalVersion = physicalVersion;
    }

    public String getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public String getOldPrice(){

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String s = df.format(price / (100f-Float.valueOf(priceDiscountpercentage)) *100);

        return s;
    }


    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", url='" + url + '\'' +
                ", nintendoClub=" + nintendoClub +
                ", releaseDay='" + releaseDay + '\'' +
                ", saleUntil='" + saleUntil + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", priceDiscountpercentage='" + priceDiscountpercentage + '\'' +
                ", categories=" + categories +
                ", digitalVersion=" + digitalVersion +
                ", physicalVersion=" + physicalVersion +
                ", numberOfPlayers='" + numberOfPlayers + '\'' +
                ", ageRating='" + ageRating + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", price=" + price +
                '}';
    }
}
