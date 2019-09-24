package sqs;

import static sqs.Receive.receive;
import static sqs.Send.send;

public class App {
  public static void main (String[] args) {
    send();
    receive();
  }
}
