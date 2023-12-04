package virtualcrm.app;

import org.springframework.boot.SpringApplication;
import virtualcrm.thrift.InternalCRMService;
import virtualcrm.thrift.InternalLeadDTO;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class InternalApp {
	public static void main(String[] args) {
		SpringApplication.run(InternalApp.class, args);
	}
}
