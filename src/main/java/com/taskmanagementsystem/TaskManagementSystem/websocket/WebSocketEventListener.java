package com.taskmanagementsystem.TaskManagementSystem.websocket;


import com.taskmanagementsystem.TaskManagementSystem.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageSendingOperations;
    @EventListener
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent event
    ){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String task = (String) headerAccessor.getSessionAttributes().get("task");
        if (task !=null){
            log.info("task completed {}",task);
            var taskDto = TaskDto.builder()
                    .title("Testing websocket")
                    .description("")
                    .build();
            messageSendingOperations.convertAndSend("/topic/public", taskDto);
        }
    }
}
