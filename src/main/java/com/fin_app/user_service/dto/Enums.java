package com.fin_app.user_service.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Enums {
    public enum AccountType {CURRENT, SAVINGS, BUSINESS}
    public enum BranchCode {BR01, BR02, BR03, BR04,BR05,BR06}
    public enum UserType {USER , ADMIN}
}
