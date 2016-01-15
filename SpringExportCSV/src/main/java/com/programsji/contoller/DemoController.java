package com.programsji.contoller;

import java.io.BufferedWriter;
import java.io.IOException;
import com.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void demo(HttpServletResponse response) throws IOException {
        List<String> names = new ArrayList<String>();
        names.add("First Name");
        names.add("Second Name");
        names.add("Third Name");
        names.add("Fourth Name");

        BufferedWriter writer = new BufferedWriter(response.getWriter());
        try {
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"mytextfile.csv\"");
            for (String name : names) {
                writer.write(name);
                writer.write(",");
            }
            writer.newLine();
        } catch (IOException ex) {
        } finally {
            writer.flush();
            writer.close();
        }

    }
}
