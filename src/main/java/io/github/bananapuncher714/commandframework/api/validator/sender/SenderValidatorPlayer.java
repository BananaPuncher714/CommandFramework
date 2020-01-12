package io.github.bananapuncher714.commandframework.api.validator.sender;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SenderValidatorPlayer implements SenderValidator {
	@Override
	public boolean isValid( CommandSender sender ) {
		return sender instanceof Player;
	}
}
