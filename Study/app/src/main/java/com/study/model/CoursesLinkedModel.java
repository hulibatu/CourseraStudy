package com.study.model;

import java.util.List;

/**
 * Created by hugo on 15/10/13.
 */
public class CoursesLinkedModel {

    private List<UniversitiesModel> universities;
    private List<SessionsModel> sessions;

    public List<InstructorsModel> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<InstructorsModel> instructors) {
        this.instructors = instructors;
    }

    private List<InstructorsModel> instructors;

    public List<UniversitiesModel> getUniversities() {
        return universities;
    }

    public void setUniversities(List<UniversitiesModel> universities) {
        this.universities = universities;
    }

    public List<SessionsModel> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionsModel> sessions) {
        this.sessions = sessions;
    }
}
