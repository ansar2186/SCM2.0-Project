package com.scm.helper;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String content;
    @Builder.Default
    private MessageType type=MessageType.BLUE;
}
