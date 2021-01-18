
import com.sas.svcs.security.authentication.client.AuthenticationClient;
import com.sas.svcs.security.authentication.client.AuthenticationClientHolder;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

/**
* A simple java program that simulates a SAS rich client application who initiates a reconnect scenario.
* This app attempts to log on using connection information supplied on the command line, then attempts
* to initiate a reconnect with the supplied url.
*
* @since 9.4
* @version 9.4
*/
public class Hello {
	/**
	 * Runs the sample program.
	 * @param args
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static void main(String[] args) throws URISyntaxException, IOException {
		//if (args.length != 3) {
		//	System.out.println("Usage: java com.sas.test.ReconnectClient <target url> <user> <password>");
		//	System.out.println("\texample: java -cp XXXX.jar Hello http://rdcesx00000.race.sas.com:7980/SASStoredProcess/do sasdemo Go4thsas");
		//	return;
		//}
		
		String[] params = new String[] {"http://rdcesx00000.race.sas.com:7980/SASStoredProcess/do", "sasdemo", "Go4thsas"};
		
		//URL targetUrl = new URL(args[0]);
		URL targetUrl = new URL(params[0]);
		
		// This is bad. In shipping code, you should use UrlGeneratorInterface to generate a URL for the Logon Manager
		URL casUrl = new URL(targetUrl.getProtocol(), targetUrl.getHost(), targetUrl.getPort(), "/SASLogon");
		// Construct an AuthenticationClient and logon
		AuthenticationClient client = new AuthenticationClient(casUrl.toString());
		client.logon(params[1], params[2]);
		AuthenticationClientHolder.set(client);
		
		// Acquire a ticket for our target URL
		final String ticket = client.acquireTicket(targetUrl.toString());
		// Construct a URI that points at the Logon Manager and passes the direct_authentication_ticket as well as
		// the target URL. The Logon Manager will handle the redirects.
		URI reconnectUri = new URI(casUrl + "?direct_authentication_ticket=" + ticket + "&service=" + URLEncoder.encode(targetUrl.toString(), "UTF-8"));
		
		// Open a browser window with our reconnect URL
		Desktop.getDesktop().browse(reconnectUri);
		
		// This pauses the program until input is received. If you input anything before the reconnect is complete in
		// browser, it will not work. Additionally, any time after the logout below runs, the browser session will no
		// longer be valid.
		System.out.println("Press return to exit. Exiting before the page loads will prevent reconnection");
		System.in.read();
		client.logout();
	}
}
