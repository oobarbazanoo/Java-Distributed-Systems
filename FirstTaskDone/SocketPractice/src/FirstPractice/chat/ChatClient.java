package FirstPractice.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient
{
    BufferedReader in;
    PrintWriter out;

    JFrame frame = new JFrame("Chatter");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);

    public ChatClient()
    {
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        textField.addActionListener((e) ->
        {
            out.println(textField.getText());
            textField.setText("");
        });
    }

    private String askUserSomeInfo(String message, String title)
    {
        return JOptionPane.showInputDialog(frame, message, title, JOptionPane.QUESTION_MESSAGE);
    }

    private void run()
            throws IOException
    {
        String serverAddress = askUserSomeInfo("Enter IP Address of the Server:", "Welcome to the Chatter");
        Socket socket = new Socket(serverAddress, 9001);

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while(true)
        {
            String line = in.readLine();
            if(line.startsWith("SUBMITNAME"))
            {
                out.println(askUserSomeInfo("Choose a screen name:", "Screen name selection"));
            }
            else if(line.startsWith("NAMEACCEPTED"))
            {
                textField.setEditable(true);
            }
            else if(line.startsWith("MESSAGE"))
            {
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }

    public static void main(String[] args)
            throws Exception
    {
        ChatClient client = new ChatClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}