package gb.shop.shop.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "textInputChanel")
public interface FileGateWay {

    void writeToFile(@Header(FileHeaders.FILENAME) String filename, String data);
}
