package fungsi;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import org.json.JSONObject;

public class webSocket {

    private Socket socket;
    private String initialMessage; // Store the message
    private String eventdituju;
    private JSONObject jsonData; // Store JSON data
    private String eventTarget;

    public webSocket(String uri, String pesanditangkap, String eventditangkap) {

        this.initialMessage = pesanditangkap; // Assign it to a class variable
        this.eventdituju = eventditangkap;

        try {
            // Connect to the Socket.IO server
            socket = IO.socket(uri);

            // Define event listeners
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("Connected to IO server! Running event : " + eventdituju);
                    JSONObject data = new JSONObject();
                    try {
                        data.put("NoRawat", initialMessage);
                    } catch (Exception e) {

                    }
                    // Emit the event with the JSON data
                    socket.emit(eventdituju, data);
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("Disconnected from server");
                }
            });

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public webSocket(String uri, JSONObject jsonData, String eventTarget) {
        this.jsonData = jsonData; // Assign JSON data to a class variable
        this.eventTarget = eventTarget;

        try {
            // Connect to the Socket.IO server
            socket = IO.socket(uri);

            // Define event listeners
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("Connected to IO server! Trigger Event : " + eventTarget);
                    // Emit the event with the JSON data
                    socket.emit(eventTarget, jsonData);
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("Disconnected from server");
                }
            });

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        socket.connect();
    }
}
