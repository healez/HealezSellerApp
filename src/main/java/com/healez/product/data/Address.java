package com.healez.product.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
	private String addressNumber;
	private String addressStreetName;
	private String addressCity;
	private String addressPostalCode;
	private String addressState;
	private String addressCountryCode;
}
