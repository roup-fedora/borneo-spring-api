package borneo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SensorController {

    // 1. System Status (Shows off Java & Docker versions)
    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        return Map.of(
                "appName", "Borneo Backend",
                "status", "ONLINE",
                "javaVersion", System.getProperty("java.version"),
                "timestamp", LocalDateTime.now().toString()
        );
    }

    // 2. Fake IoT Sensor Data
    @GetMapping("/sensors")
    public List<Map<String, Object>> getSensors() {
        return List.of(
                Map.of("id", 1, "name", "Temperature", "value", 24.5, "unit", "C"),
                Map.of("id", 2, "name", "Humidity", "value", 60.2, "unit", "%"),
                Map.of("id", 3, "name", "Motion", "value", false, "unit", "boolean")
        );
    }
}