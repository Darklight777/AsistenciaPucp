package pe.edu.pucp.grupo02.asistenciapucp.data.api.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseInRO;

@JsonRootName("generateTokenInRO")
public class GenerateTokenInRO extends BaseInRO {

    private String courseName;
    private String courseSchedule;
    private String courseTime;

    @JsonCreator
    public GenerateTokenInRO(@JsonProperty("applicationName") String applicationName,
                             @JsonProperty("courseName") String courseName,
                             @JsonProperty("courseSchedule") String courseSchedule,
                             @JsonProperty("courseTime") String courseTime){
        super(applicationName);
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
