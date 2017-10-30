package myservlet;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


public class MainServlet extends HttpServlet {
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("image/png");
        try (OutputStream outputStream = httpServletResponse.getOutputStream()) {
            ImageIO.write(ImageCreater.getBufferedImage(), "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
