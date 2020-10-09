package github.com.gracefulshutdown;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    @RequestMapping(value = "/{s}", method = RequestMethod.GET)
    public String handle(@RequestHeader(value = "request-id", defaultValue = "no-request-id") String requestId, @PathVariable int s) throws InterruptedException {

        if(requestId.equals("no-request-id")) {
            requestId = UUID.randomUUID().toString();
        }

        int loop = s / 2;
        int ticks = 1;
        int tocks = 2;

        for(int i = 1; i <= loop; i++) {
            log.info("{}: Tick {}", requestId, String.valueOf(ticks));
            Thread.sleep(1000);
            log.info("{}: Tock {}", requestId, String.valueOf(tocks));
            Thread.sleep(1000);

            ticks = (2 * i) + 1;
            tocks = (2 * i) + 2;
        }

        return "Process finished";
    }
}
