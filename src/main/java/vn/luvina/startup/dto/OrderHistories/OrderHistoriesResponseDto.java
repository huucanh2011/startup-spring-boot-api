package vn.luvina.startup.dto.OrderHistories;

import java.sql.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class OrderHistoriesResponseDto {
    private UUID id;
    private String receiverName;
    private String receiverEmail;
    private String receiverPhone;
    private String receiverAddress;
    private Integer totalAmount;
    private UUID statusId;
    private UUID userId;
    private Date entryDate;
}
