package com.clouway.calculator.framecalculator;

import com.clouway.calculator.actinterface.ActionOnText;
import com.clouway.calculator.actinterface.MalformedText;
import com.clouway.calculator.markerbuttons.MarkerNumbers;
import com.clouway.calculator.markerbuttons.MarkerOperation;
import com.clouway.calculator.markerbuttons.MarkerPoints;
import com.clouway.calculator.specificbuttons.CalculatorExpression;
import com.clouway.calculator.specificbuttons.ReducerText;
import com.clouway.calculator.specificbuttons.ResetText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Calculator extends JFrame {
  private Map<String, ActionOnText> mapOfButtons = new HashMap<String, ActionOnText>();
  private Map<String, MalformedText> mapOfSpecificButtons = new HashMap<String, MalformedText>();
  private JPanel mainPanel;
  private JTextField textField;
  private boolean isLastCharacterOperation = false;
  private boolean pointFlag = false;

  enum Numbers {
    ZERO('0'), ONE('1'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9');

    private Character number;

    Numbers(Character number) {
      this.number = number;
    }

    public Character getSymbol() {
      return number;
    }
  }
  enum Operations {
    PLUS('+'), MINUS('-'), DIVIDED('/'), MULTIPLICATION('*');

    private Character operations;

    Operations(Character number) {
      this.operations = number;
    }

    public Character getSymbol() {
      return operations;
    }
  }

  public boolean isLastCharacterOperation() {
    return isLastCharacterOperation;
  }

  public boolean isPointFlag() {
    return pointFlag;
  }

  public void setLastCharacterOperation(boolean isLastCharacterOperation) {
    this.isLastCharacterOperation = isLastCharacterOperation;
  }

  public void setPointFlag(boolean pointFlag) {
    this.pointFlag = pointFlag;
  }

  public Calculator() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    add(mainPanel);

    textField = new JTextField("");
    textField.setHorizontalAlignment(JTextField.RIGHT);
    mainPanel.add(textField, BorderLayout.NORTH);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 6));
    mainPanel.add(panel, BorderLayout.CENTER);

    addComponentsToButtonPanel(panel);

    setSize(500, 300);
    setVisible(true);
    pack();
  }

  private void addComponentsToButtonPanel(Container container) {
    for (final Numbers number : Numbers.values()) {
      JButton button = new JButton("" + number.getSymbol());
      container.add(button);
      ActionOnText pressedNumber = new MarkerNumbers(this);
      mapOfButtons.put(button.getText(), pressedNumber);
      button.addActionListener(listenerForPressedButton(button.getText()));
    }
    for (final Operations operations : Operations.values()) {
      JButton button = new JButton("" + operations.getSymbol());
      container.add(button);
      ActionOnText pressedOperation = new MarkerOperation(this);
      mapOfButtons.put(button.getText(), pressedOperation);
      button.addActionListener(listenerForPressedButton(button.getText()));
    }

    final JButton point = new JButton(".");
    container.add(point);
    ActionOnText pressedPointButton = new MarkerPoints(this);
    mapOfButtons.put(point.getText(), pressedPointButton);
    point.addActionListener(listenerForPressedButton(point.getText()));

    final JButton backSpace = new JButton("<--");
    container.add(backSpace);
    MalformedText pressedBackSpace = new ReducerText();
    mapOfSpecificButtons.put(backSpace.getText(), pressedBackSpace);
    backSpace.addActionListener(listenerForPressedSpecificButtons(backSpace.getText()));

    JButton deleted = new JButton("C");
    container.add(deleted);
    MalformedText pressedDeletedButton = new ResetText(this);
    mapOfSpecificButtons.put(deleted.getText(), pressedDeletedButton);
    deleted.addActionListener(listenerForPressedSpecificButtons(deleted.getText()));

    JButton equals = new JButton("=");
    container.add(equals);
    MalformedText pressedEqualsButton = new CalculatorExpression();
    mapOfSpecificButtons.put(equals.getText(), pressedEqualsButton);
    equals.addActionListener(listenerForPressedSpecificButtons(equals.getText()));
  }

  private ActionListener listenerForPressedSpecificButtons(final String symbol) {
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MalformedText action = mapOfSpecificButtons.get(symbol);
        String newDisplay = action.malformedExpression(textField.getText());
        textField.setText(newDisplay);
      }
    };
    return actionListener;
  }

  private ActionListener listenerForPressedButton(final String symbol) {
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ActionOnText act = mapOfButtons.get(symbol);
        String newDisplay = act.addingSymbolInEndOnText(textField.getText(), symbol);
        textField.setText(newDisplay);
      }
    };
    return actionListener;
  }
}
