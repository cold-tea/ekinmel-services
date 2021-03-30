package com.sandesh.ekinmelservices.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "USER_ACTION_AUDIT")
public class UserActionAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String action;
    private String actionStatus;
    private Integer seqFailureCount;
    private Date performedDate;
}
