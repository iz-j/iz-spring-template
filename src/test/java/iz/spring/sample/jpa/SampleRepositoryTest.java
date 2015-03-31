package iz.spring.sample.jpa;

import static org.junit.Assert.*;
import iz.spring.base.config.AppConfig;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class })
public class SampleRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(SampleRepositoryTest.class);

	@Autowired
	private SampleRepository repo;

	@Test
	public void test_ins_sel_upd_del() {
		Sample dto = new Sample();
		dto.str = "hoge";
		dto.bd = new BigDecimal("1.23");
		repo.saveAndFlush(dto);

		final Long id = dto.id;
		logger.debug("New id = {}", id);

		dto = repo.findOne(id);
		logger.debug("Saved dto = {}", dto);

		dto.str = "fuga";
		dto.bd = new BigDecimal("4.56");
		repo.saveAndFlush(dto);

		dto = repo.findOne(id);
		logger.debug("Updated dto = {}", dto);

		repo.delete(id);
		repo.flush();

		assertFalse(repo.exists(id));
	}

}
