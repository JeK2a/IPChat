import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;

class ChatServer extends JFrame {
    private static JTextArea textArea;

    private ChatServer(String s) {
        super(s);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textArea = new JTextArea(20, 30);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setPreferredSize(new Dimension(450, 450));
        add(scrollPane);

        setVisible(true);
        pack();

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) { }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }
        });
    }

    static void enterMessage(String t) {
        System.out.println(t);
        textArea.append(t + "\n");
    }

    public static void main(String[] args) {
        new Settings();
        new ChatServer("IPChatServer V5.1");

        try (ServerSocket serverSocket = new ServerSocket(Settings.getPort())) {
            enterMessage("Server starting...");

            while(true) {
                if (SocketThread.clients <= Settings.getSizeMaxClients()) {
                    new Thread(new SocketThread(serverSocket.accept())).start(); // Созлание нового потока на сервере
                } else {
                    enterMessage("Превышено максимальное количество пользователей!");
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}