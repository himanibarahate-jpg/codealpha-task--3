import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ChatBot extends JFrame {

    JTextArea chatArea;
    JTextField textField;
    JButton button;

    HashMap<String, String> faq;

    public ChatBot() {

        setTitle("AI Chatbot");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);

        textField = new JTextField();
        button = new JButton("Send");

        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(textField, BorderLayout.CENTER);
        bottom.add(button, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

        // FAQ / Rule-based data
        faq = new HashMap<>();
        faq.put("hello", "Hi! How can I help you?");
        faq.put("hi", "Hello!");
        faq.put("how are you", "I am fine, thank you!");
        faq.put("what is java", "Java is a programming language.");
        faq.put("bye", "Goodbye! Have a nice day.");
        faq.put("help", "I can answer basic questions.");

        // Button action
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Enter key support
        textField.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    private void sendMessage() {

        String userInput = textField.getText().toLowerCase().trim();

        if (userInput.isEmpty()) return;

        chatArea.append("You: " + userInput + "\n");

        String response = getResponse(userInput);

        chatArea.append("Bot: " + response + "\n\n");

        textField.setText("");
    }

    private String getResponse(String input) {

        input = input.replaceAll("[^a-zA-Z ]", "");

        for (String key : faq.keySet()) {
            if (input.contains(key)) {
                return faq.get(key);
            }
        }

        return "Sorry, I don't understand that.";
    }

    public static void main(String[] args) {
        new ChatBot();
    }
}