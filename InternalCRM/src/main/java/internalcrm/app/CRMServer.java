package internalcrm.app;

import internalcrm.model.InternalCRMImpl;
import internalcrm.thrift.InternalCRMService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

public class CRMServer {

    private static InternalCRMImpl impl;
    private static InternalCRMService.Processor processor;
    private int port;

    public CRMServer() {
        port = 9000;
    }

    private void startServer(InternalCRMService.Processor processor) {
        try {
            serverTransport = new TServerSocket(port);
            tServer = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            System.out.println("Starting internalCRM server on "+port+"...");
            tServer.serve();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
