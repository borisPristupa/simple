# Simple apps
This is just a bunch of projects, each containing a simple usage of some framework/library.

---

### Current projects
* #### SpringJavaMail
    A Spring Boot-based application, demonstrating a simple usage of Spring API for JavaMail. Uses Google's SMTP server.
    
* #### SpringJMSWithRabbitMQ
    A Spring Boot-based application, demonstrating a simple usage of JMS messaging via RabbitMQ as a queue broker.
    
    Before you start this project is [install RabbitMQ](https://www.rabbitmq.com/download.html) and [enable the JMS plugin](https://github.com/rabbitmq/rabbitmq-jms-topic-exchange#installation). 
    Then you run RabbitMQ ([sudo] rabbitmq-server). You should see something like this:
    ```
                RabbitMQ 3.6.10. Copyright (C) 2007-2017 Pivotal Software, Inc.
  ##  ##      Licensed under the MPL.  See http://www.rabbitmq.com/
  ##  ##
  ##########  Logs: /var/log/rabbitmq/rabbit@boris-VirtualBox.log
  ######  ##        /var/log/rabbitmq/rabbit@boris-VirtualBox-sasl.log
  ##########
                Starting broker...
     completed with 1 plugins.
     ```
     After the server has started, you may start the application.
    
* #### TelegramBot
    A Spring Boot-based application, demonstrating a simple usage of [TelegramBots](https://github.com/rubenlagus/TelegramBots "Java library to create bots using Telegram Bots API"). This is actually a back-end of the bot. Note, that the application uses proxy, so you should remove proxy-ing code if you don't need it.
    
    Before you start the application, you have to create a bot [here](http://t.me/BotFather "BotFather"). After that, place the token and the username of the bot into your *src/main/resources/application.properties* file. 
    ###### application.properties
    ```
    bot.token=912393:sAEYYz7YfDzhrt05lF5BM2wCotJn79EqbmA
    bot.username=simple_java_bot
    proxy.host=103.216.82.216
    proxy.port=6667
    ```    
    For further investigation, check out [Telegram Bots API](https://core.telegram.org/bots/api) and [examples](https://github.com/rubenlagus/TelegramBots#example-bots).

