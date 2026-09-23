package com.pratikjatale.assistant.ai.dto.request;

import com.pratikjatale.assistant.ai.enums.SenderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageRequest {

    @NotNull(message = "Sender type is required (CUSTOMER or AGENT)")
    private SenderType senderType;

    private String senderName;

    @NotBlank(message = "Message content cannot be blank")
    private String content;
}
