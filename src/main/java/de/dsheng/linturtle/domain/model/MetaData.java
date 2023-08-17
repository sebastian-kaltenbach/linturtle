package de.dsheng.linturtle.domain.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetaData {
    
    private String name;
    private Date timeStamp;
    private int incidents;
}
