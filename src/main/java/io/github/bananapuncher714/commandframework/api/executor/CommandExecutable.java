package io.github.bananapuncher714.commandframework.api.executor;

import org.bukkit.command.CommandSender;

import io.github.bananapuncher714.commandframework.api.CommandParameters;

public interface CommandExecutable {
	void execute( CommandSender sender, String[] args, CommandParameters params );
}
