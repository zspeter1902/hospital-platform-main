package cn.common;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.common
 * @Description: 启动类
 * @date 2021-12-01
 */
@SpringBootApplication(scanBasePackages = {
		"pro.skywalking",
		"cn.common"
})
@MapperScan({"cn.common.repository.repository"})
@EnableScheduling
@EnableAspectJAutoProxy(exposeProxy = true)
@Configuration
@EnableTransactionManagement
@Slf4j
public class AppWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppWebApplication.class, args);
	}

}
