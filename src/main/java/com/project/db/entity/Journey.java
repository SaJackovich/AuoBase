package com.project.db.entity;

import com.project.db.constant.JourneyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@ToString
@EqualsAndHashCode(of = {"id"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private Date created;

    @Column(name = "`start`")
    private Date start;

    @Column(name = "`end`")
    private Date end;

    @Enumerated(EnumType.STRING)
    private JourneyStatus status;

    @NotBlank(message = "'From place' cannot be null")
    @Column(name = "`from`")
    private String from;

    @NotBlank(message = "'To place' cannot be null")
    @Column(name = "`to`")
    private String to;

    @OneToOne(cascade=CascadeType.REMOVE, optional=true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_id", referencedColumnName = "id")
    private Auto auto;

}
