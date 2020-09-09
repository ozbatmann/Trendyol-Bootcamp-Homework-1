package model.bill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Bill {

	private String id;
	private Double amount;
	private Date lastPaymentDate;
}
