package com.study.model;

import com.lidroid.xutils.db.annotation.NoAutoIncrement;

/**
 * Created by hugo on 15/10/7.
 *
 * Sessions Base url: https://api.coursera.org/api/catalog.v1/sessions
 *
 * Fields :
 * id: Int - Session Id
 * courseId: Int - Course Id
 * homeLink: Option[String] - Home link for the course.
 * status: Int - Session status.
 * active: Boolean - Whether the session is active.
 * durationString: Option[String] - Approximate (human readable) duration string. Typically of the format ‘XX weeks’.
 * startDay: Option[Int] - Optional start day.
 * startMonth: Option[Int] - Optional start month.
 * startYear: Option[Int] - Optional start year.
 * name: Option[String] - Session iteration name.
 * signatureTrackCloseTime: Option[DateTime] - Signature track information.
 * signatureTrackOpenTime: Option[DateTime] - Signature track information.
 * signatureTrackPrice: Option[Double] - Signature track information.
 * signatureTrackRegularPrice: Option[Float] - Signature track information.
 * eligibleForCertificates: Option[Boolean] - Certificates allowed.
 * eligibleForSignatureTrack: Option[Boolean] - Signature track available.
 * certificateDescription: Option[String] - Description on the course certificate.
 * certificatesReady: Boolean - Whether the certificates have been released.
 *
 * Includes :
 * instructors - The instructors for a session.
 * courses - The linked course for this session.
 *
 */
public class SessionsModel {

    @NoAutoIncrement
    private int id;
    private int courseId;
    private String homeLink;
    private int status;
    private boolean active;
    private String durationString;
    private int startDay;

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getHomeLink() {
        return homeLink;
    }

    public void setHomeLink(String homeLink) {
        this.homeLink = homeLink;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDurationString() {
        return durationString;
    }

    public void setDurationString(String durationString) {
        this.durationString = durationString;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignatureTrackCloseTime() {
        return signatureTrackCloseTime;
    }

    public void setSignatureTrackCloseTime(String signatureTrackCloseTime) {
        this.signatureTrackCloseTime = signatureTrackCloseTime;
    }

    public String getSignatureTrackOpenTime() {
        return signatureTrackOpenTime;
    }

    public void setSignatureTrackOpenTime(String signatureTrackOpenTime) {
        this.signatureTrackOpenTime = signatureTrackOpenTime;
    }

    public double getSignatureTrackPrice() {
        return signatureTrackPrice;
    }

    public void setSignatureTrackPrice(double signatureTrackPrice) {
        this.signatureTrackPrice = signatureTrackPrice;
    }

    public float getSignatureTrackRegularPrice() {
        return signatureTrackRegularPrice;
    }

    public void setSignatureTrackRegularPrice(float signatureTrackRegularPrice) {
        this.signatureTrackRegularPrice = signatureTrackRegularPrice;
    }

    public boolean isEligibleForCertificates() {
        return eligibleForCertificates;
    }

    public void setEligibleForCertificates(boolean eligibleForCertificates) {
        this.eligibleForCertificates = eligibleForCertificates;
    }

    public boolean isEligibleForSignatureTrack() {
        return eligibleForSignatureTrack;
    }

    public void setEligibleForSignatureTrack(boolean eligibleForSignatureTrack) {
        this.eligibleForSignatureTrack = eligibleForSignatureTrack;
    }

    public String getCertificateDescription() {
        return certificateDescription;
    }

    public void setCertificateDescription(String certificateDescription) {
        this.certificateDescription = certificateDescription;
    }

    public boolean isCertificatesReady() {
        return certificatesReady;
    }

    public void setCertificatesReady(boolean certificatesReady) {
        this.certificatesReady = certificatesReady;
    }

    private int startMonth;
    private int startYear;
    private String name;
    private String signatureTrackCloseTime;
    private String signatureTrackOpenTime;
    private double signatureTrackPrice;
    private float signatureTrackRegularPrice;
    private boolean eligibleForCertificates;
    private boolean eligibleForSignatureTrack;
    private String certificateDescription;
    private boolean certificatesReady;

}
