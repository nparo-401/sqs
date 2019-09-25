package sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.Random;

public class Publish {
  private static String getAlphaNumericString(int n) {
    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                 + "0123456789"
                                 + "abcdefghijklmnopqrstuvxyz";
    
    StringBuilder sb = new StringBuilder(n);
    for (int i = 0; i < n; i++) {
      int index = (int)(AlphaNumericString.length() * Math.random());
      sb.append(AlphaNumericString.charAt(index));
    }
    
    return sb.toString();
  }
  
  public static void publish(String num) {
    int amount = Integer.parseInt(num);
    final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
    
    for (int i = 0; i < amount; i++) {
      String randQueue = randomQueueChooser();
      System.out.println(randQueue);
      String queueUrl = sqs.getQueueUrl(randQueue).getQueueUrl();
      SendMessageRequest send_msg_request = new SendMessageRequest().withQueueUrl(queueUrl).withMessageBody(getAlphaNumericString(20));
      sqs.sendMessage(send_msg_request);
    }
  }
  
  private static String randomQueueChooser() {
    String response = "";
    Random random = new Random();
    int randNum = random.nextInt(3);
    switch (randNum) {
      case 0:
        response = "QueueA";
        break;
      case 1:
        response = "QueueB";
        break;
      case 2:
        response = "QueueC";
        break;
    }
    return response;
  }
}
