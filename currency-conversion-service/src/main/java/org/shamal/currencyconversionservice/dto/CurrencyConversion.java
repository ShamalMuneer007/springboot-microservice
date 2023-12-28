package org.shamal.currencyconversionservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversion {
    private long id;
    private String from;
    private String to;
    private Double quantity;
    private Double conversionMultiple;
    private Double totalCalculatedAmount;
    private String environment;

}
