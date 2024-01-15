package internalcrm.app;

import internalcrm.service.InternalCRMImpl;
import internalcrm.thrift.InternalCRMService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class InternalApp {

	/* InternalApp */
	/* Met en place un serveur Thrift */

	public static InternalCRMImpl impl;
	public static InternalCRMService.Processor processor;
	public static int port = 9090;
	public static void main(String[] args) {
		try {
			impl = new InternalCRMImpl();
			processor = new InternalCRMService.Processor(impl);

			new Thread(new Runnable()
			{
				@Override
				public void run() {
					simple(processor);
				}
			}).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void simple(InternalCRMService.Processor processor) {
		try {
			TServerTransport serverTransport = new TServerSocket(port);
			TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

			System.out.println("Starting internalCRM server on "+port+"...");
			server.serve();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
