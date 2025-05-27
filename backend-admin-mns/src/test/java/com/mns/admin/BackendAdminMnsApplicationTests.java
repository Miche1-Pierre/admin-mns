package com.mns.admin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@SpringBootTest
@EnableAutoConfiguration(exclude = { MailSenderAutoConfiguration.class })
public class BackendAdminMnsApplicationTests {

	@Test
	void contextLoads() {
	}
}
