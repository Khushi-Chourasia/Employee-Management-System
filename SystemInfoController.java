package codingtechniques;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system-info")
public class SystemInfoController {

    @GetMapping
    public String getSystemInfo() {
        Runtime runtime = Runtime.getRuntime();

        // Fetch system details
        int processors = runtime.availableProcessors();
        long totalMemoryMB = runtime.totalMemory() / (1024 * 1024);
        long freeMemoryMB = runtime.freeMemory() / (1024 * 1024);

        // Open the Employee Management System URL
        String url = "http://localhost:8080/employees";
        openWebpage(url);

        // Create objects to check memory usage
        int objectCount = 0;
        for (int i = 0; i < 10000; i++) {
            new String("Test Object " + i);
            objectCount++;
        }

        System.gc(); // Trigger garbage collection
        long freeMemoryAfterGC = runtime.freeMemory() / (1024 * 1024);

        // Return system information as response
        return "<h2>System Information</h2>"
                + "<p>Available Processors: " + processors + "</p>"
                + "<p>Total Memory (MB): " + totalMemoryMB + "</p>"
                + "<p>Free Memory Before (MB): " + freeMemoryMB + "</p>"
                + "<p>Objects Created: " + objectCount + "</p>"
                + "<p>Free Memory After GC (MB): " + freeMemoryAfterGC + "</p>"
                + "<p><a href='" + url + "' target='_blank'>Open EMS</a></p>";
    }

    public void openWebpage(String url) {
        try {
            if (Desktop.isDesktopSupported() && !java.awt.GraphicsEnvironment.isHeadless()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Desktop operations are not supported in this environment.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
