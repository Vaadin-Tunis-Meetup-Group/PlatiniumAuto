package org.mendole.platiniumauto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mendole.platiniumauto.PlatiniumAutoApplication;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PlatiniumAutoApplication.class)
@WebAppConfiguration
public class PlatiniumAutoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
