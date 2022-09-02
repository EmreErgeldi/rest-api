package emre.restApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    private String id;

    private String userName;

    private String last_Name;

    private Date createDate = new Date();
}
