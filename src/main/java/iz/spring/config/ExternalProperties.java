package iz.spring.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExternalProperties {
	private static final Logger logger = LoggerFactory.getLogger(ExternalProperties.class);

	private static final String PROP_FILE_NAME = "iz-app.properties";
	private static final Properties p;
	static {
		p = new Properties();
		ExternalProperties.class.getClassLoader();
		try (final InputStream is = ClassLoader.getSystemResourceAsStream(PROP_FILE_NAME)) {
			if (is != null) {
				p.load(is);
				logger.info("{} was loaded.", PROP_FILE_NAME);
			} else {
				logger.info("{} was not found.", PROP_FILE_NAME);
			}
		} catch (IOException e) {
			logger.warn("Failed to load " + PROP_FILE_NAME + "!", e);
		}
	}

	public static Class<?> getConfigClass() {
		final String configClassName = p.getProperty("config");
		if (StringUtils.isEmpty(configClassName)) {
			return null;
		}
		try {
			return Class.forName(configClassName);
		} catch (ClassNotFoundException e) {
			logger.warn("{} was not found!", configClassName);
			return null;
		}
	}
}
