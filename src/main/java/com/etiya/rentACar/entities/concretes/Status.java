package com.etiya.rentACar.entities.concretes;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusId")
    private int statusId;


    @Column(name = "statusName")
    private String statusName;

    /*@OneToOne
    private Maintenance maintenance;
*/

}
