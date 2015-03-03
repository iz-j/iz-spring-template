package iz.spring;

import iz.spring.config.WebAppInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.component.AbstractLifeCycle.AbstractLifeCycleListener;
import org.eclipse.jetty.util.component.LifeCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViaJetty {
	private static final Logger logger = LoggerFactory.getLogger(ViaJetty.class);

	public static void main(String[] args) {
		logger.info("Start via Jetty!");
	}

	private final Server server;

	public ViaJetty() {
		server = new Server(8888);
	}

	private class JettyStartingListener extends AbstractLifeCycleListener {
		private final ServletContext sc;

		public JettyStartingListener(ServletContext sc) {
			this.sc = sc;
		}

		@Override
		public void lifeCycleStarting(LifeCycle event) {
			try {
				// new WebAppSecurityInitializer().onStartup(sc);
				new WebAppInitializer().onStartup(sc);
			} catch (ServletException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void start() {
		logger.info("Start server.");
		try {
			final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
			context.setContextPath("/");
			context.getServletContext().setExtendedListenerTypes(true);
			context.addLifeCycleListener(new JettyStartingListener(context.getServletContext()));
			server.setHandler(context);
			server.start();
		} catch (Exception e) {
			logger.error("Failed to start server!", e);
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		logger.info("Stop server.");
		try {
			server.stop();
			server.join();
		} catch (Exception e) {
			logger.warn("Failed to stop server!", e);
		}
	}
}
