# Simple apps
This is just a bunch of projects, each containing a simple usage of some Java technology.

---

### Current projects
* #### SpringJavaMail
    A Spring Boot-based application, demonstrating a simple usage of [Spring API for JavaMail](https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#mail). Uses Google's SMTP server.
    
    Before you start the application, you have to [allow insecure applications to access](https://myaccount.google.com/lesssecureapps) the Google account you'll use as sender.
    
* #### SpringJMSWithRabbitMQ
    A Spring Boot-based application, demonstrating a simple usage of JMS messaging via RabbitMQ as a queue broker.
    
    Before you start this project, [install RabbitMQ](https://www.rabbitmq.com/download.html) and [enable the JMS plugin](https://github.com/rabbitmq/rabbitmq-jms-topic-exchange#installation). 
    Then you run RabbitMQ (``[sudo] rabbitmq-server``). You should see something like this:
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
     After the server has started, you may run the application.
    
* #### TelegramBot
    A Spring Boot-based application, demonstrating a simple usage of [TelegramBots](https://github.com/rubenlagus/TelegramBots "Java library to create bots using Telegram Bots API") for creating a primitive Telegram long polling bot. This is actually the back-end of the bot. Note, that the application uses SOCKS5 proxy, so you should remove proxying code if you don't need it.
    
    Before you start the application, you have to create a bot [here](http://t.me/BotFather "BotFather"). After that, place the token and the username of the bot into your *src/main/resources/application.properties* file. 
    ###### application.properties
    ```
    bot.token=912393:sAEYYz7YfDzhrt05lF5BM2wCotJn79EqbmA
    bot.username=simple_java_bot
    proxy.host=103.216.82.216
    proxy.port=6667
    ```    
    For further investigation, check out [Telegram Bots API](https://core.telegram.org/bots/api) and [examples](https://github.com/rubenlagus/TelegramBots#example-bots).

* #### SpringAOP
    A Spring Boot-based application, demonstrating a simple usage of [Spring AOP](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop) for conditional logging.
    
    Contains examples of a pointcut (by custom annotation), an aspect and an advice of ``@Before`` type (see the link above). There is also an example of an "inner call" - that is when annotated method is not affected by AOP because of being called not through a proxy, but from the same class.
    **Important:** Spring AOP is done by proxying. In this project, a CGLIB proxy is used. The other common way is using JDK dynamic proxies. [Here](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-proxying "About proxying mechanisms in Spring AOP") you can find more about proxying in Spring AOP.
    
* #### SpringSecurity
    A Spring Boot-based application, demonstrating a simple usage of [Spring Security](https://spring.io/guides/topicals/spring-security-architecture) for authentication and authorization.
    
    Contains examples of a custom ``AuthenticationManager`` and methods annotated by ``@Secured`` and ``@PreAuthorized``.
    
* #### Jabber
    A Spring Boot-based application, demonstrating a simple usage of [Smack API](https://igniterealtime.org/projects/smack/) for [XMPP protocol](https://xmpp.org/about/technology-overview.html) for sending jabber messages. Messages are sent via [jabber.hot-chilli.net](http://jabber.hot-chilli.net) - a free Jabber/XMPP service for everybody.
    
---
### How to work with this repository
#### How to get one project 
For example, you want to download SpringAOP project. There is absolutely **no need in cloning whole repository**, you can just do the following:
```
mkdir temporary_directory
cd temporary_directory
git init
git remote add origin https://github.com/borisPristupa/simple.git
git pull origin SpringAOP
```

#### How to add your own project / alter old projects
Just make a fork of this repository on GitHub, make your changes and create a pull request.
**Important**: keep each project's files in its own directory, whose name should contain no spaces.