package com.example.bibliotek.Book;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum BookStatus {
//    NOT_IN_STOCK,
//    IN_STOCK,
//    WHACKED,
//    ONGOING_DELIVERY,
//    RESERVED

    NOT_IN_STOCK("NOT_IN_STOCK"),
    IN_STOCK("IN_STOCK"),
    RESERVED("RESERVED"),
    WHACKED("WHACKED"),
    ONGOING_DELIVERY("ONGOING_DELIVERY"),
    MISSING("MISSING"),
    LATE("LATE"),
    ON_LOAN("ON_LOAN");

//    INTE_I_LAGER("INTE_I_LAGER"),
//    I_LAGER("IN_STOCK"),
//    RESERVERAD("RESERVERAD"),
//    WACKAD("WACKAD"),
//    PÅGÅENDE_LEVERANS("PÅGÅENDE_LEVERANS"),
//    FÖRSVUNNEN("FÖRSVUNNEN"),
//    SEN("SEN"),
//    PÅ_LÅN("PÅ_LÅN");

    private final String status;

    BookStatus(String status) {
        this.status = status;
    }

    @JsonCreator
    public static BookStatus getStatus(String value) {

        for (BookStatus s : BookStatus.values()) {

            if (s.getStatus().equals(value)) {
                return s;
            }
        }
        return null;
    }
}
