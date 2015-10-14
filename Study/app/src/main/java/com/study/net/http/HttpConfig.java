package com.study.net.http;

import java.util.ArrayList;

/**
 * Created by hugo on 15/10/7.
 */
public class HttpConfig {

    public final static String Fields = "fields=";
    public final static String Includes = "includes=";
    public final static String Query = "q=search&query=";
    public final static String IDS = "ids=";

    public final static String CoursesBaseUrl = "https://api.coursera.org/api/catalog.v1/courses";
    public final static String UniversitiesBaseUrl = "https://api.coursera.org/api/catalog.v1/universities";
    public final static String CategoriesBaseUrl = "https://api.coursera.org/api/catalog.v1/categories";
    public final static String InstructorsBaseUrl = "https://api.coursera.org/api/catalog.v1/instructors";
    public final static String SessionsBaseUrl = "https://api.coursera.org/api/catalog.v1/sessions";

    public final static String Categories_Fields_Id = "id";
    public final static String Categories_Fields_Name = "name";
    public final static String Categories_Fields_ShortName = "shortName";
    public final static String Categories_Fields_Description = "description";

    public final static String Instructors_Fields_id = "id";
    public final static String Instructors_Fields_photo = "photo";
    public final static String Instructors_Fields_photo150 = "photo150";
    public final static String Instructors_Fields_bio = "bio";
    public final static String Instructors_Fields_prefixName = "prefixName";
    public final static String Instructors_Fields_firstName = "firstName";
    public final static String Instructors_Fields_middleName = "middleName";
    public final static String Instructors_Fields_lastName = "lastName";
    public final static String Instructors_Fields_suffixName = "suffixName";
    public final static String Instructors_Fields_fullName = "fullName";
    public final static String Instructors_Fields_title = "title";
    public final static String Instructors_Fields_department = "department";
    public final static String Instructors_Fields_website = "website";
    public final static String Instructors_Fields_websiteTwitter = "websiteTwitter";
    public final static String Instructors_Fields_websiteFacebook = "websiteFacebook";
    public final static String Instructors_Fields_websiteLinkedin = "websiteLinkedin";
    public final static String Instructors_Fields_websiteGplus = "websiteGplus";
    public final static String Instructors_Fields_shortName = "shortName";

    public final static String Universities_Fields_id = "id";
    public final static String Universities_Fields_name = "name";
    public final static String Universities_Fields_shortName = "shortName";
    public final static String Universities_Fields_description = "description";
    public final static String Universities_Fields_banner = "banner";
    public final static String Universities_Fields_homeLink = "homeLink";
    public final static String Universities_Fields_location = "location";
    public final static String Universities_Fields_locationCity = "locationCity";
    public final static String Universities_Fields_locationState = "locationState";
    public final static String Universities_Fields_locationCountry = "locationCountry";
    public final static String Universities_Fields_locationLat = "locationLat";
    public final static String Universities_Fields_locationLng = "locationLng";
    public final static String Universities_Fields_classLogo = "classLogo";
    public final static String Universities_Fields_website = "website";
    public final static String Universities_Fields_websiteTwitter = "websiteTwitter";
    public final static String Universities_Fields_websiteFacebook = "websiteFacebook";
    public final static String Universities_Fields_websiteYoutube = "websiteYoutube";
    public final static String Universities_Fields_logo = "logo";
    public final static String Universities_Fields_squareLogo = "squareLogo";
    public final static String Universities_Fields_landingPageBanner = "landingPageBanner";

    public final static String Courses_Fields_id = "id";
    public final static String Courses_Fields_shortName = "shortName";
    public final static String Courses_Fields_name = "name";
    public final static String Courses_Fields_language = "language";
    public final static String Courses_Fields_largeIcon = "largeIcon";
    public final static String Courses_Fields_photo = "photo";
    public final static String Courses_Fields_previewLink = "previewLink";
    public final static String Courses_Fields_shortDescription = "shortDescription";
    public final static String Courses_Fields_smallIcon = "smallIcon";
    public final static String Courses_Fields_smallIconHover = "smallIconHover";
    public final static String Courses_Fields_subtitleLanguagesCsv = "subtitleLanguagesCsv";
    public final static String Courses_Fields_isTranslate = "isTranslate";
    public final static String Courses_Fields_universityLogo = "universityLogo";
    public final static String Courses_Fields_universityLogoSt = "universityLogoSt";
    public final static String Courses_Fields_video = "video";
    public final static String Courses_Fields_videoId = "videoId";
    public final static String Courses_Fields_aboutTheCourse = "aboutTheCourse";
    public final static String Courses_Fields_targetAudience = "targetAudience";
    public final static String Courses_Fields_faq = "faq";
    public final static String Courses_Fields_courseSyllabus = "courseSyllabus";
    public final static String Courses_Fields_courseFormat = "courseFormat";
    public final static String Courses_Fields_suggestedReadings = "suggestedReadings";
    public final static String Courses_Fields_instructor = "instructor";
    public final static String Courses_Fields_estimatedClassWorkload = "estimatedClassWorkload";
    public final static String Courses_Fields_aboutTheInstructor = "aboutTheInstructor";
    public final static String Courses_Fields_recommendedBackground = "recommendedBackground";

    public final static String Sessions_Fields_id = "id";
    public final static String Sessions_Fields_courseId = "courseId";
    public final static String Sessions_Fields_homeLink = "homeLink";
    public final static String Sessions_Fields_status = "status";
    public final static String Sessions_Fields_active = "active";
    public final static String Sessions_Fields_durationString = "durationString";
    public final static String Sessions_Fields_startDay = "startDay";
    public final static String Sessions_Fields_startMonth = "startMonth";
    public final static String Sessions_Fields_startYear = "startYear";
    public final static String Sessions_Fields_name = "name";
    public final static String Sessions_Fields_signatureTrackCloseTime = "signatureTrackCloseTime";
    public final static String Sessions_Fields_signatureTrackOpenTime = "signatureTrackOpenTime";
    public final static String Sessions_Fields_signatureTrackPrice = "signatureTrackPrice";
    public final static String Sessions_Fields_signatureTrackRegularPrice = "signatureTrackRegularPrice";
    public final static String Sessions_Fields_eligibleForCertificates = "eligibleForCertificates";
    public final static String Sessions_Fields_eligibleForSignatureTrack = "eligibleForSignatureTrack";
    public final static String Sessions_Fields_certificateDescription = "certificateDescription";
    public final static String Sessions_Fields_certificatesReady = "certificatesReady";

    public final static String IncludesCourses = "courses";
    public final static String IncludesInstructors = "instructors";
    public final static String IncludesUniversities = "universities";
    public final static String IncludesSessions = "sessions";

    public static String GetQueryUrl(String baseUrl, ArrayList<String> Fields, ArrayList<String> Includes, ArrayList<String> values) {

        String url = baseUrl;

        if ((Fields != null && Fields.size() > 0) || (Includes != null && Includes.size() > 0) || (values != null && values.size() > 0)) {

            url += "?";

            int index = 0;
            if (Fields != null) {
                for (String field : Fields) {
                    if (index == 0) {
                        url += (HttpConfig.Fields + field);
                    } else {
                        url += ("," + field);
                    }
                    index++;
                }
            }

            if (Includes != null) {
                index = 0;
                for (String include : Includes) {

                    if (index == 0) {
                        if (Fields != null && Fields.size() > 0) {
                            url += "&";
                        }
                        url += (HttpConfig.Includes + include);
                    } else {
                        url += ("," + include);
                    }
                    index++;
                }
            }

            if (values != null) {
                index = 0;
                for (String v : values) {
                    if (index == 0) {
                        if ((Fields != null && Fields.size() > 0) || (Includes != null && Includes.size() > 0)) {
                            url += "&";
                        }
                        url += (Query + v);
                    } else {
                        url += ("+" + v);
                    }
                }
            }
        }
        return url;
    }

    public static String GetUrl(String baseUrl, ArrayList<String> Fields, ArrayList<String> Includes, ArrayList<String> Ids) {
        String url = baseUrl;
        if ((Fields != null && Fields.size() > 0) || (Includes != null && Includes.size() > 0) || (Ids != null && Ids.size() > 0)) {
            url += "?";
            int index = 0;
            if (Fields != null) {
                for (String field : Fields) {
                    if (index == 0) {
                        url += (HttpConfig.Fields + field);
                    } else {
                        url += ("," + field);
                    }
                    index++;
                }
            }

            if (Includes != null) {
                index = 0;
                for (String include : Includes) {

                    if (index == 0) {
                        if (Fields != null && Fields.size() > 0) {
                            url += "&";
                        }
                        url += (HttpConfig.Includes + include);
                    } else {
                        url += ("," + include);
                    }
                    index++;
                }
            }

            if (Ids != null) {
                index = 0;
                for (String id : Ids) {

                    if (index == 0) {
                        if ((Fields != null && Fields.size() > 0) || (Includes != null && Includes.size() > 0)) {
                            url += "&";
                        }
                        url += (HttpConfig.IDS + id);
                    } else {
                        url += ("," + id);
                    }
                    index++;
                }
            }


        }
        return url;
    }


}
