package commandlinetool.app;

import commandlinetool.service.VirtualCRMAPIService;

import java.io.IOException;

public class InternalApp {
	public static void main(String[] args) {

        handleArg()
		VirtualCRMAPIService virtualCRMAPIService = new VirtualCRMAPIService();
        try {
            System.out.println(virtualCRMAPIService.findLeadsByDate("2000-01-01", "2050-01-01"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
