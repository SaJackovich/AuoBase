package com.project.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import java.util.Date;

@ToString
@EqualsAndHashCode(of = {"id"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Min(value = 0)
    private double height;

    @Min(value = 0)
    private double width;

    @Min(value = 0)
    private Integer carrying;

    @NotBlank(message = "'From place' cannot be null")
    @Column(name = "`from`")
    private String from;

    @NotBlank(message = "'To place' cannot be null")
    @Column(name = "`to`")
    private String to;

    @CreationTimestamp
    private Date created;

    private boolean processed;

    @NotBlank(message = "Message cannot be null")
    private String message;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
