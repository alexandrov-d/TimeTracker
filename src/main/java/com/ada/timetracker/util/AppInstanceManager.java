package com.ada.timetracker.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class AppInstanceManager {

	private final static Logger log = Logger.getLogger("log");
	
    private static AppInstanceListener subListener;

    /** Randomly chosen, but static, high socket number */
    public static final int SINGLE_INSTANCE_NETWORK_SOCKET = 44331;

    /** Must end with newline */
    public static final String SINGLE_INSTANCE_SHARED_KEY = "$$NewInstance$$\n";

    /**
     * Registers this instance of the application.
     *
     * @return true if first instance, false if not.
     */
    public static boolean registerInstance() {
        // returnValueOnError should be true if lenient (allows app to run on network error) or false if strict.
        boolean returnValueOnError = true;
        // try to open network socket
        // if success, listen to socket for new instance message, return true
        // if unable to open, connect to existing and send new instance message, return false
        try {
        	
            @SuppressWarnings("resource")
			ServerSocket socket = new ServerSocket(SINGLE_INSTANCE_NETWORK_SOCKET, 10, InetAddress.getByAddress(new byte[] {127, 0, 0, 1}));
           
            //log.info("Listening for application instances on socket " + SINGLE_INSTANCE_NETWORK_SOCKET);
           // System.out.println("Listening for application instances on socket " + SINGLE_INSTANCE_NETWORK_SOCKET);
            
            Thread instanceListenerThread = new Thread(new Runnable() {
                public void run() {
                    boolean socketClosed = false;
                    while (!socketClosed) {
                        if (socket.isClosed()) {
                            socketClosed = true;
                        } else {
                            try {
                                Socket client = socket.accept();
                                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                                String message = in.readLine();
                                if (SINGLE_INSTANCE_SHARED_KEY.trim().equals(message.trim())) {
                                    log.fine("Shared key matched - new application instance found");
                                    fireNewInstance();
                                }
                                in.close();
                                client.close();
                            } catch (IOException e) {
                                socketClosed = true;
                            }
                        }
                    }
                }
            });
            instanceListenerThread.start();
            // listen
        } catch (UnknownHostException e) {
            log.info(e.getMessage());
            return returnValueOnError;
        } catch (IOException e) {
           // log.info("Port is already taken.  Notifying first instance.");
            try {
                Socket clientSocket = new Socket(InetAddress.getByAddress(new byte[] {127, 0, 0, 1}), SINGLE_INSTANCE_NETWORK_SOCKET);
                OutputStream out = clientSocket.getOutputStream();
                out.write(SINGLE_INSTANCE_SHARED_KEY.getBytes());
                out.close();
                clientSocket.close();
                log.fine("Successfully notified first instance.");
                return false;
            } catch (UnknownHostException e1) {
                log.severe(e.getMessage());
                return returnValueOnError;
            } catch (IOException e1) {
                log.severe("Error connecting to local port for single instance notification");
                log.severe(e1.getMessage());
                return returnValueOnError;
            }

        }
        return true;
    }
    /**
     * Sets callback function 
     * @param listener the AppInstanceListener whose newInstanceCreated method will be executed 
     * if another instance of application launched
     */
    public static void setAppInstanceListener(AppInstanceListener listener) {
        subListener = listener; 
    }

    private static void fireNewInstance() {
    	if (subListener != null) {
		    subListener.newInstanceCreated();
	    }
    }
}



