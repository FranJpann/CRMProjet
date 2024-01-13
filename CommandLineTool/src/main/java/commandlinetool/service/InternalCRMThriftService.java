package commandlinetool.service;

import internalcrm.thrift.InternalCRMService;
import internalcrm.thrift.InternalLeadDTO;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class InternalCRMThriftService {

    private final String internalHost = "localhost";
    private final int internalPort = 9090;

    public void addLead(InternalLeadDTO lead) {
        try {
            TTransport transport;
            transport = new TSocket(internalHost, internalPort);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            InternalCRMService.Client client = new InternalCRMService.Client(protocol);

            client.addLead(lead);

            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
