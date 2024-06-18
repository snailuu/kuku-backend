package io.snailuu.boot;

import io.snailuu.boot.util.IpUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

/**
 * 启动类
 *
 * @author snailuu
 * @date 2022-3-16
 */
@EnableAsync
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    private static final String BACKSLASH = "/";

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootApplication.class, args);
        System.out.println("  _____ _______       _____ _______    _____ _    _  _____ _____ ______  _____ _____ \n" +
                " / ____|__   __|/\\   |  __ \\__   __|  / ____| |  | |/ ____/ ____|  ____|/ ____/ ____|\n" +
                "| (___    | |  /  \\  | |__) | | |    | (___ | |  | | |   | |    | |__  | (___| (___  \n" +
                " \\___ \\   | | / /\\ \\ |  _  /  | |     \\___ \\| |  | | |   | |    |  __|  \\___ \\\\___ \\ \n" +
                " ____) |  | |/ ____ \\| | \\ \\  | |     ____) | |__| | |___| |____| |____ ____) |___) |\n" +
                "|_____/   |_/_/    \\_\\_|  \\_\\ |_|    |_____/ \\____/ \\_____\\_____|______|_____/_____/ \n");
        // 打印项目信息
        printlnProjectInfo(context);
    }

    /**
     * 打印项目信息
     *
     * @param context
     */
    private static void printlnProjectInfo(ConfigurableApplicationContext context) {
        try {
            ConfigurableEnvironment environment = context.getEnvironment();
            String serverPort = environment.getProperty("server.port");
            String contextPath = environment.getProperty("server.servlet.context-path");
            if (!BACKSLASH.equals(contextPath)) {
                contextPath = contextPath + BACKSLASH;
            }
            String localhostDocUrl = "\nhttp://localhost:" + serverPort + contextPath + "doc.html";
            System.out.println(localhostDocUrl);
            String localhostSwaggerUrl = "http://localhost:" + serverPort + contextPath + "swagger-ui/index.html";
            System.out.println(localhostSwaggerUrl);
            List<String> ipV4List = IpUtil.getLocalhostIpList();
            if (CollectionUtils.isNotEmpty(ipV4List)) {
                for (String ip : ipV4List) {
                    String ipUrl = "http://" + ip + ":" + serverPort + contextPath + "doc.html";
                    System.out.println(ipUrl);
                }
            }
            System.out.println("\n账号：admin");
            System.out.println("密码：123456");
            System.out.println("swagger密码：e10adc3949ba59abbe56e057f20f883e\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
