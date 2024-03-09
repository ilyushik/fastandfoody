package org.example.fastandfoodyapp.Mails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailStructure {
    private String subject;
    private String message;
}
