package com.googlepay.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AccountInfo {
    private String accountHolderName;
    private String accountType;
    private String accountStatus;
    private String bankName;
    private String phoneNumber;
    private String panCard;
    private Float balance;
}
