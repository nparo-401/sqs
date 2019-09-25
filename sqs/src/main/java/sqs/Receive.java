package sqs;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class Receive implements RequestHandler<SQSEvent, Void> {
  @Override
  public Void handleRequest(SQSEvent event, Context context) {
    for(SQSEvent.SQSMessage msg : event.getRecords()){
      System.out.println(msg.getMessageId() + ": " + msg.getBody());
    }
    return null;
  }
}
