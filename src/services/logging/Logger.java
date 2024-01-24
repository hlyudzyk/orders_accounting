package services.logging;

import services.Service;

public interface Logger extends Service {
    void log(String message);
}
