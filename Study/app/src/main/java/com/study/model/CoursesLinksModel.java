package com.study.model;

import java.util.List;

/**
 * Created by hugo on 15/10/13.
 */
public class CoursesLinksModel {

    private List<Integer> universities;
    private List<Integer> sessions;
    private List<Integer> instructors;

    public List<Integer> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Integer> instructors) {
        this.instructors = instructors;
    }

    public List<Integer> getUniversities() {
        return universities;
    }

    public void setUniversities(List<Integer> universities) {
        this.universities = universities;
    }

    public List<Integer> getSessions() {
        return sessions;
    }

    public void setSessions(List<Integer> sessions) {
        this.sessions = sessions;
    }
}
