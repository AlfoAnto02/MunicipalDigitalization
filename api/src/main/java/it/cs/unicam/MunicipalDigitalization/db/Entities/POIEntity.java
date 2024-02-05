package it.cs.unicam.MunicipalDigitalization.db.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "POITable")
public class POIEntity {
    /**
     * Id of the POI, is the PrimaryKey
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Name of the POI
     */
    @Column(nullable = false)
    private String name;

    /**
     * Status of the POI. It can be Pending or Not Pending
     */
    @Enumerated(EnumType.STRING)
    private POIStatus status;

    /**
     * Author of the POI
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private UserEntity author;

    //Metti la data

    /**
     * Latitude of the POI
     */
    @Column(nullable = false)
    private Number latitude;

    /**
     * Longitude of the POI
     */
    @Column(nullable = false)
    private Number longitude;


}
