import React, {useEffect, useState} from 'react';
import { Consumer } from 'sqs-consumer';

import AWS from 'aws-sdk';

AWS.config.update({
  region: 'us-west-2',
  accessKeyId: process.env.REACT_APP_AWS_ACCESS_KEY_ID,
  secretAccessKey: process.env.REACT_APP_AWS_SECRET_ACCESS_KEY,
});

export default function App() {

  const [list, setList] = useState([]);
  const [list2, setList2] = useState([]);
  const [list3, setList3] = useState([]);

  useEffect( () => {
    const app = Consumer.create({
      queueUrl: 'https://sqs.us-west-2.amazonaws.com/830278276484/QueueA',
      handleMessage: handler,
    });
    const app2 = Consumer.create({
      queueUrl: 'https://sqs.us-west-2.amazonaws.com/830278276484/QueueB',
      handleMessage: handler2,
    });
    const app3 = Consumer.create({
      queueUrl: 'https://sqs.us-west-2.amazonaws.com/830278276484/QueueC',
      handleMessage: handler3,
    });

    function handler(message) {
      setList( (list) => [...list, message.Body]);
    }
    function handler2(message) {
      setList2( (list2) => [...list2, message.Body]);
    }
    function handler3(message) {
      setList3( (list3) => [...list3, message.Body]);
    }

    app.start();
    app2.start();
    app3.start();

    console.log(app);
    console.log(app2);
    console.log(app3);

    return () => {
      app.stop();
      app2.stop();
      app3.stop();
    };
  }, []);

  const flexItem = (queueName, list) => {
    return (
      <div className="flex-item">
        <h3>{queueName}</h3>
        <ul>
          {list.map( (item,i) => <li key={i}>{item}</li>)}
        </ul>
      </div>
    )
  }

  return (
    <div>
      <h2>SQS Responses</h2>
      <div id="flex-container">
        {flexItem('QueueA', list)}
        {flexItem('QueueB', list2)}
        {flexItem('QueueC', list3)}
      </div>
    </div>
  );
}
