package pe.edu.pucp.grupo02.asistenciapucp.data.api.out;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseOutRO;

@JsonRootName("teacherTokenOutRO")
public class TeacherTokenOutRO extends BaseOutRO {

    private String courseName;
    private String courseSchedule;
    private String courseTime;

    @JsonCreator
    public TeacherTokenOutRO(@JsonProperty("errorCode") int errorCode,
                             @JsonProperty("message") String message,
                             @JsonProperty("courseName") String courseName,
                             @JsonProperty("courseSchedule") String courseSchedule,
                        @JsonProperty("courseTime") String courseTime) {
        super(errorCode, message);
        this.courseName = courseName;
        this.courseSchedule = courseSchedule;
        this.courseTime = courseTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseSchedule() {
        return courseSchedule;
    }

    public String getCourseTime() {
        return courseTime;
    }
}
