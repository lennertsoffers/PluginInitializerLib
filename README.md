<br />

<div align="center">
  <a href="https://github.com/lennertsoffers/PluginInitializerLib">
    <img src="https://lennertsoffers.com/hosting/PluginInitializerLib.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">PluginInitializerLib</h3>

  <p align="center">
    A Spigot library that provides annotations to easily and quickly register events, commands and runnables to your Spigot Plugin
    <br />
    <br />
    <a href="https://github.com/lennertsoffers/PluginInitializerLib/issues">Report Bug</a>
    ·
    <a href="https://github.com/lennertsoffers/PluginInitializerLib/issues">Request Feature</a>
    .
    <a href="https://www.spigotmc.org/resources/plugininitializerlib.102391/">View on Spigot</a>
  </p>
</div>


## About The Project
PluginInitializerLib is a Spigot library that helps you with the development of Spigot plugins.
It is mainly used to speed up the process of registering events, commands and other tedious tasks.

This functionality is achieved with simply adding an annotation to the class to indicate which type of plugin class it is.
The rest is all handled by the library. So to register a new event, you only have to add the `@EventListener` above the class.

Currently, this library is in active development to add new features to make the life of Spigot developers easier.


## Get Started
To use this library you have two options:

### Use Maven Dependency
If you have a Maven project, just add the dependency to your pom.xml

```xml
<dependency>
    <groupId>io.github.lennertsoffers</groupId>
    <artifactId>PluginInitializerLib</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Download On Spigot
The project is also published as a Spigot resource, so you can download it on their website if you wish.

[Download on Spigot](https://www.spigotmc.org/resources/plugininitializerlib.102391/)


## Usage

### Initialization

Initialize the plugin in the `onEnable()` method of your plugin
```java
@Override
public void onEnable() {
    PluginInitializerLib.init(this, "me.example");
}
```
Where `this` is your Plugin instance and `"me.example"` your groupId.

Now you can make use of the provided annotations

### Registering Events

Each Listener class annotated with `@EventListener` will be automatically be registered, no matter in which folder you put it. (As long it is in your project of course😃)

```java
@EventListener
public class ExampleListener implements Listener {
    @EventHandler
    public void onExample(ExampleEvent event) {}
}
```

### Registering Commands

The `@NamedCommandExecutor` annotation registers all commandExecutors to the server. The name the commandExecutor will be registered to is the name you provide as a parameter in the annotation.

```java
@NamedCommandExecutor(commandName = "example")
public class ExampleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {}
}
```

### Auto Running BukkitRunnables

With the `@ScheduledBukkitRunnable` annotation, you can run BukkitRunnables in the initialisation step of your plugin.
It provides the same functionalities as invoking this runnable directly, except there won't be such a mess of code in your plugin main class.

```java
@ScheduledBukkitRunnable(runMethod = RunMethod.RUN_TIMER, async = true, delay = 1, period = 40)
public class ExampleRunnable extends BukkitRunnable {
    @Override
    public void run() {}
}
```

## Contributing

If you have any suggestions, reports or other requests, please let us know via an issue.

Contributions are greatly appreciated and everyone is free to do them.
Please fork this repo and create a new pull request.

## License

Distributed under the GPL-3.0 license, See [LICENSE.txt](https://github.com/lennertsoffers/PluginInitializerLib/blob/main/LICENSE) for more information

