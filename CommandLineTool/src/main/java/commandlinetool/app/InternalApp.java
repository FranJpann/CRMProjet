package commandlinetool.app;

import java.io.IOException;

public class InternalApp {
	public static void main(String[] args) {
        try {
            HandleArgs.handleArgs(args);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
