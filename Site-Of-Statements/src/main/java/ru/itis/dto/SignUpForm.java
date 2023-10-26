package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpForm {
    private String name;
    private String mail;
    private String phoneNumber;
}
