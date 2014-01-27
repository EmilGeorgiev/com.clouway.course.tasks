package com.clouway.downloadagent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by clouway on 1/24/14.
 */
public class DownloadView extends JFrame {

  private JProgressBar progressBar;
  private final DownloadAgent downloadAgent;

  public JProgressBar getProgressBar() {
    return progressBar;
  }

  public DownloadView(final DownloadAgent downloadAgent) {
    this.downloadAgent = downloadAgent;
    JPanel mainPanem = new JPanel(new FlowLayout());
    add(mainPanem);

    JLabel save = new JLabel("Save in");
    mainPanem.add(save);

    final JTextField filePath = new JTextField();
    filePath.setPreferredSize(new Dimension(200, 30));
    mainPanem.add(filePath);

    JLabel downloadPage = new JLabel("Page address");
    mainPanem.add(downloadPage);

    final JTextField pageAddress = new JTextField();
    pageAddress.setPreferredSize(new Dimension(200, 30));

    mainPanem.add(pageAddress);

    JButton download = new JButton("Download");
    mainPanem.add(download);

    progressBar = new JProgressBar(0, 100);
    progressBar.setValue(0);
    progressBar.setStringPainted(true);
    mainPanem.add(progressBar);

    download.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Runnable a = new Runnable() {
          @Override
          public void run() {
            downloadAgent.downloadFile(filePath.getText(), pageAddress.getText());
          }
        };
        Thread thr = new Thread(a);
        thr.start();
      }
    });

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setSize(450, 200);
    pack();
  }
}
