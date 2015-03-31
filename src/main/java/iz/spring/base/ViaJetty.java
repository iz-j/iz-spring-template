package iz.spring.base;

import iz.spring.base.config.web.WebAppInitializer;
import iz.spring.base.config.web.WebAppSecurityInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle.AbstractLifeCycleListener;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Launch your web application project via Jetty.
 *
 * @author izumi_j
 *
 */
public final class ViaJetty {
	private static final Logger logger = LoggerFactory.getLogger(ViaJetty.class);

	/**
	 * Call this to launch your project.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Start via Jetty!");
		final ViaJetty jetty = new ViaJetty();
		try {
			jetty.start();
			jetty.join();
		} catch (Throwable e) {
			throw e;
		} finally {
			jetty.stop();
		}
	}

	private final Server server;

	public ViaJetty() {
		server = new Server(8888);
	}

	/**
	 * Instead of ServletContainerInitializer.
	 *
	 * @author izumi_j
	 *
	 */
	private static class JettyStartingListener extends AbstractLifeCycleListener {
		private final ServletContext ctx;

		public JettyStartingListener(ServletContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public void lifeCycleStarting(LifeCycle event) {
			try {
				new WebAppSecurityInitializer().onStartup(ctx);
				new WebAppInitializer().onStartup(ctx);
			} catch (ServletException e) {
				logger.error("Failed to starting!", e);
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Start server.
	 */
	public void start() {
		logger.info("Start server.");
		try {
			final WebAppContext webAppCtx = new WebAppContext();
			webAppCtx.setResourceBase("src/main/webapp");
			webAppCtx.setContextPath("/");
			webAppCtx.getServletContext().setExtendedListenerTypes(true);
			webAppCtx.addLifeCycleListener(new JettyStartingListener(webAppCtx.getServletContext()));

			server.setHandler(webAppCtx);
			server.start();
		} catch (Exception e) {
			logger.error("Failed to start server!", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Stop server.
	 */
	public void stop() {
		logger.info("Stop server.");
		try {
			server.stop();
			server.join();
		} catch (Exception e) {
			logger.warn("Failed to stop server!", e);
		}
	}

	/**
	 * Join server.
	 */
	public void join() {
		try {
			server.join();
		} catch (Exception e) {
			logger.warn("Failed to join server!", e);
		}
	}
}
