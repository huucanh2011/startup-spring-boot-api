package vn.luvina.startup.model;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "order_histories")
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_email")
    private String receiverEmail;

    @Column(name = "receiver_phone_number")
    private String receiverPhone;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "status_id")
    private UUID statusId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "entry_date")
    private Date entryDate;
}
