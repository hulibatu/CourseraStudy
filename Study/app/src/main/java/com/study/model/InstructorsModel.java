package com.study.model;

import com.lidroid.xutils.db.annotation.NoAutoIncrement;

/**
 * Created by hugo on 15/10/7.
 *
 * Instructors Base url: https://api.coursera.org/api/catalog.v1/instructors
 *
 * Fields :
 * id: Int - Instructor ID
 * photo: Option[String] - photo URL
 * photo150: Option[String] - photo URL 150x150px
 * bio: String - Instructor Biogrophy
 * prefixName: Option[String] - Prefix for the instructor’s name
 * firstName: Option[String] - Instructor first name
 * middleName: Option[String] - Instructor middle name
 * lastName: Option[String] - Instructor last name
 * suffixName: Option[String] - Suffix for the instructor name
 * fullName: Option[String] - Instructor full name
 * title: Option[String] - Instructor title
 * department: Option[String] - Instructor department
 * website: Option[String] - Instructor’s personal website
 * websiteTwitter: Option[String] - Instructor’s twitter handle.
 * websiteFacebook: Option[String] - Instructor’s facebook page.
 * websiteLinkedin: Option[String] - Instructor’s LinkedIn profile.
 * websiteGplus: Option[String] - Instructor’s Google+ Website.
 * shortName: Option[String] - Instructor’s short name.
 *
 * Includes :
 * universities - The universities an instructor is associated with.
 * courses - The courses an instructor teaches.
 * sessions - The sessions an instructor teaches.
 *
 */
public class InstructorsModel {

    @NoAutoIncrement
    private int id;
    private String photo;
    private String photo150;
    private String bio;
    private String prefixName;
    private String firstName;
    private String middleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto150() {
        return photo150;
    }

    public void setPhoto150(String photo150) {
        this.photo150 = photo150;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getWebsiteLinkedin() {
        return websiteLinkedin;
    }

    public void setWebsiteLinkedin(String websiteLinkedin) {
        this.websiteLinkedin = websiteLinkedin;
    }

    public String getWebsiteGplus() {
        return websiteGplus;
    }

    public void setWebsiteGplus(String websiteGplus) {
        this.websiteGplus = websiteGplus;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    private String lastName;
    private String suffixName;
    private String fullName;
    private String title;
    private String department;
    private String website;
    private String websiteTwitter;
    private String websiteFacebook;
    private String websiteLinkedin;
    private String websiteGplus;
    private String shortName;

}
