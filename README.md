# Error-prone command framework designed for lazy Bukkit developers
Supports tab completes too! May or may not work randomly, depending on the weather.

## Goal:
This is meant to simplify the need to write giant if-trees or use some other valid command framework(like ACF). Currently it's a build and run sort of command and *should* work if you treat it nicely.

## Usage:
### SubCommands
To build a command and apply it:
```java
PluginCommand bukkitCommand; // Some random command from Bukkit
SubCommand command = new SubCommand( "main" )
    // Add a subcommand
    .add( new SubCommand( "sub1" )
        // Set the default action
        .defaultTo( new CommandExecutableMessage( "Subcommand 1 ran!" ) ) )

    // Add another subcommand
    .add( new SubCommand( "sub2" )
        // Make sure the sender is a player
        .addSenderValidator( new SenderValidatorPlayer() )
        .defaultTo( new CommandExecutableMessage( "Subcommand 2 ran as a player!" ) ) )
    .add( new SubCommand( "sub2" )
        // Add a subcommand to our current subcommand
        .add( new SubCommand( "subofsub2" )
            .defaultTo( new CommandExecutableMessage( "Subcommand of subcommand 2 ran!" ) ) )
        .defaultTo( new CommandExecutableMessage( "Subcommand 2 ran!" ) ) )
    // When the player provides an incorrect argument
    .whenUnknown( new CommandExecutableMessage( "Invalid Argument!" ) )
    .defaultTo( new CommandExecutableMessage( "You provided no arguments!" ) );
    // Apply it to a bukkit command, for tab completion and execution
    .applyTo( bukkitCommand );
```
Congratulations! Your command will now execute accordingly, depending on what arguments the sender provided. This will also provide the correct tab completes for the sender. You can use different InputValidators, to test for things like players, numbers, or a pattern. In addition to this, you can also validate a sender with a SenderValidator to check if they have a required permission, or is a player running your command.

### Executors
Your command can default to a CommandExecutable, which normally performs what you want the command to do. Here's an example:
```java
public class TestCommandExecutable implements CommandExecutable {
    void execute( CommandSender sender, String[] args, CommandParameters params ) {
        // args contains the leftover arguments that weren't parsed
        // params contains the results of the previous subcommands, if the input validators converted any arguments into an object other than a string
        sender.sendMessage( "You've reached the end!" );
    }
}
```

### Input validators and sender validators
Input valiators check if the input is valid, and convert an argument the user provides into another object if so. Ex:
```java
public class InputValidatorInt implements InputValidator< Integer > {
    @Override
    public Collection< String > getTabCompletes() {
        return null;
    }

    @Override
    public boolean isValid( String input, String[] args ) {
        try {
            Integer.valueOf( input );
        } catch ( Exception exception ) {
            return false;
        }
        return true;
    }

    @Override
    public Integer get( String input ) {
        return Integer.parseInt( input );
    }
}
```
This input validator checks to see if an argument is an integer. If so, they'll let it pass and parse it when it needs to be converted.

Sender validators check if the sender is allowed to run the subcommand. This can be used for permission checking, or making sure the sender is a player. Ex:
```java
public class SenderValidatorPlayer implements SenderValidator {
    @Override
    public boolean isValid( CommandSender sender ) {
        return sender instanceof Player;
    }
}
```