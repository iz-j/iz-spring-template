package iz.springtest;

import iz.spring.ViaJetty;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JettyTestServer extends ExternalResource {
	private static final Logger logger = LoggerFactory.getLogger(JettyTestServer.class);

	private ViaJetty jetty;

	@Override
	protected void before() {
		logger.info("Start TestServer.");

		jetty = new ViaJetty();
		jetty.start();
	}

	@Override
	protected void after() {
		jetty.stop();
		logger.info("Stop TestServer.");
	}
}
