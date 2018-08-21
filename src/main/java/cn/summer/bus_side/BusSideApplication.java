package cn.summer.bus_side;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.summer.bus_side.dao")
@SpringBootApplication
public class BusSideApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusSideApplication.class, args);
    }
}
