package com.clouway.downloadagent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by clouway on 1/24/14.
 */
public class DownloadViewListener extends JFrame implements DownloadProgressListener {

  private JProgressBar progressBar;
  private final DownloadAgent downloadAgent;
  private JButton download;

  public DownloadViewListener(final DownloadAgent downloadAgent) {
    this.downloadAgent = downloadAgent;
    JPanel mainPanel = new JPanel(new FlowLayout());
    add(mainPanel);

    JLabel save = new JLabel("Save in");
    mainPanel.add(save);

    final JTextField filePath = new JTextField();
    filePath.setPreferredSize(new Dimension(200, 30));
    mainPanel.add(filePath);

    JLabel downloadPage = new JLabel("Page address");
    mainPanel.add(downloadPage);

    final JTextField pageAddress = new JTextField();
    pageAddress.setPreferredSize(new Dimension(200, 30));

    mainPanel.add(pageAddress);

    download = new JButton("Download");
    mainPanel.add(download);

    progressBar = new JProgressBar(0, 100);
    progressBar.setValue(0);
    progressBar.setStringPainted(true);
    mainPanel.add(progressBar);

    download.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Thread thread = new Thread() {
          public void run () {
            download.setEnabled(false);
            downloadAgent.downloadFile(filePath.getText(), pageAddress.getText());
            download.setEnabled(true);
          }
        };
        thread.start();
      }
    });

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setSize(450, 200);
    pack();
  }

  @Override
  public void onProgressWasUpdate(int savedBytes, long sizeDownloadFile) {
    progressBar.setMaximum((int) sizeDownloadFile);
    progressBar.setValue(savedBytes);
  }
}
