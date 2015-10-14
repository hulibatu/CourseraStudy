package com.study.model;

import com.lidroid.xutils.db.annotation.NoAutoIncrement;

/**
 * Created by hugo on 15/10/7.
 *
 * Universities Base url: https://api.coursera.org/api/catalog.v1/universities
 *
 * Fields :
 * id: Int - The University Id
 * name: String - The university’s name
 * shortName: String - The short name associated with the university.
 * description: Option[String] - The university’s description.
 * banner: Option[String] - The URL to a background banner image
 * homeLink: Option[String] - Link to the university’s home page.
 * location: Option[String] - Human-readable description of the partner’s location.
 * locationCity: Option[String] - University’s city.
 * locationState: Option[String] - University’s state.
 * locationCountry: Option[String] - University’s country.
 * locationLat: Option[Double] - University’s latitude.
 * locationLng: Option[Double] - University’s longitude.
 * classLogo: Option[String] - URL to the logo used inside courses.
 * website: Option[String] - University’s website.
 * websiteTwitter: Option[String] - University’s offical twitter handle.
 * websiteFacebook: Option[String] - University’s facebook page.
 * websiteYoutube: Option[String] - University’s youtube channel.
 * logo: Option[String] - University’s Logo
 * squareLogo: Option[String] - University’s Logo (Square)
 * landingPageBanner: Option[String] - High-resolution banner for use on the Coursera home landing page.
 *
 * Includes :
 * courses - The courses an instructor teaches.
 * instructors - The instructors associated with a university.
 *
 */
public class UniversitiesModel {

    @NoAutoIncrement
    private int id;
    private String name;
    private String shortName;
    private String description;
    private String banner;
    private String homeLink;
    private String location;
    private String locationCity;
    private String locationState;
    private String locationCountry;
    private String locationLat;
    private String locationLng;
    private String classLogo;
    private String website;
    private String websiteTwitter;
    private String websiteFacebook;
    private String websiteYoutube;
    private String logo;
    private String squareLogo;
    private String landingPageBanner;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomeLink() {
        return homeLink;
    }

    public void setHomeLink(String homeLink) {
        this.homeLink = homeLink;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLng() {
        return locationLng;
    }

    public void setLocationLng(String locationLng) {
        this.locationLng = locationLng;
    }

    public String getClassLogo() {
        return classLogo;
    }

    public void setClassLogo(String classLogo) {
        this.classLogo = classLogo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsiteTwitter() {
        return websiteTwitter;
    }

    public void setWebsiteTwitter(String websiteTwitter) {
        this.websiteTwitter = websiteTwitter;
    }

    public String getWebsiteFacebook() {
        return websiteFacebook;
    }

    public void setWebsiteFacebook(String websiteFacebook) {
        this.websiteFacebook = websiteFacebook;
    }

    public String getWebsiteYoutube() {
        return websiteYoutube;
    }

    public void setWebsiteYoutube(String websiteYoutube) {
        this.websiteYoutube = websiteYoutube;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSquareLogo() {
        return squareLogo;
    }

    public void setSquareLogo(String squareLogo) {
        this.squareLogo = squareLogo;
    }

    public String getLandingPageBanner() {
        return landingPageBanner;
    }

    public void setLandingPageBanner(String landingPageBanner) {
        this.landingPageBanner = landingPageBanner;
    }
}
