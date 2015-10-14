package com.study.model;

import com.lidroid.xutils.db.annotation.NoAutoIncrement;

/**
 * Created by hugo on 15/10/7.
 *
 * Courses Base url: https://api.coursera.org/api/catalog.v1/courses
 *
 * Fields :
 * id: Int - The Course Id.
 * shortName: String - The short name associated with the course.
 * name: String - The course name or title.
 * language: String - The language code for the course. (e.g. `en` means English.)
 * largeIcon: Option[String] - A large image to represent the course.
 * photo: Option[String] - A course photo.
 * previewLink: Option[String] - If the course has enabled preview, this is the preview URL.
 * shortDescription: Option[String] - A short description of the course.
 * smallIcon: Option[String] - A small icon.
 * smallIconHover: Option[String] - Hovered icon.
 * subtitleLanguagesCsv: String - Translated languages.
 * isTranslate: Boolean - Whether the course is translated.
 * universityLogo: Option[String] - If there is a special university logo.
 * universityLogoSt: Option[String] - Signature track university logo.
 * video: Option[String] - The YouTube video code. e.g. <https://www.youtube.com/watch?v=CCmTQW7OebM>
 * videoId: Option[String] - Synonym for video
 * aboutTheCourse: Option[String] - HTML snippet describing the course.
 * targetAudience: Option[Int] - Description of the intended course audience. The target audiences are: 0 - Basic undergraduates, 1 - Advanced undergraduates or beginning graduates, 2 - Advanced graduates, and 3 - Other
 * faq: Option[String] - HTML snippet answering frequently asked questions.
 * courseSyllabus: Option[String] - HTML snippet containing the course syllabus.
 * courseFormat: Option[String] - Format description.
 * suggestedReadings: Option[String] - HTML snippet containing suggested readings.
 * instructor: Option[String] - Instructor name(s) and title(s).
 * estimatedClassWorkload: Option[String] - Description of the estimated workload for the course.
 * aboutTheInstructor: Option[String] - HTML snippet about the instructor.
 * recommendedBackground: Option[String] - HTML snippet describing recommended background.
 * 
 * Includes :
 * sessions - The sessions for a course.
 * instructors - The instructors for a course.
 * universities - The universities associated with a course.
 * categories - The categories of the course.
 *
 */
public class CoursesModel {

    @NoAutoIncrement
    private int id;
    private String shortName;
    private String name;
    private String language;
    private String largeIcon;
    private String photo;
    private String previewLink;
    private String shortDescription;
    private String smallIcon;
    private String smallIconHover;
    private String subtitleLanguagesCsv;
    private boolean isTranslate;
    private String universityLogo;
    private String universityLogoSt;
    private String video;
    private String videoId;
    private String aboutTheCourse;
    private int targetAudience;
    private String faq;
    private String courseSyllabus;
    private String courseFormat;
    private String suggestedReadings;
    private String instructor;
    private String estimatedClassWorkload;
    private String aboutTheInstructor;
    private String recommendedBackground;

    private CoursesLinksModel links;

    private String universityName;
    private int classTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLargeIcon() {
        return largeIcon;
    }

    public void setLargeIcon(String largeIcon) {
        this.largeIcon = largeIcon;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getSmallIconHover() {
        return smallIconHover;
    }

    public void setSmallIconHover(String smallIconHover) {
        this.smallIconHover = smallIconHover;
    }

    public String getSubtitleLanguagesCsv() {
        return subtitleLanguagesCsv;
    }

    public void setSubtitleLanguagesCsv(String subtitleLanguagesCsv) {
        this.subtitleLanguagesCsv = subtitleLanguagesCsv;
    }

    public String getUniversityLogo() {
        return universityLogo;
    }

    public void setUniversityLogo(String universityLogo) {
        this.universityLogo = universityLogo;
    }

    public String getUniversityLogoSt() {
        return universityLogoSt;
    }

    public void setUniversityLogoSt(String universityLogoSt) {
        this.universityLogoSt = universityLogoSt;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getAboutTheCourse() {
        return aboutTheCourse;
    }

    public void setAboutTheCourse(String aboutTheCourse) {
        this.aboutTheCourse = aboutTheCourse;
    }

    public int getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(int targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getFaq() {
        return faq;
    }

    public void setFaq(String faq) {
        this.faq = faq;
    }

    public String getCourseSyllabus() {
        return courseSyllabus;
    }

    public void setCourseSyllabus(String courseSyllabus) {
        this.courseSyllabus = courseSyllabus;
    }

    public String getCourseFormat() {
        return courseFormat;
    }

    public void setCourseFormat(String courseFormat) {
        this.courseFormat = courseFormat;
    }

    public String getSuggestedReadings() {
        return suggestedReadings;
    }

    public void setSuggestedReadings(String suggestedReadings) {
        this.suggestedReadings = suggestedReadings;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getEstimatedClassWorkload() {
        return estimatedClassWorkload;
    }

    public void setEstimatedClassWorkload(String estimatedClassWorkload) {
        this.estimatedClassWorkload = estimatedClassWorkload;
    }

    public String getAboutTheInstructor() {
        return aboutTheInstructor;
    }

    public void setAboutTheInstructor(String aboutTheInstructor) {
        this.aboutTheInstructor = aboutTheInstructor;
    }

    public String getRecommendedBackground() {
        return recommendedBackground;
    }

    public void setRecommendedBackground(String recommendedBackground) {
        this.recommendedBackground = recommendedBackground;
    }

    public CoursesLinksModel getLinks() {
        return links;
    }

    public void setLinks(CoursesLinksModel links) {
        this.links = links;
    }

    public boolean isTranslate() {
        return isTranslate;
    }

    public void setIsTranslate(boolean isTranslate) {
        this.isTranslate = isTranslate;
    }


    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getClassTime() {
        return classTime;
    }

    public void setClassTime(int classTime) {
        this.classTime = classTime;
    }
}
