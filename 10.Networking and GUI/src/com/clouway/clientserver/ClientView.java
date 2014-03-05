package com.clouway.clientserver;

import javax.swing.*;
import java.awt.*;

/**
 * Created by clouway on 2/12/14.
 */
public class ClientView extends JFrame implements ClientMessageListener {

  private JTextArea textArea;

  public ClientView() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setSize(200, 200);

    textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);
    add(scrollPane, BorderLayout.CENTER);

    setVisible(true);
  }

  @Override
  public void onNewMessageReceived(String message) {
    textArea.append(message + "\n");
  }
}
