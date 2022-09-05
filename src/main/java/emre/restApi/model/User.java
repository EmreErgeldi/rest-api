package emre.restApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@Data
public class User {
    @Id
    private String id;
    private String userName;
    private String last_Name;

    private double oid;
    private String meter_oid;
    private String equipment_id;
    private String equipment_type;
    private double ta64;
    private String meter_ts;
    private String received_ts;
    private String meter_status;
    private String record_oid;
    private double status;
    private String last_update;

    private Date createDate = new Date();
}