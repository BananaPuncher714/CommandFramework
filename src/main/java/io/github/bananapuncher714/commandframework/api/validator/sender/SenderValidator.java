package io.github.bananapuncher714.commandframework.api.validator.sender;

import org.bukkit.command.CommandSender;

public interface SenderValidator {
	boolean isValid( CommandSender sender );
}
